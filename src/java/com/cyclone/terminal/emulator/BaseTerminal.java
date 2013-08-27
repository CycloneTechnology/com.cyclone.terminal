/**
 *
 */
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
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
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
    private LogicalScreen m_logicalScreen;

    private Composite m_composite;

    private Canvas m_canvas;

    private Menu m_menu;

    private Panel m_LEDPanel;

    private CLabel m_statusLine;

    private GC m_gc;

    private Font m_font;

    private Image m_image;

    private CursorStyle m_cursorStyle = CursorStyle.BLOCK;

    private Thread m_blinkThread;

    private CharSet m_charSet;

    private CharacterSetSequence m_charSetSequenceOnDisplay;

    private CharacterSet m_charSetOnDisplay;

    private Rendition m_ActiveRendition;

    private boolean m_haveSelection;

    private int m_selectionStartRow;

    private int m_selectionStartColumn;

    private int m_selectionEndRow;

    private int m_selectionEndColumn;

    private boolean m_readOnly;

    private boolean m_invisibleCursor;

    private boolean m_Linefeed;

    private List<ScreenRow> m_screenData;

    private boolean m_useApplicationCursorKeys;

    private boolean m_autoRepeat = true;

    private boolean m_keyDown; // = false;

    private boolean m_AllowWidthChange = true;

    private DataScope m_dataScope;

    private boolean m_bsSendsDel = true;

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
        m_logicalScreen = new LogicalScreen(a_width, a_height, a_history);

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
        m_charSetSequenceOnDisplay = CharacterSetSequence.G0;
        m_charSetOnDisplay = CharacterSet.USASCII;
        m_ActiveRendition = new Rendition();
        m_useApplicationCursorKeys = false;

        initializeCells();
        initializeEmulatorWindow(a_parent);
        initializeBlinking();

        m_readOnly = false;
    }

    private void initializeEmulatorWindow(final Composite a_parent)
    {
        createComposite(a_parent);

        m_charSet = new CharSet(getDisplay());

        m_gc = new GC(m_canvas);
        m_gc.setFont(m_font);
        m_gc.setBackground(new Color(getDisplay(),
                Colours.NORMAL[Colours.BACKGROUND_COLOUR_INDEX]));
        m_gc.setForeground(new Color(getDisplay(),
                Colours.NORMAL[Colours.FOREGROUND_COLOUR_INDEX]));
        m_gc.fillRectangle(m_canvas.getClientArea());

        m_canvas.addKeyListener(new KeyListener()
        {
            /**
             * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent a_e)
            {
                if (!m_keyDown || m_autoRepeat)
                {
                    if (!m_readOnly)
                    {
                        final ByteArrayOutputStream bb = new ByteArrayOutputStream();

                        final byte[] keyCode = translateKeyCode(a_e.keyCode,
                                a_e.character, m_useApplicationCursorKeys);
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
                                if (m_Linefeed)
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
                                if (m_bsSendsDel)
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

                        if (m_dataScope != null)
                        {
                            m_dataScope.add(bb.toByteArray(), bb.size(),
                                    Direction.SEND);
                        }

                        onTerminalData(bb.toByteArray());
                    }
                }

                m_keyDown = true;
            }

            @Override
            public void keyReleased(KeyEvent a_e)
            {
                m_keyDown = false;
            }

        });

        m_canvas.addControlListener(new ControlListener()
        {
            @Override
            public void controlResized(ControlEvent a_e)
            {
                m_composite.redraw();
            }

            @Override
            public void controlMoved(ControlEvent a_e)
            {
                m_composite.redraw();
            }
        });

        m_canvas.addPaintListener(new PaintListener()
        {
            @Override
            public void paintControl(PaintEvent a_e)
            {
                redrawScreen();
            }
        });

        enableMenu(true);

        // Set the active position to row 0, column 0 (the home position)
        m_logicalScreen.getCursor().home();

        drawScreen();
    }

    /**
     * This method initializes m_composite
     * 
     * @param a_parent
     */
    private void createComposite(final Composite a_parent)
    {
        // m_composite = new Composite(a_parent, SWT.NONE);
        // m_composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
        // true));
        m_composite = a_parent;

        final GridLayout compositeGridLayout = new GridLayout();
        compositeGridLayout.numColumns = 5;
        compositeGridLayout.horizontalSpacing = 0;
        compositeGridLayout.verticalSpacing = 0;
        compositeGridLayout.marginWidth = 0;
        compositeGridLayout.marginHeight = 0;
        compositeGridLayout.makeColumnsEqualWidth = false;
        m_composite.setLayout(compositeGridLayout);

        m_composite.addDisposeListener(new DisposeListener()
        {
            /**
             * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
             */
            @Override
            public void widgetDisposed(DisposeEvent a_e)
            {
                m_blinkThread.interrupt();
                try
                {
                    m_blinkThread.join();
                }
                catch (InterruptedException e)
                {

                }
                m_blinkThread = null;
            }

        });

        final GridData canvasGridData = new GridData(SWT.FILL, SWT.FILL, true,
                true);
        canvasGridData.horizontalSpan = 5;
        m_canvas = new Canvas(m_composite, SWT.NO_BACKGROUND);
        m_canvas.setLayoutData(canvasGridData);

        m_font = new Font(getDisplay(), "fixed", 120, SWT.NONE);
        m_canvas.setFont(m_font);
        m_canvas.setBackground(new Color(getDisplay(),
                Colours.NORMAL[Colours.BACKGROUND_COLOUR_INDEX]));
        m_canvas.setForeground(new Color(getDisplay(),
                Colours.NORMAL[Colours.FOREGROUND_COLOUR_INDEX]));

        m_statusLine = new CLabel(m_composite, SWT.NONE);
        m_statusLine.setText("Online");
        m_statusLine
                .setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        m_LEDPanel = new Panel(m_composite, 4);

        m_LEDPanel.refresh();
    }

    private void initializeCells()
    {
        m_screenData = new ArrayList<>();

        for (int iRow = 0; iRow < (m_logicalScreen.getHeight() + m_logicalScreen
                .getHistory()); iRow++)
        {
            m_screenData.add(new ScreenRow(iRow, m_logicalScreen.getWidth()));
        }
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getLEDPanel()
     */
    @Override
    public final Panel getLEDPanel()
    {
        return m_LEDPanel;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#showLEDPanel(boolean)
     */
    @Override
    public final void showLEDPanel(final boolean a_show)
    {
        if (!isDisposed())
        {
            m_LEDPanel.show(a_show);
            m_statusLine.pack();
        }
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setStatus(java.lang.String)
     */
    @Override
    public final void setStatus(final String a_status)
    {
        if (!isDisposed())
        {
            m_statusLine.setText(a_status);
        }
    }

    /**
     * @return true if the display widget the tre minal is using has been
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

    /**
     * @see com.cyclone.terminal.emulator.Terminal#showStatus(boolean)
     */
    @Override
    public final void showStatus(final boolean a_show)
    {
        if (!isDisposed())
        {
            m_statusLine.setVisible(a_show);
            m_statusLine.pack();
        }
    }

    /**
     * Ensure that our start selection is always before our end selection
     */
    private void reorderSelection()
    {
        if (m_selectionEndRow < m_selectionStartRow)
        {
            int tmp;

            tmp = m_selectionStartRow;
            m_selectionStartRow = m_selectionEndRow;
            m_selectionEndRow = tmp;

            tmp = m_selectionStartColumn;
            m_selectionStartColumn = m_selectionEndColumn;
            m_selectionEndColumn = tmp;
        }
        else
        {
            if (m_selectionEndRow == m_selectionStartRow)
            {
                if (m_selectionEndColumn < m_selectionStartColumn)
                {
                    final int tmp = m_selectionStartColumn;
                    m_selectionStartColumn = m_selectionEndColumn;
                    m_selectionEndColumn = tmp;
                }
            }
        }
    }

    private String getSelectedText()
    {
        final StringBuilder selection = new StringBuilder();

        for (int iRow = m_selectionStartRow; iRow <= m_selectionEndRow; iRow++)
        {
            final StringBuilder sb = new StringBuilder();
            if ((iRow == m_selectionStartRow) && (iRow == m_selectionEndRow))
            {
                // All on one row, so get the characters...
                for (int iCol = m_selectionStartColumn; iCol <= m_selectionEndColumn; iCol++)
                {
                    sb.append(m_screenData.get(iRow).getCell(iCol)
                            .getCharacter());
                }
                selection.append(sb.toString().trim());
            }
            else
            {
                // Multiple rows...
                if (iRow == m_selectionStartRow)
                {
                    for (int iCol = m_selectionStartColumn; iCol < m_logicalScreen
                            .getWidth(); iCol++)
                    {
                        sb.append(m_screenData.get(iRow).getCell(iCol)
                                .getCharacter());
                    }
                    selection.append(sb.toString().trim());
                    selection.append("\r\n");
                }
                else
                {
                    if (iRow == m_selectionEndRow)
                    {
                        for (int iCol = 0; iCol <= m_selectionEndRow; iCol++)
                        {
                            sb.append(m_screenData.get(iRow).getCell(iCol)
                                    .getCharacter());
                        }
                        selection.append(sb.toString().trim());
                    }
                    else
                    {
                        for (int iCol = 0; iCol < m_logicalScreen.getWidth(); iCol++)
                        {
                            sb.append(m_screenData.get(iRow).getCell(iCol)
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

    /**
     * @see com.cyclone.terminal.emulator.Terminal#enableMenu(boolean)
     */
    @Override
    public final void enableMenu(final boolean a_enable)
    {
        if (a_enable)
        {
            if (m_menu == null)
            {
                final Clipboard cb = new Clipboard(getDisplay());

                m_menu = new Menu(m_composite.getShell(), SWT.POP_UP);

                final MenuItem copyItem = new MenuItem(m_menu, SWT.PUSH);
                copyItem.setText("Copy");
                copyItem.addListener(SWT.Selection, new Listener()
                {
                    /**
                     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
                     */
                    @Override
                    public void handleEvent(Event a_event)
                    {
                        final String textData = getSelectedText();

                        final TextTransfer textTransfer = TextTransfer
                                .getInstance();
                        cb.setContents(new Object[]
                        {textData}, new Transfer[]
                        {textTransfer});
                    }
                });

                final MenuItem pasteItem = new MenuItem(m_menu, SWT.PUSH);
                pasteItem.setText("Paste");
                pasteItem.addListener(SWT.Selection, new Listener()
                {
                    /**
                     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
                     */
                    @Override
                    public void handleEvent(Event a_event)
                    {
                        final TextTransfer transfer = TextTransfer
                                .getInstance();
                        final String data = (String) cb.getContents(transfer);
                        if (data != null)
                        {
                            onTerminalData(data.getBytes());
                        }
                    }
                });

                m_menu.addMenuListener(new MenuAdapter()
                {
                    /**
                     * @see org.eclipse.swt.events.MenuAdapter#menuShown(org.eclipse.swt.events.MenuEvent)
                     */
                    @Override
                    public void menuShown(MenuEvent a_event)
                    {
                        // is copy valid?
                        final String selection = getSelectedText();
                        copyItem.setEnabled(selection.length() > 0);

                        // is paste valid?
                        boolean enabled = false;
                        if (!m_readOnly)
                        {
                            final TransferData[] available = cb
                                    .getAvailableTypes();
                            for (TransferData element : available)
                            {
                                if (TextTransfer.getInstance().isSupportedType(
                                        element))
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
                private boolean m_inDrag;

                /**
                 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
                 */
                @Override
                public void handleEvent(Event a_event)
                {
                    switch (a_event.type)
                    {
                        case SWT.MouseDown:
                            if (a_event.button == 1)
                            {
                                m_inDrag = true;

                                // Save the drag start position, i.e. row and
                                // column...
                                setSelectionStartRow(a_event.y);
                                setSelectionStartColumn(a_event.x);

                                setSelectionEndRow(a_event.y);
                                setSelectionEndColumn(a_event.x);

                                m_haveSelection = true;

                                drawScreen();
                            }
                            break;
                        case SWT.MouseMove:
                            // We dont get the button number set when the mouse
                            // move event is given to us, so, we only need to do
                            // anything here if we are currently in a drag
                            // operation.
                            if (m_inDrag)
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
                            if ((a_event.button == 1) && m_inDrag)
                            {
                                // stop the selection and save the final cell
                                // position i.e. row and column.
                                setSelectionEndRow(a_event.y);
                                setSelectionEndColumn(a_event.x);

                                reorderSelection();

                                if ((getSelectionEndColumn() == getSelectionStartColumn())
                                        && (getSelectionStartRow() == getSelectionEndRow()))
                                {
                                    m_haveSelection = false;
                                }

                                m_inDrag = false;

                                drawScreen();
                            }
                            break;
                        default:
                            break;
                    }
                }
            };

            m_canvas.addListener(SWT.MouseDown, listener);
            m_canvas.addListener(SWT.MouseMove, listener);
            m_canvas.addListener(SWT.MouseUp, listener);

            m_canvas.setMenu(m_menu);
        }
        else
        {
            m_canvas.setMenu(null);
        }
    }

    private void initializeBlinking()
    {
        m_blinkThread = new Thread()
        {
            /**
             * @see java.lang.Thread#run()
             */
            @Override
            public void run()
            {
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
                            getDisplay().asyncExec(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    for (int iRow = 0; iRow < m_logicalScreen
                                            .getHeight(); iRow++)
                                    {
                                        for (int iColumn = 0; iColumn < m_logicalScreen
                                                .getWidth(); iColumn++)
                                        {
                                            final Cell cell = m_screenData.get(
                                                    iRow).getCell(iColumn);

                                            // is this cell the one under the
                                            // cursor?
                                            if ((iRow == m_logicalScreen
                                                    .getCursor().getRow())
                                                    && (iColumn == m_logicalScreen
                                                            .getCursor()
                                                            .getColumn()))
                                            {
                                                if (!m_invisibleCursor)
                                                {
                                                    // Blink the cursor...
                                                    if (m_logicalScreen
                                                            .cursorInBounds())
                                                    {
                                                        switch (m_cursorStyle)
                                                        {
                                                            default:
                                                            case BLOCK:
                                                                drawCell(cell,
                                                                        false,
                                                                        on);
                                                                break;
                                                            case UNDERLINE:
                                                                drawCell(cell,
                                                                        false,
                                                                        on);
                                                                break;
                                                        }
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                if (cell.getRendition()
                                                        .isBlink())
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
                                }
                            });
                        }
                        sleep(500);
                        blinkOn = !blinkOn;
                    }
                }
                catch (InterruptedException e)
                {

                }
            }
        };

        m_blinkThread.start();
    }

    /**
     * @return the display
     */
    protected final Display getDisplay()
    {
        return m_composite.getDisplay();
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getParent()
     */
    @Override
    public final Composite getParent()
    {
        return m_composite.getParent();
    }

    private Image getImage()
    {
        if (m_image == null)
        {
            final Point charSize = m_charSet.getCharSize();
            m_image = new Image(getDisplay(), charSize.x
                    * m_logicalScreen.getWidth(), charSize.y
                    * m_logicalScreen.getHeight());

            final GC imgGC = new GC(getImage());
            try
            {
                imgGC.setBackground(new Color(getDisplay(),
                        Colours.NORMAL[Colours.BACKGROUND_COLOUR_INDEX]));
                imgGC.fillRectangle(0, 0,
                        charSize.x * m_logicalScreen.getWidth(), charSize.y
                                * m_logicalScreen.getHeight());
            }
            finally
            {
                imgGC.dispose();
            }
        }

        return m_image;
    }

    private void redrawScreen()
    {
        final Rectangle clientRect = m_canvas.getClientArea();

        m_gc.drawImage(getImage(), 0, 0, getImage().getBounds().width,
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
        if (m_image != null)
        {
            m_image.dispose();
            m_image = null;
        }

        // Draw each cell on the screen, ignore any cells that have a rendition
        // that is the default in order to improve performance especially when
        // scrolling.
        for (int iRow = 0; iRow < m_logicalScreen.getHeight(); iRow++)
        {
            for (int iColumn = 0; iColumn < m_logicalScreen.getWidth(); iColumn++)
            {
                final Cell cell = m_screenData.get(iRow).getCell(iColumn);
                final boolean doDraw;
                if (cell.getCharacter() != 32)
                {
                    doDraw = true;
                }
                else
                {
                    if (!cell.getRendition().isPlain())
                    {
                        doDraw = true;
                    }
                    else
                    {
                        doDraw = false;
                    }
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

        if (m_haveSelection)
        {
            // Check the rows, is this cell on one of the selected rows?
            if ((a_cell.getRow() >= m_selectionStartRow)
                    && (a_cell.getRow() <= m_selectionEndRow))
            {
                // Check to see if the column for the cell is in the selected
                // area,
                // the first and last row of the selected area are special
                // because
                // they may not start or finish at the beginning or end of the
                // row
                // respectively
                if (a_cell.getRow() == m_selectionStartRow)
                {
                    // On the first row, is our column after the selection start
                    // column?
                    if (a_cell.getColumn() >= m_selectionStartColumn)
                    {
                        // But is this our end row too?
                        if (a_cell.getRow() == m_selectionEndRow)
                        {
                            // Last row, is our column before the end column?
                            if (a_cell.getColumn() <= m_selectionEndColumn)
                            {
                                selected = true;
                            }
                            else
                            {
                                selected = false;
                            }
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
                    if (a_cell.getRow() == m_selectionEndRow)
                    {
                        // Last row, is our column before the end column?
                        if (a_cell.getColumn() <= m_selectionEndColumn)
                        {
                            selected = true;
                        }
                        else
                        {
                            selected = false;
                        }
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

        if (m_haveSelection)
        {
            for (int iRow = m_selectionStartRow; iRow <= m_selectionEndRow; iRow++)
            {
                if (iRow == m_selectionStartRow)
                {
                    if (iRow == m_selectionEndRow)
                    {
                        for (int iCol = m_selectionStartColumn; iCol <= m_selectionEndColumn; iCol++)
                        {
                            drawCell(m_screenData.get(iRow).getCell(iCol),
                                    false, BlinkState.OFF);
                        }
                    }
                    else
                    {
                        for (int iCol = m_selectionStartColumn; iCol < m_logicalScreen
                                .getWidth(); iCol++)
                        {
                            drawCell(m_screenData.get(iRow).getCell(iCol),
                                    false, BlinkState.OFF);
                        }
                    }
                }
                else
                {
                    if (iRow == m_selectionEndRow)
                    {
                        for (int iCol = 0; iCol < m_selectionEndColumn; iCol++)
                        {
                            drawCell(m_screenData.get(iRow).getCell(iCol),
                                    false, BlinkState.OFF);
                        }
                    }
                    else
                    {
                        for (int iCol = 0; iCol < m_logicalScreen.getWidth(); iCol++)
                        {
                            drawCell(m_screenData.get(iRow).getCell(iCol),
                                    false, BlinkState.OFF);
                        }
                    }
                }
            }
        }

        setRedraw(true);

        m_canvas.getDisplay().readAndDispatch();
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
        if (!m_canvas.isDisposed())
        {
            // Set this cell to use the active rendition
            if (a_useActiveRendition)
            {
                a_cell.setRendition(m_ActiveRendition);
            }

            final GC imgGC = new GC(getImage());
            try
            {
                final Point charSize = m_charSet.getCharSize();

                final boolean selected = isCellSelected(a_cell);

                try
                {
                    int cellX;
                    int cellY;
                    final Image cellImage = m_charSet.getImage(a_cell,
                            selected, a_blinkState);

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
                                            cellImage.getBounds().height,
                                            cellX, cellY, charSize.x,
                                            charSize.y);
                                    break;
                                case DOUBLE:
                                    cellX = charSize.x * 2 * a_cell.getColumn();
                                    cellY = charSize.y * a_cell.getRow();
                                    imgGC.drawImage(cellImage, 0, 0,
                                            cellImage.getBounds().width,
                                            cellImage.getBounds().height,
                                            cellX, cellY, charSize.x * 2,
                                            charSize.y);
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

                m_canvas.redraw();
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
        if (!m_canvas.isDisposed())
        {
            final Cell cell = m_screenData.get(
                    m_logicalScreen.getCursor().getRow()).getCell(
                    m_logicalScreen.getCursor().getColumn());
            cell.setCharacter(a_ch);
            // System.out.println("Cell at ("
            // + m_logicalScreen.getCursorPosition().getRow() + ","
            // + m_logicalScreen.getCursorPosition().getColumn()
            // + ") using set " + m_charSetSequenceOnDisplay + ":"
            // + m_charSetOnDisplay);
            cell.setCharacterSetSequence(getCharSetsSequenceOnDisplay());
            cell.setCharacterSet(getCharSetOnDisplay());

            drawCell(cell, true, BlinkState.OFF);

            m_logicalScreen.getCursor().right();
        }
    }

    /**
     * Perform a linefeed on the display
     */
    protected final void performLF()
    {
        setRedraw(false);

        drawCell(m_screenData.get(m_logicalScreen.getCursor().getRow())
                .getCell(m_logicalScreen.getCursor().getColumn()), false,
                BlinkState.OFF);

        final Scroll scroll;
        if (m_Linefeed)
        {
            scroll = m_logicalScreen.getCursor().down();
            m_logicalScreen.getCursor().setColumn(0);
        }
        else
        {
            scroll = m_logicalScreen.getCursor().down();
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
        drawCell(m_screenData.get(m_logicalScreen.getCursor().getRow())
                .getCell(m_logicalScreen.getCursor().getColumn()), false,
                BlinkState.OFF);

        m_logicalScreen.getCursor().setColumn(0);
    }

    /**
     * perform a backspace on the display
     */
    protected final void performBS()
    {
        m_logicalScreen.getCursor().left();
    }

    protected final void performTAB()
    {
        m_logicalScreen.getCursor().tab();
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

        m_screenData.remove(topMargin);

        m_screenData.add(
                bottomMargin,
                new ScreenRow(m_logicalScreen.getHeight(), m_logicalScreen
                        .getWidth() + 1));

        for (int iRow = topMargin; iRow <= bottomMargin; iRow++)
        {
            m_screenData.get(iRow).setRow(iRow);
        }

        // If there is a selection, adjust its start and end row too...
        if (m_haveSelection)
        {
            final Rectangle clientArea = m_canvas.getClientArea();
            final int cellHeight = clientArea.height
                    / m_logicalScreen.getHeight();

            m_selectionStartRow -= cellHeight;
            m_selectionEndRow -= cellHeight;

            if (m_selectionStartRow < 0)
            {
                m_selectionStartRow = 0;
                m_selectionEndRow = 0;
                m_selectionStartColumn = 0;
                m_selectionEndColumn = 0;

                m_haveSelection = false;
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
                        if (m_logicalScreen.cursorInBounds(iRow, iColumn))
                        {
                            m_screenData.get(iRow).getCell(iColumn).reset();
                            drawCell(m_screenData.get(iRow).getCell(iColumn),
                                    false, BlinkState.OFF);
                        }
                    }
                }
                else
                {
                    for (int iColumn = a_startColumn; iColumn <= m_logicalScreen
                            .getWidth(); iColumn++)
                    {
                        if (m_logicalScreen.cursorInBounds(iRow, iColumn))
                        {
                            m_screenData.get(iRow).getCell(iColumn).reset();
                            drawCell(m_screenData.get(iRow).getCell(iColumn),
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
                            if (m_logicalScreen.cursorInBounds(iRow, iColumn))
                            {
                                m_screenData.get(iRow).getCell(iColumn).reset();
                                drawCell(m_screenData.get(iRow)
                                        .getCell(iColumn), false,
                                        BlinkState.OFF);
                            }
                        }
                    }
                    else
                    {
                        // On the last row, erase from the first column up until
                        // the final column
                        for (int iColumn = 0; iColumn <= a_endColumn; iColumn++)
                        {
                            if (m_logicalScreen.cursorInBounds(iRow, iColumn))
                            {
                                m_screenData.get(iRow).getCell(iColumn).reset();
                                drawCell(m_screenData.get(iRow)
                                        .getCell(iColumn), false,
                                        BlinkState.OFF);
                            }
                        }
                    }
                }
                else
                {
                    // An intermediate row, erase the entire row...
                    for (int iColumn = 0; iColumn <= m_logicalScreen.getWidth(); iColumn++)
                    {
                        if (m_logicalScreen.cursorInBounds(iRow, iColumn))
                        {
                            m_screenData.get(iRow).getCell(iColumn).reset();
                            drawCell(m_screenData.get(iRow).getCell(iColumn),
                                    false, BlinkState.OFF);
                        }
                    }
                }
            }
        }

        setRedraw(true);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getCursorStyle()
     */
    @Override
    public final CursorStyle getCursorStyle()
    {
        return m_cursorStyle;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setCursorStyle(com.cyclone.terminal.emulator.CursorStyle)
     */
    @Override
    public final void setCursorStyle(CursorStyle a_cursorStyle)
    {
        m_cursorStyle = a_cursorStyle;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setLineWidth(com.cyclone.terminal.emulator.cell.LineWidth)
     */
    @Override
    public final void setLineWidth(final LineWidth a_lineWidth)
    {
        for (int iColumn = 0; iColumn < m_logicalScreen.getWidth(); iColumn++)
        {
            if (iColumn > (m_logicalScreen.getWidth() / 2))
            {
                m_screenData.get(m_logicalScreen.getCursor().getRow())
                        .getCell(iColumn).reset();
            }
            m_screenData.get(m_logicalScreen.getCursor().getRow())
                    .getCell(iColumn).setLineWidth(a_lineWidth);
        }
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setLineHeight(com.cyclone.terminal.emulator.cell.LineHeight)
     */
    @Override
    public final void setLineHeight(final LineHeight a_height)
    {
        for (int iColumn = 0; iColumn < m_logicalScreen.getWidth(); iColumn++)
        {
            m_screenData.get(m_logicalScreen.getCursor().getRow())
                    .getCell(iColumn).setLineHeight(a_height);
        }
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getCurrentCell()
     */
    @Override
    public final Cell getCurrentCell()
    {
        return m_screenData.get(m_logicalScreen.getCursor().getRow()).getCell(
                m_logicalScreen.getCursor().getColumn());
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getCharSetOnDisplay()
     */
    @Override
    public final CharacterSet getCharSetOnDisplay()
    {
        return m_charSetOnDisplay;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setCharSetOnDisplay(com.cyclone.terminal.emulator.CharacterSet)
     */
    @Override
    public final void setCharSetOnDisplay(CharacterSet a_charSetOnDisplay)
    {
        m_charSetOnDisplay = a_charSetOnDisplay;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getCharSetsSequenceOnDisplay()
     */
    @Override
    public final CharacterSetSequence getCharSetsSequenceOnDisplay()
    {
        return m_charSetSequenceOnDisplay;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setCharSetsSequenceOnDisplay(com.cyclone.terminal.emulator.CharacterSetSequence)
     */
    @Override
    public final void setCharSetsSequenceOnDisplay(
            CharacterSetSequence a_charSetsSequenceOnDisplay)
    {
        m_charSetSequenceOnDisplay = a_charSetsSequenceOnDisplay;
    }

    /**
     * @return the rendition that is currently being applied to cells
     */
    protected final Rendition getActiveRendition()
    {
        return m_ActiveRendition;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setScreenWidth(int)
     */
    @Override
    public final void setScreenWidth(final int a_width)
    {
        if (m_AllowWidthChange)
        {
            if (a_width != m_logicalScreen.getWidth())
            {
                m_logicalScreen.setWidth(a_width);
                m_logicalScreen.getCursor().home();

                m_screenData = new ArrayList<>();

                for (int iRow = 0; iRow < (m_logicalScreen.getHeight() + 1); iRow++)
                {
                    m_screenData.add(new ScreenRow(iRow, m_logicalScreen
                            .getWidth() + 1));
                }

                drawScreen();
            }
        }
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getSelectionStartColumn()
     */
    @Override
    public final int getSelectionStartColumn()
    {
        return m_selectionStartColumn;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setSelectionStartColumn(int)
     */
    @Override
    public final void setSelectionStartColumn(int a_selectionStartColumn)
    {
        m_selectionStartColumn = getColumnFromCoords(a_selectionStartColumn);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getSelectionStartRow()
     */
    @Override
    public final int getSelectionStartRow()
    {
        return m_selectionStartRow;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setSelectionStartRow(int)
     */
    @Override
    public final void setSelectionStartRow(int a_selectionStartRow)
    {
        m_selectionStartRow = getRowFromCoords(a_selectionStartRow);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getSelectionEndColumn()
     */
    @Override
    public final int getSelectionEndColumn()
    {
        return m_selectionEndColumn;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setSelectionEndColumn(int)
     */
    @Override
    public final void setSelectionEndColumn(int a_selectionEndColumn)
    {
        m_selectionEndColumn = getColumnFromCoords(a_selectionEndColumn);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getSelectionEndRow()
     */
    @Override
    public final int getSelectionEndRow()
    {
        return m_selectionEndRow;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setSelectionEndRow(int)
     */
    @Override
    public final void setSelectionEndRow(int a_selectionEndRow)
    {
        m_selectionEndRow = getRowFromCoords(a_selectionEndRow);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getRowFromCoords(int)
     */
    @Override
    public final int getRowFromCoords(final int a_y)
    {
        // The row number is the y coordinate value of the cursor divided by the
        // cell height in pixels
        final Rectangle clientArea = m_canvas.getClientArea();
        final float cellHeight = (float) clientArea.height
                / (float) m_logicalScreen.getHeight();

        return (int) (a_y / cellHeight);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#getColumnFromCoords(int)
     */
    @Override
    public final int getColumnFromCoords(final int a_x)
    {
        // The column number is the x coordinate value of the cursor divided by
        // the cell width in pixels
        final Rectangle clientArea = m_canvas.getClientArea();
        final float cellWidth = (float) clientArea.width
                / (float) m_logicalScreen.getWidth();

        return (int) (a_x / cellWidth);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#processDataRead(byte[])
     */
    @Override
    public final void processDataRead(byte[] a_Data)
    {
        processDataRead(a_Data, a_Data.length);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#processDataRead(byte[],
     *      int)
     */
    @Override
    public final void processDataRead(byte[] a_Data, int a_count)
    {
        if (m_dataScope != null)
        {
            m_dataScope.add(a_Data, a_count, Direction.RECEIVE);
        }
        parse(a_Data, a_count);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#isReadOnly()
     */
    @Override
    public final boolean isReadOnly()
    {
        return m_readOnly;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setReadOnly(boolean)
     */
    @Override
    public final void setReadOnly(boolean a_readOnly)
    {
        m_readOnly = a_readOnly;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setFocus()
     */
    @Override
    public final boolean setFocus()
    {
        return m_canvas.setFocus();
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setRedraw(boolean)
     */
    @Override
    public final void setRedraw(final boolean a_redraw)
    {
        m_canvas.setRedraw(a_redraw);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#fillScreen(char)
     */
    @Override
    public final void fillScreen(final char a_char)
    {
        for (int iRow = 0; iRow < m_logicalScreen.getHeight(); iRow++)
        {
            for (int iColumn = 0; iColumn < m_logicalScreen.getWidth(); iColumn++)
            {
                final Cell cell = m_screenData.get(iRow).getCell(iColumn);
                cell.reset();
                cell.setCharacter(a_char);
            }
        }

        m_logicalScreen.getCursor().setColumn(0);
        m_logicalScreen.getCursor().setRow(0);
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#resetDevice()
     */
    @Override
    public final void resetDevice()
    {
        setScreenWidth(DEFAULT_COLUMN_COUNT);
        fillScreen(' ');
        m_logicalScreen.getCursor().reset();
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#isInvisibleCursor()
     */
    @Override
    public final boolean isInvisibleCursor()
    {
        return m_invisibleCursor;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setInvisibleCursor(boolean)
     */
    @Override
    public final void setInvisibleCursor(boolean a_invisibleCursor)
    {
        m_invisibleCursor = a_invisibleCursor;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#isLinefeed()
     */
    @Override
    public final boolean isLinefeed()
    {
        return m_Linefeed;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#setLinefeed(boolean)
     */
    @Override
    public final void setLinefeed(final boolean a_Linefeed)
    {
        m_Linefeed = a_Linefeed;
    }

    /**
     * @return the screen measurements
     */
    public final LogicalScreen getLogicalScreen()
    {
        return m_logicalScreen;
    }

    /**
     * @param a_applicationKeys
     */
    public final void setApplicationCursorKeys(final boolean a_applicationKeys)
    {
        m_useApplicationCursorKeys = a_applicationKeys;
    }

    /**
     * @see com.cyclone.terminal.emulator.Terminal#selectCharacterSet(com.cyclone.terminal.emulator.CharacterSetSequence,
     *      com.cyclone.terminal.emulator.CharacterSet)
     */
    @Override
    public final void selectCharacterSet(final CharacterSetSequence a_sequence,
            final CharacterSet a_set)
    {
        addMessageToDataScope("\n+++\nCharacter set change\n    Was : "
                + m_charSetSequenceOnDisplay + ":" + m_charSetOnDisplay);

        m_charSetSequenceOnDisplay = a_sequence;
        m_charSetOnDisplay = a_set;

        addMessageToDataScope("\n    Now : " + m_charSetSequenceOnDisplay + ":"
                + m_charSetOnDisplay + "\n+++\n");
    }

    /**
     * @return true if the terminal will auto repeat when a key is pressed.
     */
    public final boolean isAutoRepeat()
    {
        return m_autoRepeat;
    }

    /**
     * @param a_autoRepeat
     */
    public final void setAutoRepeat(boolean a_autoRepeat)
    {
        m_autoRepeat = a_autoRepeat;
    }

    /**
     * @return true if the screen width is allowed to change between 80 and 132
     *         columns
     */
    public final boolean isAllowWidthChange()
    {
        return m_AllowWidthChange;
    }

    /**
     * @param a_allowWidthChange
     */
    public final void setAllowWidthChange(boolean a_allowWidthChange)
    {
        m_AllowWidthChange = a_allowWidthChange;
    }

    public final void setDataScope(final DataScope a_dataScope)
    {
        if (m_dataScope != null)
        {
            m_dataScope.close();
        }

        m_dataScope = a_dataScope;
    }

    public final void addMessageToDataScope(final String a_message)
    {
        if (m_dataScope != null)
        {
            m_dataScope.addMessage(a_message);
        }
    }

    /**
     * @return the bsSendsDel
     */
    public final boolean isBsSendsDel()
    {
        return m_bsSendsDel;
    }

    /**
     * @param a_bsSendsDel the bsSendsDel to set
     */
    public final void setBsSendsDel(boolean a_bsSendsDel)
    {
        m_bsSendsDel = a_bsSendsDel;
    }
}
