/**
 * 
 */
package com.cyclone.terminal.emulator;

/**
 * @author Phil.Baxter
 * 
 */
public final class LogicalScreen
{
    private int m_Width;

    private int m_Height;

    private int m_History;

    private int m_firstVisibleRow;

    private Cursor m_cursor;

    /**
     * @param a_Width
     * @param a_Height
     * @param a_History
     */
    public LogicalScreen(final int a_Width, final int a_Height,
            final int a_History)
    {
        m_Width = a_Width;
        m_Height = a_Height;
        m_History = a_History;

        m_firstVisibleRow = m_History - m_Height;
        m_cursor = new Cursor(m_Width, m_Height);
    }

    /**
     * @return the height of the visible screen
     */
    public int getHeight()
    {
        return m_Height;
    }

    /**
     * @param a_height
     */
    public void setHeight(int a_height)
    {
        m_Height = a_height;
        m_cursor.setBounds(m_Width, m_Height);
    }

    /**
     * @return the number of history lines off the top of the screen
     */
    public int getHistory()
    {
        return m_History;
    }

    /**
     * @param a_history
     */
    public void setHistory(int a_history)
    {
        m_History = a_history;
        m_firstVisibleRow = m_History - m_Height;
    }

    /**
     * @return the screen width
     */
    public int getWidth()
    {
        return m_Width;
    }

    /**
     * @param a_width
     */
    public void setWidth(int a_width)
    {
        m_Width = a_width;
        m_cursor.setBounds(m_Width, m_Height);
    }

    /**
     * @return the current cursor position
     */
    public Cursor getCursor()
    {
        return m_cursor;
    }

    /**
     * @return the first visible row on the screen, this takes into account that
     *         rows of history are not visible off the top of the screen.
     */
    public int getFirstVisibleRow()
    {
        return m_firstVisibleRow;
    }

    /**
     * @param a_firstVisibleRow
     */
    public void setFirstVisibleRow(int a_firstVisibleRow)
    {
        m_firstVisibleRow = a_firstVisibleRow;
    }

    /**
     * @param a_row
     * @param a_column
     * @return true if the specified cell is currently positioned within the
     *         bounds of the visible display
     */
    public boolean cursorInBounds(final int a_row, final int a_column)
    {
        if (((a_row < m_Height) && (a_column < m_Width)) && (a_row >= 0)
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
        return cursorInBounds(m_cursor.getRow(), m_cursor.getColumn());
    }

    /**
     * @return the last row on the display, rows start at 0
     */
    public int lastRow()
    {
        return m_cursor.getLastRow();
    }

    /**
     * @return the last column on the display, columns start at 0
     */
    public int lastColumn()
    {
        return m_cursor.getLastColumn();
    }

}
