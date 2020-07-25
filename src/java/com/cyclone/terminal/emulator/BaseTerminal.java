package com.cyclone.terminal.emulator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.cyclone.terminal.emulator.cell.BlinkState;
import com.cyclone.terminal.emulator.cell.Cell;
import com.cyclone.terminal.emulator.cell.LineHeight;
import com.cyclone.terminal.emulator.cell.LineWidth;
import com.cyclone.terminal.emulator.cell.Rendition;
import com.cyclone.terminal.emulator.datascope.DataScope;
import com.cyclone.terminal.emulator.datascope.SimpleDataScopeDialog.Direction;
import com.cyclone.terminal.emulator.font.VT100.CharSet;
import com.cyclone.terminal.emulator.led.Panel;
import com.cyclone.terminal.parser.Parser;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class BaseTerminal extends Parser implements Terminal
{
    private LogicalScreen logicalScreen;

    private Composite composite;

    private Canvas canvas;

    private Menu menu;

    private Panel ledPanel;

    private CLabel statusLine;

    private GC gc;

    private Font font;

    private Image image;

    private CursorStyle cursorStyle = CursorStyle.BLOCK;

    private Thread blinkThread;

    private CharSet charSet;

    private CharacterSetSequence charSetSequenceOnDisplay;

    private CharacterSet charSetOnDisplay;

    private Rendition activeRendition;

    private boolean haveSelection;

    private int selectionStartRow;

    private int selectionStartColumn;

    private int selectionEndRow;

    private int selectionEndColumn;

    private boolean readOnly;

    private boolean invisibleCursor;

    private boolean linefeed;

    private List<ScreenRow> screenData;

    private boolean useApplicationCursorKeys;

    private boolean autoRepeat = true;

    private boolean keyDown; // = false;

    private boolean allowWidthChange = true;

    private DataScope dataScope;

    private boolean bsSendsDel = true;

    /**
     * Constructor for BaseTerminal.
     * 
     * @param a_parent
     */
    public BaseTerminal(final Composite a_parent)
    {
        initialize(a_parent);
    }

    /**
     * Constructor for BaseTerminal.
     * 
     * @param a_parent
     * @param a_width
     * @param a_height
     * @param a_history
     */
    public BaseTerminal(final Composite a_parent, final int a_width,
            final int a_height, final int a_history)
    {
        logicalScreen = new LogicalScreen(a_width, a_height, a_history);

        initialize(a_parent);
    }

    /**
     * Indicates that the specified data has been typed or is being sent in
     * response to a terminal query
     * 
     * @param a_data
     */
    protected abstract void onTerminalData(final byte[] a_data);

    protected abstract byte[] translateKeyCode(final int a_keyCode,
            final char a_character, final boolean a_applicationKeys);

    private void initialize(final Composite a_parent)
    {
        charSetSequenceOnDisplay = CharacterSetSequence.G0;
        charSetOnDisplay = CharacterSet.USASCII;
        activeRendition = new Rendition();
        useApplicationCursorKeys = false;

        initializeCells();
        initializeEmulatorWindow(a_parent);
        initializeBlinking();

        readOnly = false;
    }

    private void initializeEmulatorWindow(final Composite a_parent)
    {
        createComposite(a_parent);

        charSet = new CharSet(getDisplay());

        gc = new GC(canvas);
        gc.setFont(font);
        gc.setBackground(new Color(getDisplay(),
                Colours.NORMAL[Colours.BACKGROUND_COLOUR_INDEX]));
        gc.setForeground(new Color(getDisplay(),
                Colours.NORMAL[Colours.FOREGROUND_COLOUR_INDEX]));
        gc.fillRectangle(canvas.getClientArea());

        canvas.addKeyListener(new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent a_e)
            {
                if (!keyDown || autoRepeat)
                {
                    if (!readOnly)
                    {
                        final ByteArrayOutputStream bb = new ByteArrayOutputStream();

                        final byte[] keyCode = translateKeyCode(a_e.keyCode,
                                a_e.character, useApplicationCursorKeys);
                        if (keyCode != null)
                        {
                            try
                            {
                                bb.write(keyCode);
                            }
                            catch (IOException e)
                            {

                            }
                        }
                        else
                        {
                            if (a_e.character == 13)
                            {
                                if (linefeed)
                                {
                                    bb.write((byte) a_e.character);
                                    bb.write((byte) 10);
                                }
                                else
                                {
                                    bb.write((byte) a_e.character);
                                }
                            }
                            else if (a_e.character == 8)
                            {
                                // BS (Backspace key pressed) so send DEL (127)
                                if (bsSendsDel)
                                {
                                    bb.write((byte) 127);
                                }
                            }
                            else
                            {
                                if (a_e.character != 0)
                                {
                                    bb.write((byte) a_e.character);
                                }
                            }
                        }

                        if (dataScope != null)
                        {
                            dataScope.add(bb.toByteArray(), bb.size(),
                                    Direction.SEND);
                        }

                        onTerminalData(bb.toByteArray());
                    }
                }

                keyDown = true;
            }

            @Override
            public void keyReleased(KeyEvent a_e)
            {
                keyDown = false;
            }

        });

        canvas.addControlListener(new ControlListener()
        {
            @Override
            public void controlResized(ControlEvent a_e)
            {
                composite.redraw();
            }

            @Override
            public void controlMoved(ControlEvent a_e)
            {
                composite.redraw();
            }
        });

        canvas.addPaintListener(a_e -> redrawScreen());

        enableMenu(true);

        // Set the active position to row 0, column 0 (the home position)
        logicalScreen.getCursor().home();

        drawScreen();
    }

    /**
     * This method initializes composite
     * 
     * @param a_parent
     */
    private void createComposite(final Composite a_parent)
    {
        // composite = new Composite(a_parent, SWT.NONE);
        // composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
        // true));
        composite = a_parent;

        final GridLayout compositeGridLayout = new GridLayout();
        compositeGridLayout.numColumns = 5;
        compositeGridLayout.horizontalSpacing = 0;
        compositeGridLayout.verticalSpacing = 0;
        compositeGridLayout.marginWidth = 0;
        compositeGridLayout.marginHeight = 0;
        compositeGridLayout.makeColumnsEqualWidth = false;
        composite.setLayout(compositeGridLayout);

        composite.addDisposeListener(a_e -> {
            blinkThread.interrupt();
            try
            {
                blinkThread.join();
            }
            catch (InterruptedException ignored)
            {

            }
            blinkThread = null;
        });

        final GridData canvasGridData = new GridData(SWT.FILL, SWT.FILL, true,
                true);
        canvasGridData.horizontalSpan = 5;
        canvas = new Canvas(composite, SWT.NO_BACKGROUND);
        canvas.setLayoutData(canvasGridData);

        font = new Font(getDisplay(), "fixed", 120, SWT.NONE);
        canvas.setFont(font);
        canvas.setBackground(new Color(getDisplay(),
                Colours.NORMAL[Colours.BACKGROUND_COLOUR_INDEX]));
        canvas.setForeground(new Color(getDisplay(),
                Colours.NORMAL[Colours.FOREGROUND_COLOUR_INDEX]));

        statusLine = new CLabel(composite, SWT.NONE);
        statusLine.setText("Online");
        statusLine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        ledPanel = new Panel(composite, 4);

        ledPanel.refresh();
    }

    private void initializeCells()
    {
        screenData = new ArrayList<>();

        for (int iRow = 0; iRow < (logicalScreen.getHeight()
                + logicalScreen.getHistory()); iRow++)
        {
            screenData.add(new ScreenRow(iRow, logicalScreen.getWidth()));
        }
    }

    @Override
    public final Panel getLEDPanel()
    {
        return ledPanel;
    }

    @Override
    public final void showLEDPanel(final boolean a_show)
    {
        if (!isDisposed())
        {
            ledPanel.show(a_show);
            statusLine.pack();
        }
    }

    @Override
    public final void setStatus(final String a_status)
    {
        if (!isDisposed())
        {
            statusLine.setText(a_status);
        }
    }

    /**
     * @return true if the display widget the terminal is using has been
     *         disposed
     */
    public boolean isDisposed()
    {
        final Display display = getDisplay();
        if (display != null)
        {
            return display.isDisposed();
        }

        return true;
    }

    @Override
    public final void showStatus(final boolean a_show)
    {
        if (!isDisposed())
        {
            statusLine.setVisible(a_show);
            statusLine.pack();
        }
    }

    /**
     * Ensure that our start selection is always before our end selection
     */
    private void reorderSelection()
    {
        if (selectionEndRow < selectionStartRow)
        {
            int tmp;

            tmp = selectionStartRow;
            selectionStartRow = selectionEndRow;
            selectionEndRow = tmp;

            tmp = selectionStartColumn;
            selectionStartColumn = selectionEndColumn;
            selectionEndColumn = tmp;
        }
        else
        {
            if (selectionEndRow == selectionStartRow)
            {
                if (selectionEndColumn < selectionStartColumn)
                {
                    final int tmp = selectionStartColumn;
                    selectionStartColumn = selectionEndColumn;
                    selectionEndColumn = tmp;
                }
            }
        }
    }

    private String getSelectedText()
    {
        final StringBuilder selection = new StringBuilder();

        for (int iRow = selectionStartRow; iRow <= selectionEndRow; iRow++)
        {
            final StringBuilder sb = new StringBuilder();
            if ((iRow == selectionStartRow) && (iRow == selectionEndRow))
            {
                // All on one row, so get the characters...
                for (int iCol = selectionStartColumn; iCol <= selectionEndColumn; iCol++)
                {
                    sb.append(
                            screenData.get(iRow).getCell(iCol).getCharacter());
                }
                selection.append(sb.toString().trim());
            }
            else
            {
                // Multiple rows...
                if (iRow == selectionStartRow)
                {
                    for (int iCol = selectionStartColumn; iCol < logicalScreen
                            .getWidth(); iCol++)
                    {
                        sb.append(screenData.get(iRow).getCell(iCol)
                                .getCharacter());
                    }
                    selection.append(sb.toString().trim());
                    selection.append("\r\n");
                }
                else
                {
                    if (iRow == selectionEndRow)
                    {
                        for (int iCol = 0; iCol <= selectionEndRow; iCol++)
                        {
                            sb.append(screenData.get(iRow).getCell(iCol)
                                    .getCharacter());
                        }
                        selection.append(sb.toString().trim());
                    }
                    else
                    {
                        for (int iCol = 0; iCol < logicalScreen
                                .getWidth(); iCol++)
                        {
                            sb.append(screenData.get(iRow).getCell(iCol)
                                    .getCharacter());
                        }
                        selection.append(sb.toString().trim());
                        selection.append("\r\n");
                    }
                }

            }
        }

        return selection.toString();
    }

    @Override
    public final void enableMenu(final boolean a_enable)
    {
        if (a_enable)
        {
            if (menu == null)
            {
                final Clipboard cb = new Clipboard(getDisplay());

                menu = new Menu(composite.getShell(), SWT.POP_UP);

                final MenuItem copyItem = new MenuItem(menu, SWT.PUSH);
                copyItem.setText("Copy");
                copyItem.addListener(SWT.Selection, a_event -> {
                    final String textData = getSelectedText();

                    final TextTransfer textTransfer = TextTransfer
                            .getInstance();
                    cb.setContents(new Object[]
                    {textData}, new Transfer[]
                    {textTransfer});
                });

                final MenuItem pasteItem = new MenuItem(menu, SWT.PUSH);
                pasteItem.setText("Paste");
                pasteItem.addListener(SWT.Selection, a_event -> {
                    final TextTransfer transfer = TextTransfer.getInstance();
                    final String data = (String) cb.getContents(transfer);
                    if (data != null)
                    {
                        onTerminalData(data.getBytes());
                    }
                });

                menu.addMenuListener(new MenuAdapter()
                {
                    @Override
                    public void menuShown(MenuEvent a_event)
                    {
                        // is copy valid?
                        final String selection = getSelectedText();
                        copyItem.setEnabled(selection.length() > 0);

                        // is paste valid?
                        boolean enabled = false;
                        if (!readOnly)
                        {
                            final TransferData[] available = cb
                                    .getAvailableTypes();
                            for (TransferData element : available)
                            {
                                if (TextTransfer.getInstance()
                                        .isSupportedType(element))
                                {
                                    enabled = true;
                                    break;
                                }
                            }
                        }
                        pasteItem.setEnabled(enabled);
                    }
                });
            }

            final Listener listener = new Listener()
            {
                private boolean inDrag;

                @Override
                public void handleEvent(Event a_event)
                {
                    switch (a_event.type)
                    {
                        case SWT.MouseDown:
                            if (a_event.button == 1)
                            {
                                inDrag = true;

                                // Save the drag start position, i.e. row and
                                // column...
                                setSelectionStartRow(a_event.y);
                                setSelectionStartColumn(a_event.x);

                                setSelectionEndRow(a_event.y);
                                setSelectionEndColumn(a_event.x);

                                haveSelection = true;

                                drawScreen();
                            }
                            break;
                        case SWT.MouseMove:
                            // We dont get the button number set when the mouse
                            // move event is given to us, so, we only need to do
                            // anything here if we are currently in a drag
                            // operation.
                            if (inDrag)
                            {
                                // Get the current cursor point and select
                                // all
                                // cells from the start to here
                                setSelectionEndRow(a_event.y);
                                setSelectionEndColumn(a_event.x);

                                reorderSelection();

                                drawScreen();
                            }
                            break;
                        case SWT.MouseUp:
                            //
                            if ((a_event.button == 1) && inDrag)
                            {
                                // stop the selection and save the final cell
                                // position i.e. row and column.
                                setSelectionEndRow(a_event.y);
                                setSelectionEndColumn(a_event.x);

                                reorderSelection();

                                if ((getSelectionEndColumn() == getSelectionStartColumn())
                                        && (getSelectionStartRow() == getSelectionEndRow()))
                                {
                                    haveSelection = false;
                                }

                                inDrag = false;

                                drawScreen();
                            }
                            break;
                        default:
                            break;
                    }
                }
            };

            canvas.addListener(SWT.MouseDown, listener);
            canvas.addListener(SWT.MouseMove, listener);
            canvas.addListener(SWT.MouseUp, listener);

            canvas.setMenu(menu);
        }
        else
        {
            canvas.setMenu(null);
        }
    }

    private void initializeBlinking()
    {
        blinkThread = new Thread(() -> {
            try
            {
                boolean blinkOn = false;
                while (true)
                {
                    final BlinkState on = (blinkOn ? BlinkState.ON
                            : BlinkState.OFF);

                    // Go through each cell on the screen blinking its
                    // value.
                    if (!isDisposed())
                    {
                        getDisplay().asyncExec(() -> {
                            for (int iRow = 0; iRow < logicalScreen
                                    .getHeight(); iRow++)
                            {
                                for (int iColumn = 0; iColumn < logicalScreen
                                        .getWidth(); iColumn++)
                                {
                                    final Cell cell = screenData.get(iRow)
                                            .getCell(iColumn);

                                    // is this cell the one under the
                                    // cursor?
                                    if ((iRow == logicalScreen.getCursor()
                                            .getRow())
                                            && (iColumn == logicalScreen
                                                    .getCursor().getColumn()))
                                    {
                                        if (!invisibleCursor)
                                        {
                                            // Blink the cursor...
                                            if (logicalScreen.cursorInBounds())
                                            {
                                                switch (cursorStyle)
                                                {
                                                    default:
                                                    case BLOCK:
                                                        drawCell(cell, false,
                                                                on);
                                                        break;
                                                    case UNDERLINE:
                                                        drawCell(cell, false,
                                                                on);
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if (cell.getRendition().isBlink())
                                        {
                                            // Blink the cell...
                                            drawCell(cell, false, on);
                                        }
                                        else
                                        {
                                            drawCell(cell, false,
                                                    BlinkState.OFF);
                                        }
                                    }
                                }
                            }
                        });
                    }
                    Thread.sleep(500);
                    blinkOn = !blinkOn;
                }
            }
            catch (InterruptedException ignored)
            {
            }
        });

        blinkThread.start();
    }

    /**
     * @return the display
     */
    protected final Display getDisplay()
    {
        return composite.getDisplay();
    }

    @Override
    public final Composite getParent()
    {
        return composite.getParent();
    }

    private Image getImage()
    {
        if (image == null)
        {
            final Point charSize = charSet.getCharSize();
            image = new Image(getDisplay(),
                    charSize.x * logicalScreen.getWidth(),
                    charSize.y * logicalScreen.getHeight());

            final GC imgGC = new GC(getImage());
            try
            {
                imgGC.setBackground(new Color(getDisplay(),
                        Colours.NORMAL[Colours.BACKGROUND_COLOUR_INDEX]));
                imgGC.fillRectangle(0, 0, charSize.x * logicalScreen.getWidth(),
                        charSize.y * logicalScreen.getHeight());
            }
            finally
            {
                imgGC.dispose();
            }
        }

        return image;
    }

    private void redrawScreen()
    {
        final Rectangle clientRect = canvas.getClientArea();

        gc.drawImage(getImage(), 0, 0, getImage().getBounds().width,
                getImage().getBounds().height, 0, 0, clientRect.width,
                clientRect.height);
    }

    /**
     * draw the contents of the screen.
     */
    public final void drawScreen()
    {
        setRedraw(false);

        // We are drawing the entire screen, so get a new image for it...
        if (image != null)
        {
            image.dispose();
            image = null;
        }

        // Draw each cell on the screen, ignore any cells that have a rendition
        // that is the default in order to improve performance especially when
        // scrolling.
        for (int iRow = 0; iRow < logicalScreen.getHeight(); iRow++)
        {
            for (int iColumn = 0; iColumn < logicalScreen.getWidth(); iColumn++)
            {
                final Cell cell = screenData.get(iRow).getCell(iColumn);
                final boolean doDraw;
                if (cell.getCharacter() != 32)
                {
                    doDraw = true;
                }
                else
                {
                    doDraw = !cell.getRendition().isPlain();
                }

                if (doDraw)
                {
                    drawCell(cell, false, BlinkState.OFF);
                }
            }
        }

        drawSelectedCells();

        setRedraw(true);
    }

    /**
     * Determines if the specified cell is within the current selection range.
     * 
     * @param a_cell
     * @return true if the cell is withing the range of selected cells on screen
     */
    private boolean isCellSelected(final Cell a_cell)
    {
        final boolean selected;

        if (haveSelection)
        {
            // Check the rows, is this cell on one of the selected rows?
            if ((a_cell.getRow() >= selectionStartRow)
                    && (a_cell.getRow() <= selectionEndRow))
            {
                // Check to see if the column for the cell is in the selected
                // area,
                // the first and last row of the selected area are special
                // because
                // they may not start or finish at the beginning or end of the
                // row
                // respectively
                if (a_cell.getRow() == selectionStartRow)
                {
                    // On the first row, is our column after the selection start
                    // column?
                    if (a_cell.getColumn() >= selectionStartColumn)
                    {
                        // But is this our end row too?
                        if (a_cell.getRow() == selectionEndRow)
                        {
                            // Last row, is our column before the end column?
                            selected = a_cell.getColumn() <= selectionEndColumn;
                        }
                        else
                        {
                            selected = true;
                        }
                    }
                    else
                    {
                        selected = false;
                    }
                }
                else
                {
                    if (a_cell.getRow() == selectionEndRow)
                    {
                        // Last row, is our column before the end column?
                        selected = a_cell.getColumn() <= selectionEndColumn;
                    }
                    else
                    {
                        // An in between row, so, we must be selected
                        selected = true;
                    }
                }
            }
            else
            {
                // Not on one of our selection rows, return false
                selected = false;
            }
        }
        else
        {
            selected = false;
        }

        return selected;
    }

    private void drawSelectedCells()
    {
        setRedraw(false);

        if (haveSelection)
        {
            for (int iRow = selectionStartRow; iRow <= selectionEndRow; iRow++)
            {
                if (iRow == selectionStartRow)
                {
                    if (iRow == selectionEndRow)
                    {
                        for (int iCol = selectionStartColumn; iCol <= selectionEndColumn; iCol++)
                        {
                            drawCell(screenData.get(iRow).getCell(iCol), false,
                                    BlinkState.OFF);
                        }
                    }
                    else
                    {
                        for (int iCol = selectionStartColumn; iCol < logicalScreen
                                .getWidth(); iCol++)
                        {
                            drawCell(screenData.get(iRow).getCell(iCol), false,
                                    BlinkState.OFF);
                        }
                    }
                }
                else
                {
                    if (iRow == selectionEndRow)
                    {
                        for (int iCol = 0; iCol < selectionEndColumn; iCol++)
                        {
                            drawCell(screenData.get(iRow).getCell(iCol), false,
                                    BlinkState.OFF);
                        }
                    }
                    else
                    {
                        for (int iCol = 0; iCol < logicalScreen
                                .getWidth(); iCol++)
                        {
                            drawCell(screenData.get(iRow).getCell(iCol), false,
                                    BlinkState.OFF);
                        }
                    }
                }
            }
        }

        setRedraw(true);

        canvas.getDisplay().readAndDispatch();
    }

    /**
     * Draw the cell to the screen.
     * 
     * @param a_cell
     * @param a_useActiveRendition
     * @param a_blinkState
     */
    protected final synchronized void drawCell(final Cell a_cell,
            final boolean a_useActiveRendition, final BlinkState a_blinkState)
    {
        if (!canvas.isDisposed())
        {
            // Set this cell to use the active rendition
            if (a_useActiveRendition)
            {
                a_cell.setRendition(activeRendition);
            }

            final GC imgGC = new GC(getImage());
            try
            {
                final Point charSize = charSet.getCharSize();

                final boolean selected = isCellSelected(a_cell);

                try
                {
                    int cellX;
                    int cellY;
                    final Image cellImage = charSet.getImage(a_cell, selected,
                            a_blinkState);

                    switch (a_cell.getLineHeight())
                    {
                        default:
                        case NORMAL:
                            switch (a_cell.getLineWidth())
                            {
                                default:
                                case NORMAL:
                                    cellX = charSize.x * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0, 0,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height, cellX,
                                            cellY, charSize.x, charSize.y);
                                    break;
                                case DOUBLE:
                                    cellX = charSize.x * 2 * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0, 0,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height, cellX,
                                            cellY, charSize.x * 2, charSize.y);
                                    break;
                            }
                            break;
                        case TOP:
                            switch (a_cell.getLineWidth())
                            {
                                default:
                                case NORMAL:
                                    cellX = charSize.x * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0, 0,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height / 2,
                                            cellX, cellY, charSize.x,
                                            charSize.y);
                                    break;
                                case DOUBLE:
                                    cellX = charSize.x * 2 * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0, 0,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height / 2,
                                            cellX, cellY, charSize.x * 2,
                                            charSize.y);
                                    break;
                            }
                            break;
                        case BOTTOM:
                            switch (a_cell.getLineWidth())
                            {
                                default:
                                case NORMAL:
                                    cellX = charSize.x * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0,
                                            cellImage.getBounds().height / 2,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height / 2,
                                            cellX, cellY, charSize.x,
                                            charSize.y);
                                    break;
                                case DOUBLE:
                                    cellX = charSize.x * 2 * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0,
                                            cellImage.getBounds().height / 2,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height / 2,
                                            cellX, cellY, charSize.x * 2,
                                            charSize.y);
                                    break;
                            }
                            break;
                    }
                }
                catch (EmulatorException e)
                {

                }

                canvas.redraw();
            }
            finally
            {
                imgGC.dispose();
            }
        }
    }

    /**
     * @param a_ch
     */
    protected final void putCharacter(final char a_ch)
    {
        if (!canvas.isDisposed())
        {
            final Cell cell = screenData.get(logicalScreen.getCursor().getRow())
                    .getCell(logicalScreen.getCursor().getColumn());
            cell.setCharacter(a_ch);
            // System.out.println("Cell at ("
            // + logicalScreen.getCursorPosition().getRow() + ","
            // + logicalScreen.getCursorPosition().getColumn()
            // + ") using set " + charSetSequenceOnDisplay + ":"
            // + charSetOnDisplay);
            cell.setCharacterSetSequence(getCharSetsSequenceOnDisplay());
            cell.setCharacterSet(getCharSetOnDisplay());

            drawCell(cell, true, BlinkState.OFF);

            logicalScreen.getCursor().right();
        }
    }

    /**
     * Perform a linefeed on the display
     */
    protected final void performLF()
    {
        setRedraw(false);

        drawCell(
                screenData.get(logicalScreen.getCursor().getRow())
                        .getCell(logicalScreen.getCursor().getColumn()),
                false, BlinkState.OFF);

        final Scroll scroll;
        if (linefeed)
        {
            scroll = logicalScreen.getCursor().down();
            logicalScreen.getCursor().setColumn(0);
        }
        else
        {
            scroll = logicalScreen.getCursor().down();
        }

        switch (scroll.getScrollDirection())
        {
            case DOWN:
                for (int i = 0; i < scroll.getScrollAmount(); i++)
                {
                    scrollDown();
                }
                break;
            case UP:
                for (int i = 0; i < scroll.getScrollAmount(); i++)
                {
                    scrollUp();
                }
                break;
            case NONE:
            default:
                break;
        }

        setRedraw(true);
    }

    /**
     * perform a carriage return on the display
     */
    protected final void performCR()
    {
        drawCell(
                screenData.get(logicalScreen.getCursor().getRow())
                        .getCell(logicalScreen.getCursor().getColumn()),
                false, BlinkState.OFF);

        logicalScreen.getCursor().setColumn(0);
    }

    /**
     * perform a backspace on the display
     */
    protected final void performBS()
    {
        logicalScreen.getCursor().left();
    }

    protected final void performTAB()
    {
        logicalScreen.getCursor().tab();
    }

    /**
     *
     */
    public final void scrollDown()
    {
        // TODO scrollDown()
    }

    /**
     *
     */
    public final void scrollUp()
    {
        final int topMargin = getLogicalScreen().getCursor().getTopMargin();
        final int bottomMargin = getLogicalScreen().getCursor()
                .getBottomMargin();

        screenData.remove(topMargin);

        screenData.add(bottomMargin, new ScreenRow(logicalScreen.getHeight(),
                logicalScreen.getWidth() + 1));

        for (int iRow = topMargin; iRow <= bottomMargin; iRow++)
        {
            screenData.get(iRow).setRow(iRow);
        }

        // If there is a selection, adjust its start and end row too...
        if (haveSelection)
        {
            final Rectangle clientArea = canvas.getClientArea();
            final int cellHeight = clientArea.height
                    / logicalScreen.getHeight();

            selectionStartRow -= cellHeight;
            selectionEndRow -= cellHeight;

            if (selectionStartRow < 0)
            {
                selectionStartRow = 0;
                selectionEndRow = 0;
                selectionStartColumn = 0;
                selectionEndColumn = 0;

                haveSelection = false;
            }
        }

        drawScreen();
    }

    /**
     * @param a_startRow
     * @param a_startColumn
     * @param a_endRow
     * @param a_endColumn
     */
    protected final void eraseCells(final int a_startRow,
            final int a_startColumn, final int a_endRow, final int a_endColumn)
    {
        setRedraw(false);

        for (int iRow = a_startRow; iRow <= a_endRow; iRow++)
        {
            if (iRow == a_startRow)
            {
                // On the starting row, so erase from the start column to the
                // end of the row, or, the end column, if we are also on the end
                // row.
                if (iRow == a_endRow)
                {
                    for (int iColumn = a_startColumn; iColumn <= a_endColumn; iColumn++)
                    {
                        if (logicalScreen.cursorInBounds(iRow, iColumn))
                        {
                            screenData.get(iRow).getCell(iColumn).reset();
                            drawCell(screenData.get(iRow).getCell(iColumn),
                                    false, BlinkState.OFF);
                        }
                    }
                }
                else
                {
                    for (int iColumn = a_startColumn; iColumn <= logicalScreen
                            .getWidth(); iColumn++)
                    {
                        if (logicalScreen.cursorInBounds(iRow, iColumn))
                        {
                            screenData.get(iRow).getCell(iColumn).reset();
                            drawCell(screenData.get(iRow).getCell(iColumn),
                                    false, BlinkState.OFF);
                        }
                    }
                }
            }
            else
            {
                // Not on the start row, but, are we on the end row?
                if (iRow == a_endRow)
                {
                    if (iRow == a_startRow)
                    {
                        for (int iColumn = a_startColumn; iColumn <= a_endColumn; iColumn++)
                        {
                            if (logicalScreen.cursorInBounds(iRow, iColumn))
                            {
                                screenData.get(iRow).getCell(iColumn).reset();
                                drawCell(screenData.get(iRow).getCell(iColumn),
                                        false, BlinkState.OFF);
                            }
                        }
                    }
                    else
                    {
                        // On the last row, erase from the first column up until
                        // the final column
                        for (int iColumn = 0; iColumn <= a_endColumn; iColumn++)
                        {
                            if (logicalScreen.cursorInBounds(iRow, iColumn))
                            {
                                screenData.get(iRow).getCell(iColumn).reset();
                                drawCell(screenData.get(iRow).getCell(iColumn),
                                        false, BlinkState.OFF);
                            }
                        }
                    }
                }
                else
                {
                    // An intermediate row, erase the entire row...
                    for (int iColumn = 0; iColumn <= logicalScreen
                            .getWidth(); iColumn++)
                    {
                        if (logicalScreen.cursorInBounds(iRow, iColumn))
                        {
                            screenData.get(iRow).getCell(iColumn).reset();
                            drawCell(screenData.get(iRow).getCell(iColumn),
                                    false, BlinkState.OFF);
                        }
                    }
                }
            }
        }

        setRedraw(true);
    }

    @Override
    public final CursorStyle getCursorStyle()
    {
        return cursorStyle;
    }

    @Override
    public final void setCursorStyle(CursorStyle a_cursorStyle)
    {
        cursorStyle = a_cursorStyle;
    }

    @Override
    public final void setLineWidth(final LineWidth a_lineWidth)
    {
        for (int iColumn = 0; iColumn < logicalScreen.getWidth(); iColumn++)
        {
            if (iColumn > (logicalScreen.getWidth() / 2))
            {
                screenData.get(logicalScreen.getCursor().getRow())
                        .getCell(iColumn).reset();
            }
            screenData.get(logicalScreen.getCursor().getRow()).getCell(iColumn)
                    .setLineWidth(a_lineWidth);
        }
    }

    @Override
    public final void setLineHeight(final LineHeight a_height)
    {
        for (int iColumn = 0; iColumn < logicalScreen.getWidth(); iColumn++)
        {
            screenData.get(logicalScreen.getCursor().getRow()).getCell(iColumn)
                    .setLineHeight(a_height);
        }
    }

    @Override
    public final Cell getCurrentCell()
    {
        return screenData.get(logicalScreen.getCursor().getRow())
                .getCell(logicalScreen.getCursor().getColumn());
    }

    @Override
    public final CharacterSet getCharSetOnDisplay()
    {
        return charSetOnDisplay;
    }

    @Override
    public final void setCharSetOnDisplay(CharacterSet a_charSetOnDisplay)
    {
        charSetOnDisplay = a_charSetOnDisplay;
    }

    @Override
    public final CharacterSetSequence getCharSetsSequenceOnDisplay()
    {
        return charSetSequenceOnDisplay;
    }

    @Override
    public final void setCharSetsSequenceOnDisplay(
            CharacterSetSequence a_charSetsSequenceOnDisplay)
    {
        charSetSequenceOnDisplay = a_charSetsSequenceOnDisplay;
    }

    /**
     * @return the rendition that is currently being applied to cells
     */
    protected final Rendition getActiveRendition()
    {
        return activeRendition;
    }

    @Override
    public final void setScreenWidth(final int a_width)
    {
        if (allowWidthChange)
        {
            if (a_width != logicalScreen.getWidth())
            {
                logicalScreen.setWidth(a_width);
                logicalScreen.getCursor().home();

                screenData = new ArrayList<>();

                for (int iRow = 0; iRow < (logicalScreen.getHeight()
                        + 1); iRow++)
                {
                    screenData.add(
                            new ScreenRow(iRow, logicalScreen.getWidth() + 1));
                }

                drawScreen();
            }
        }
    }

    @Override
    public final int getSelectionStartColumn()
    {
        return selectionStartColumn;
    }

    @Override
    public final void setSelectionStartColumn(int a_selectionStartColumn)
    {
        selectionStartColumn = getColumnFromCoords(a_selectionStartColumn);
    }

    @Override
    public final int getSelectionStartRow()
    {
        return selectionStartRow;
    }

    @Override
    public final void setSelectionStartRow(int a_selectionStartRow)
    {
        selectionStartRow = getRowFromCoords(a_selectionStartRow);
    }

    @Override
    public final int getSelectionEndColumn()
    {
        return selectionEndColumn;
    }

    @Override
    public final void setSelectionEndColumn(int a_selectionEndColumn)
    {
        selectionEndColumn = getColumnFromCoords(a_selectionEndColumn);
    }

    @Override
    public final int getSelectionEndRow()
    {
        return selectionEndRow;
    }

    @Override
    public final void setSelectionEndRow(int a_selectionEndRow)
    {
        selectionEndRow = getRowFromCoords(a_selectionEndRow);
    }

    @Override
    public final int getRowFromCoords(final int a_y)
    {
        // The row number is the y coordinate value of the cursor divided by the
        // cell height in pixels
        final Rectangle clientArea = canvas.getClientArea();
        final float cellHeight = (float) clientArea.height
                / (float) logicalScreen.getHeight();

        return (int) (a_y / cellHeight);
    }

    @Override
    public final int getColumnFromCoords(final int a_x)
    {
        // The column number is the x coordinate value of the cursor divided by
        // the cell width in pixels
        final Rectangle clientArea = canvas.getClientArea();
        final float cellWidth = (float) clientArea.width
                / (float) logicalScreen.getWidth();

        return (int) (a_x / cellWidth);
    }

    @Override
    public final void processDataRead(byte[] a_Data)
    {
        processDataRead(a_Data, a_Data.length);
    }

    @Override
    public final void processDataRead(byte[] a_Data, int a_count)
    {
        if (dataScope != null)
        {
            dataScope.add(a_Data, a_count, Direction.RECEIVE);
        }
        parse(a_Data, a_count);
    }

    @Override
    public final boolean isReadOnly()
    {
        return readOnly;
    }

    @Override
    public final void setReadOnly(boolean a_readOnly)
    {
        readOnly = a_readOnly;
    }

    @Override
    public final boolean setFocus()
    {
        return canvas.setFocus();
    }

    @Override
    public final void setRedraw(final boolean a_redraw)
    {
        canvas.setRedraw(a_redraw);
    }

    @Override
    public final void fillScreen(final char a_char)
    {
        for (int iRow = 0; iRow < logicalScreen.getHeight(); iRow++)
        {
            for (int iColumn = 0; iColumn < logicalScreen.getWidth(); iColumn++)
            {
                final Cell cell = screenData.get(iRow).getCell(iColumn);
                cell.reset();
                cell.setCharacter(a_char);
            }
        }

        logicalScreen.getCursor().setColumn(0);
        logicalScreen.getCursor().setRow(0);
    }

    @Override
    public final void resetDevice()
    {
        setScreenWidth(DEFAULT_COLUMN_COUNT);
        fillScreen(' ');
        logicalScreen.getCursor().reset();
    }

    @Override
    public final boolean isInvisibleCursor()
    {
        return invisibleCursor;
    }

    @Override
    public final void setInvisibleCursor(boolean a_invisibleCursor)
    {
        invisibleCursor = a_invisibleCursor;
    }

    @Override
    public final boolean isLinefeed()
    {
        return linefeed;
    }

    @Override
    public final void setLinefeed(final boolean a_Linefeed)
    {
        linefeed = a_Linefeed;
    }

    /**
     * @return the screen measurements
     */
    public final LogicalScreen getLogicalScreen()
    {
        return logicalScreen;
    }

    /**
     * @param a_applicationKeys
     */
    public final void setApplicationCursorKeys(final boolean a_applicationKeys)
    {
        useApplicationCursorKeys = a_applicationKeys;
    }

    @Override
    public final void selectCharacterSet(final CharacterSetSequence a_sequence,
            final CharacterSet a_set)
    {
        addMessageToDataScope("\n+++\nCharacter set change\n    Was : "
                + charSetSequenceOnDisplay + ":" + charSetOnDisplay);

        charSetSequenceOnDisplay = a_sequence;
        charSetOnDisplay = a_set;

        addMessageToDataScope("\n    Now : " + charSetSequenceOnDisplay + ":"
                + charSetOnDisplay + "\n+++\n");
    }

    /**
     * @return true if the terminal will auto repeat when a key is pressed.
     */
    public final boolean isAutoRepeat()
    {
        return autoRepeat;
    }

    /**
     * @param a_autoRepeat
     */
    public final void setAutoRepeat(boolean a_autoRepeat)
    {
        autoRepeat = a_autoRepeat;
    }

    /**
     * @return true if the screen width is allowed to change between 80 and 132
     *         columns
     */
    public final boolean isAllowWidthChange()
    {
        return allowWidthChange;
    }

    /**
     * @param a_allowWidthChange
     */
    public final void setAllowWidthChange(boolean a_allowWidthChange)
    {
        allowWidthChange = a_allowWidthChange;
    }

    /**
     * @param a_dataScope
     */
    public final void setDataScope(final DataScope a_dataScope)
    {
        if (dataScope != null)
        {
            dataScope.close();
        }

        dataScope = a_dataScope;
    }

    /**
     * @param a_message
     */
    public final void addMessageToDataScope(final String a_message)
    {
        if (dataScope != null)
        {
            dataScope.addMessage(a_message);
        }
    }

    /**
     * @return the bsSendsDel
     */
    public final boolean isBsSendsDel()
    {
        return bsSendsDel;
    }

    /**
     * @param a_bsSendsDel the bsSendsDel to set
     */
    public final void setBsSendsDel(boolean a_bsSendsDel)
    {
        bsSendsDel = a_bsSendsDel;
    }
}
