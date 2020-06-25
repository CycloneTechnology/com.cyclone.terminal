package com.cyclone.terminal.emulator;

import org.eclipse.swt.widgets.Composite;

import com.cyclone.terminal.emulator.cell.Cell;
import com.cyclone.terminal.emulator.cell.LineHeight;
import com.cyclone.terminal.emulator.cell.LineWidth;
import com.cyclone.terminal.emulator.led.Panel;

/**
 * @author Phil.Baxter
 *
 */
public interface Terminal
{
    /**
     * The default number of columns for the terminal
     */
    int DEFAULT_COLUMN_COUNT = 80;

    /**
     * The max number of columns for the terminal
     */
    int MAX_COLUMN_COUNT = 132;

    /**
     * The default number of rows for the terminal
     */
    int DEFAULT_ROW_COUNT = 24;

    /**
     * The max number of rows for the terminal
     */
    int MAX_ROW_COUNT = 24;

    /**
     * The Escape character
     */
    char ESC = 27;

    /**
     * Default history lines
     */
    int DEFAULT_HISTORY = 0;

    /**
     * @return the panel containing the LED's
     */
    Panel getLEDPanel();

    /**
     * Show or hide the LED panel
     *
     * @param a_show
     */
    void showLEDPanel(final boolean a_show);

    /**
     * Set the status line text.
     *
     * @param a_status
     */
    void setStatus(final String a_status);

    /**
     * Show or hide the status text
     *
     * @param a_show
     */
    void showStatus(final boolean a_show);

    /**
     * Enable or disable the popup context menu
     *
     * @param a_enable
     */
    void enableMenu(final boolean a_enable);

    /**
     * @return the parent window
     */
    Composite getParent();

    /**
     * @return the style of the current cursor
     */
    CursorStyle getCursorStyle();

    /**
     * @param a_cursorStyle
     */
    void setCursorStyle(CursorStyle a_cursorStyle);

    /**
     * Sets the current active row to be double width, any cells that flow off
     * the edge of the screen will be reset
     *
     * @param a_lineWidth
     */
    void setLineWidth(final LineWidth a_lineWidth);

    /**
     * Sets the current active row to be double height displaying either the top
     * or bottom half of the character.
     *
     * @param a_height
     */
    void setLineHeight(final LineHeight a_height);

    /**
     * @return the currently active cell
     */
    Cell getCurrentCell();

    /**
     * @return the character sets sequence being used to display
     */
    CharacterSetSequence getCharSetsSequenceOnDisplay();

    /**
     * Set the character sets sequence that should be used for character display
     *
     * @param a_charSetsSequenceOnDisplay
     */
    void setCharSetsSequenceOnDisplay(
            CharacterSetSequence a_charSetsSequenceOnDisplay);

    /**
     * @return the character set being used to display
     */
    CharacterSet getCharSetOnDisplay();

    /**
     * Set the character set that should be used for character display
     *
     * @param a_charSetOnDisplay
     */
    void setCharSetOnDisplay(CharacterSet a_charSetOnDisplay);

    /**
     * Set the width of the screen.
     *
     * @param a_width
     */
    void setScreenWidth(final int a_width);

    /**
     * @return the start column for a selected area
     */
    int getSelectionStartColumn();

    /**
     * @param a_selectionStartX
     */
    void setSelectionStartColumn(int a_selectionStartX);

    /**
     * @return the start row for a selected area
     */
    int getSelectionStartRow();

    /**
     * @param a_selectionStartY
     */
    void setSelectionStartRow(int a_selectionStartY);

    /**
     * @return the end column for a selected area
     */
    int getSelectionEndColumn();

    /**
     * @param a_selectionEndX
     */
    void setSelectionEndColumn(int a_selectionEndX);

    /**
     * @return the end row for a selected area
     */
    int getSelectionEndRow();

    /**
     * @param a_selectionEndY
     */
    void setSelectionEndRow(int a_selectionEndY);

    /**
     * Obtain the row for a given y coordinate.
     *
     * @param a_y
     * @return the row number
     */
    int getRowFromCoords(final int a_y);

    /**
     * Obtain the column for a given x coordinate.
     *
     * @param a_x
     * @return the column number
     */
    int getColumnFromCoords(final int a_x);

    /**
     * Parses data read from the remote device
     *
     * @param a_Data the data
     * @param a_count number of bytes of real data to be processed in the a_data
     *            array
     */
    void processDataRead(byte[] a_Data, int a_count);

    /**
     * Parses data read from the remote device
     *
     * @param a_Data the data
     */
    void processDataRead(byte[] a_Data);

    /**
     * Set the terminal into read-only mode, characters cannot be entered by the
     * user or pasted into the terminal, however copy from the terminal will
     * still work correctly.
     *
     * @param a_readOnly
     */
    void setReadOnly(boolean a_readOnly);

    /**
     * @return true if the terminal is in read-only mode
     */
    boolean isReadOnly();

    /**
     * Set the focus in the emulator window.
     *
     * @return true if focus was granted
     */
    boolean setFocus();

    /**
     * Fill the screen with the specified character
     *
     * @param a_char
     */
    void fillScreen(final char a_char);

    /**
     * Reset the terminal device settings to default
     */
    void resetDevice();

    /**
     * @return true if the cursor is currently invisible
     */
    boolean isInvisibleCursor();

    /**
     * Sets the cursor visibility.
     *
     * @param a_invisibleCursor
     */
    void setInvisibleCursor(boolean a_invisibleCursor);

    /**
     * @return true if we should send Linefeed only, false if we should send
     *         Linefeed/Carriage Return.
     */
    boolean isLinefeed();

    /**
     * Set Linefeed.
     *
     * @param a_Linefeed true if we should send Linefeed only, false if we
     *            should send Linefeed/Carriage Return.
     */
    void setLinefeed(final boolean a_Linefeed);

    /**
     * Select a charcater set for display.
     *
     * @param a_sequence
     * @param a_set
     */
    void selectCharacterSet(final CharacterSetSequence a_sequence,
            final CharacterSet a_set);

    /**
     * If the argument is false, causes subsequent drawing operations on the
     * screen canvas to be ignored. No drawing of any kind can occur in the
     * screen canvas until the flag is set to true. Graphics operations that
     * occurred while the flag was false are lost. When the flag is set to true,
     * the entire screen canvas is marked as needing to be redrawn. Nested calls
     * to this method are stacked.
     *
     * @param a_redraw
     */
    void setRedraw(final boolean a_redraw);
}
