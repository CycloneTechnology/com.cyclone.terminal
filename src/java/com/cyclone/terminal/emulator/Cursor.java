/**
 * 
 */
package com.cyclone.terminal.emulator;

/**
 * @author Phil.Baxter
 * 
 */
public final class Cursor
{
    private int m_lastColumn;

    private int m_lastRow;

    private int m_Column;

    private int m_Row;

    private boolean m_AutoWrap;

    private boolean m_ReverseAutoWrap;

    private int m_SavedRow;

    private int m_SavedColumn;

    private boolean[] m_tabs;

    private boolean m_origin;

    private int m_leftMargin;

    private int m_rightMargin;

    private int m_topMargin;

    private int m_bottomMargin;

    /**
     * @param a_width
     * @param a_height
     * 
     */
    public Cursor(final int a_width, final int a_height)
    {
        setBounds(a_width, a_height);
    }

    /**
     * Sets the bounds for the active display that the cursor is bound by
     * 
     * @param a_width
     * @param a_height
     */
    public void setBounds(final int a_width, final int a_height)
    {
        m_lastColumn = a_width - 1;
        m_lastRow = a_height - 1;

        // We need to reset our current row and column so that they are within
        // the new display bounds...
        m_Row = Math.min(m_Row, m_lastRow);
        m_Column = Math.min(m_Column, m_lastColumn);

        // Set up our default tab positions...
        m_tabs = new boolean[a_width];
        for (int iTab = 9; iTab < a_width; iTab += 8)
        {
            m_tabs[iTab] = true;
        }

        m_topMargin = 0;
        m_bottomMargin = m_lastRow;
    }

    /**
     * @return the last column the cursor is allowed to be on
     */
    public int getLastColumn()
    {
        return m_lastColumn;
    }

    /**
     * @return the column the cursor is on
     */
    public int getColumn()
    {
        return m_Column;
    }

    /**
     * @param a_column
     */
    public void setColumn(final int a_column)
    {
        m_Column = a_column;
        validateColumn();
    }

    /**
     * @return the last row the cursor is allowed to be on
     */
    public int getLastRow()
    {
        return m_lastRow;
    }

    /**
     * @return the row the cursor is on
     */
    public int getRow()
    {
        return m_Row;
    }

    /**
     * Set an absolute Row.
     * 
     * @param a_row
     */
    public void setRow(final int a_row)
    {
        m_Row = a_row;
        validateRow();
    }

    /**
     * Set an absolute position.
     * 
     * @param a_row
     * @param a_column
     */
    public void setPosition(final int a_row, final int a_column)
    {
        setRow(a_row);
        setColumn(a_column);
    }

    /**
     * 
     */
    public void home()
    {
        m_Row = 0;
        m_Column = 0;
    }

    /**
     * 
     */
    public void right()
    {
        right(1);
        validateColumn();
    }

    /**
     * @param a_columns
     */
    public void right(final int a_columns)
    {
        m_Column += a_columns;
        validateColumn();
    }

    /**
     * @return data indicating wether the rows on the screen should be scrolled
     * 
     */
    public Scroll down()
    {
        return down(1);
    }

    /**
     * @param a_rows
     * @return data indicating wether the rows on the screen should be scrolled
     */
    public Scroll down(final int a_rows)
    {
        m_Row += a_rows;
        return validateRow();
    }

    /**
     * @return the amount the screen should scroll after decrementing the column
     * 
     */
    public Scroll left()
    {
        return left(1);
    }

    /**
     * @param a_columns
     * @return the amount the screen should scroll after decrementing the column
     */
    public Scroll left(final int a_columns)
    {
        m_Column -= a_columns;
        return validateColumn();
    }

    /**
     * @return data indicating wether the rows on the screen should be scrolled
     * 
     */
    public Scroll up()
    {
        return up(1);
    }

    /**
     * @param a_rows
     * @return data indicating wether the rows on the screen should be scrolled
     */
    public Scroll up(final int a_rows)
    {
        m_Row -= a_rows;
        return validateRow();
    }

    /**
     * Move the cursor to the next tab position.
     */
    public void tab()
    {
        for (int iCol = m_Column + 1; iCol <= m_lastColumn; iCol++)
        {
            if (m_tabs[iCol])
            {
                setColumn(iCol);
                break;
            }
        }
    }

    /**
     * Set a tab stop at the current cursor position
     */
    public void setTab()
    {
        m_tabs[m_Column] = true;
    }

    /**
     * Set a tab stop at the current cursor position
     * 
     * @return true if a tab stop is set at the current cursor position.
     */
    public boolean getTab()
    {
        return m_tabs[m_Column];
    }

    /**
     * Clear a tab stop at the current cursor position
     */
    public void clearTab()
    {
        m_tabs[m_Column] = false;
    }

    /**
     * Clear a tab stop at the current cursor position
     */
    public void clearAllTabs()
    {
        m_tabs = new boolean[m_lastColumn + 1];
    }

    private Scroll validateColumn()
    {
        Scroll scroll = new Scroll();
        if (m_Column > m_lastColumn)
        {
            if (m_AutoWrap)
            {
                scroll = down();
                m_Column = 0;
            }
            else
            {
                m_Column = m_lastColumn;
            }
        }
        else
        {
            if (m_Column < 0)
            {
                if (m_ReverseAutoWrap)
                {
                    scroll = up();
                    m_Column = m_lastColumn;
                }
                else
                {
                    m_Column = 0;
                }
            }
        }

        return scroll;
    }

    private Scroll validateRow()
    {
        final Scroll scroll = new Scroll();
        while (m_Row > m_lastRow)
        {
            scroll.setScrollDirection(Scroll.Direction.UP);
            scroll.increment();
            m_Row = m_lastRow;
        }

        while (m_Row < 0)
        {
            scroll.setScrollDirection(Scroll.Direction.DOWN);
            scroll.increment();
            m_Row++;
        }

        return scroll;
    }

    /**
     * @param a_autoWrap
     */
    public void setAutoWrap(final boolean a_autoWrap)
    {
        m_AutoWrap = a_autoWrap;
    }

    /**
     * @return true if auto-wrap is enabled
     */
    public boolean isAutoWrap()
    {
        return m_AutoWrap;
    }

    /**
     * @param a_reverseAutoWrap
     */
    public void setReverseAutoWrap(final boolean a_reverseAutoWrap)
    {
        m_ReverseAutoWrap = a_reverseAutoWrap;
    }

    /**
     * @return true if reverse auto-wrap is enabled
     */
    public boolean isReverseAutoWrap()
    {
        return m_ReverseAutoWrap;
    }

    /**
     * Save the Cursor Position.
     */
    public void saveCursor()
    {
        m_SavedRow = getRow();
        m_SavedColumn = getColumn();
    }

    /**
     * Unsave the cursor position.
     */
    public void unsaveCursor()
    {
        setPosition(m_SavedRow, m_SavedColumn);
    }

    /**
     * Reset the row, column, autowrap and saved cursor values
     */
    public void reset()
    {
        m_Column = 0;
        m_Row = 0;
        m_AutoWrap = false;
        m_ReverseAutoWrap = false;
        m_SavedRow = 0;
        m_SavedColumn = 0;
    }

    /**
     * @return true if origin mode is reset (upper left corner), false if set
     *         within margins.
     */
    public boolean isOrigin()
    {
        return m_origin;
    }

    /**
     * When origin is set, the home cursor position is at the upper-left corner
     * of the screen, within the margins. The starting point for line numbers
     * depends on the current top margin setting. The cursor cannot move outside
     * of the margins.
     * 
     * When origin is reset, the home cursor position is at the upper-left
     * corner of the screen. The starting point for line numbers is independent
     * of the margins. The cursor can move outside of the margins.
     * 
     * @param a_origin
     */
    public void setOrigin(boolean a_origin)
    {
        m_origin = a_origin;
    }

    /**
     * @return the left margin column number
     */
    public int getLeftMargin()
    {
        return m_leftMargin;
    }

    /**
     * @param a_leftMargin set the left margin column
     */
    public void setLeftMargin(int a_leftMargin)
    {
        m_leftMargin = a_leftMargin;
        validateMargins();
    }

    /**
     * @return the right margin column number
     */
    public int getRightMargin()
    {
        return m_rightMargin;
    }

    /**
     * @param a_rightMargin set the right margin column
     */
    public void setRightMargin(int a_rightMargin)
    {
        m_rightMargin = a_rightMargin;
        validateMargins();
    }

    /**
     * @return the top margin column number
     */
    public int getTopMargin()
    {
        return m_topMargin;
    }

    /**
     * @param a_leftMargin set the left margin column
     */
    public void setTopMargin(int a_leftMargin)
    {
        m_topMargin = a_leftMargin;
        validateMargins();
    }

    /**
     * @return the right margin column number
     */
    public int getBottomMargin()
    {
        return m_bottomMargin;
    }

    /**
     * @param a_bottomMargin set the right margin column
     */
    public void setBottomMargin(int a_bottomMargin)
    {
        m_bottomMargin = a_bottomMargin;
        validateMargins();
    }

    private void validateMargins()
    {
        if (m_leftMargin >= m_rightMargin)
        {
            m_leftMargin = m_rightMargin + 1;
        }

        if (m_topMargin >= m_bottomMargin)
        {
            m_topMargin = m_bottomMargin - 1;
        }
    }
}
