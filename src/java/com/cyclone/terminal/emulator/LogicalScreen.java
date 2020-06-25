package com.cyclone.terminal.emulator;

/**
 * @author Phil.Baxter
 * 
 */
public final class LogicalScreen
{
    private int width;

    private int height;

    private int history;

    private int firstVisibleRow;

    private Cursor cursor;

    /**
     * @param a_Width
     * @param a_Height
     * @param a_History
     */
    public LogicalScreen(final int a_Width, final int a_Height,
            final int a_History)
    {
        width = a_Width;
        height = a_Height;
        history = a_History;

        firstVisibleRow = history - height;
        cursor = new Cursor(width, height);
    }

    /**
     * @return the height of the visible screen
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param a_height
     */
    public void setHeight(int a_height)
    {
        height = a_height;
        cursor.setBounds(width, height);
    }

    /**
     * @return the number of history lines off the top of the screen
     */
    public int getHistory()
    {
        return history;
    }

    /**
     * @param a_history
     */
    public void setHistory(int a_history)
    {
        history = a_history;
        firstVisibleRow = history - height;
    }

    /**
     * @return the screen width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @param a_width
     */
    public void setWidth(int a_width)
    {
        width = a_width;
        cursor.setBounds(width, height);
    }

    /**
     * @return the current cursor position
     */
    public Cursor getCursor()
    {
        return cursor;
    }

    /**
     * @return the first visible row on the screen, this takes into account that
     *         rows of history are not visible off the top of the screen.
     */
    public int getFirstVisibleRow()
    {
        return firstVisibleRow;
    }

    /**
     * @param a_firstVisibleRow
     */
    public void setFirstVisibleRow(int a_firstVisibleRow)
    {
        firstVisibleRow = a_firstVisibleRow;
    }

    /**
     * @param a_row
     * @param a_column
     * @return true if the specified cell is currently positioned within the
     *         bounds of the visible display
     */
    public boolean cursorInBounds(final int a_row, final int a_column)
    {
        if (((a_row < height) && (a_column < width)) && (a_row >= 0)
                && (a_column >= 0))
        {
            return true;
        }

        return false;
    }

    /**
     * @return true if the cursor is currently positioned within the bounds of
     *         the visible display
     */
    public boolean cursorInBounds()
    {
        return cursorInBounds(cursor.getRow(), cursor.getColumn());
    }

    /**
     * @return the last row on the display, rows start at 0
     */
    public int lastRow()
    {
        return cursor.getLastRow();
    }

    /**
     * @return the last column on the display, columns start at 0
     */
    public int lastColumn()
    {
        return cursor.getLastColumn();
    }

}
