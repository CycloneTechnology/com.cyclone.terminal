package com.cyclone.terminal.emulator;

/**
 * @author Phil.Baxter
 * 
 */
public final class Cursor
{
    private int lastColumn;

    private int lastRow;

    private int column;

    private int row;

    private boolean autoWrap;

    private boolean reverseAutoWrap;

    private int savedRow;

    private int savedColumn;

    private boolean[] tabs;

    private boolean origin;

    private int leftMargin;

    private int rightMargin;

    private int topMargin;

    private int bottomMargin;

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
        lastColumn = a_width - 1;
        lastRow = a_height - 1;

        // We need to reset our current row and column so that they are within
        // the new display bounds...
        row = Math.min(row, lastRow);
        column = Math.min(column, lastColumn);

        // Set up our default tab positions...
        tabs = new boolean[a_width];
        for (int iTab = 9; iTab < a_width; iTab += 8)
        {
            tabs[iTab] = true;
        }

        topMargin = 0;
        bottomMargin = lastRow;
    }

    /**
     * @return the last column the cursor is allowed to be on
     */
    public int getLastColumn()
    {
        return lastColumn;
    }

    /**
     * @return the column the cursor is on
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * @param a_column
     */
    public void setColumn(final int a_column)
    {
        column = a_column;
        validateColumn();
    }

    /**
     * @return the last row the cursor is allowed to be on
     */
    public int getLastRow()
    {
        return lastRow;
    }

    /**
     * @return the row the cursor is on
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Set an absolute Row.
     * 
     * @param a_row
     */
    public void setRow(final int a_row)
    {
        row = a_row;
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
        row = 0;
        column = 0;
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
        column += a_columns;
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
        row += a_rows;
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
        column -= a_columns;
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
        row -= a_rows;
        return validateRow();
    }

    /**
     * Move the cursor to the next tab position.
     */
    public void tab()
    {
        for (int iCol = column + 1; iCol <= lastColumn; iCol++)
        {
            if (tabs[iCol])
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
        tabs[column] = true;
    }

    /**
     * Set a tab stop at the current cursor position
     * 
     * @return true if a tab stop is set at the current cursor position.
     */
    public boolean getTab()
    {
        return tabs[column];
    }

    /**
     * Clear a tab stop at the current cursor position
     */
    public void clearTab()
    {
        tabs[column] = false;
    }

    /**
     * Clear a tab stop at the current cursor position
     */
    public void clearAllTabs()
    {
        tabs = new boolean[lastColumn + 1];
    }

    private Scroll validateColumn()
    {
        Scroll scroll = new Scroll();
        if (column > lastColumn)
        {
            if (autoWrap)
            {
                scroll = down();
                column = 0;
            }
            else
            {
                column = lastColumn;
            }
        }
        else
        {
            if (column < 0)
            {
                if (reverseAutoWrap)
                {
                    scroll = up();
                    column = lastColumn;
                }
                else
                {
                    column = 0;
                }
            }
        }

        return scroll;
    }

    private Scroll validateRow()
    {
        final Scroll scroll = new Scroll();
        while (row > lastRow)
        {
            scroll.setScrollDirection(Scroll.Direction.UP);
            scroll.increment();
            row = lastRow;
        }

        while (row < 0)
        {
            scroll.setScrollDirection(Scroll.Direction.DOWN);
            scroll.increment();
            row++;
        }

        return scroll;
    }

    /**
     * @param a_autoWrap
     */
    public void setAutoWrap(final boolean a_autoWrap)
    {
        autoWrap = a_autoWrap;
    }

    /**
     * @return true if auto-wrap is enabled
     */
    public boolean isAutoWrap()
    {
        return autoWrap;
    }

    /**
     * @param a_reverseAutoWrap
     */
    public void setReverseAutoWrap(final boolean a_reverseAutoWrap)
    {
        reverseAutoWrap = a_reverseAutoWrap;
    }

    /**
     * @return true if reverse auto-wrap is enabled
     */
    public boolean isReverseAutoWrap()
    {
        return reverseAutoWrap;
    }

    /**
     * Save the Cursor Position.
     */
    public void saveCursor()
    {
        savedRow = getRow();
        savedColumn = getColumn();
    }

    /**
     * Unsave the cursor position.
     */
    public void unsaveCursor()
    {
        setPosition(savedRow, savedColumn);
    }

    /**
     * Reset the row, column, autowrap and saved cursor values
     */
    public void reset()
    {
        column = 0;
        row = 0;
        autoWrap = false;
        reverseAutoWrap = false;
        savedRow = 0;
        savedColumn = 0;
    }

    /**
     * @return true if origin mode is reset (upper left corner), false if set
     *         within margins.
     */
    public boolean isOrigin()
    {
        return origin;
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
        origin = a_origin;
    }

    /**
     * @return the left margin column number
     */
    public int getLeftMargin()
    {
        return leftMargin;
    }

    /**
     * @param a_leftMargin set the left margin column
     */
    public void setLeftMargin(int a_leftMargin)
    {
        leftMargin = a_leftMargin;
        validateMargins();
    }

    /**
     * @return the right margin column number
     */
    public int getRightMargin()
    {
        return rightMargin;
    }

    /**
     * @param a_rightMargin set the right margin column
     */
    public void setRightMargin(int a_rightMargin)
    {
        rightMargin = a_rightMargin;
        validateMargins();
    }

    /**
     * @return the top margin column number
     */
    public int getTopMargin()
    {
        return topMargin;
    }

    /**
     * @param a_leftMargin set the left margin column
     */
    public void setTopMargin(int a_leftMargin)
    {
        topMargin = a_leftMargin;
        validateMargins();
    }

    /**
     * @return the right margin column number
     */
    public int getBottomMargin()
    {
        return bottomMargin;
    }

    /**
     * @param a_bottomMargin set the right margin column
     */
    public void setBottomMargin(int a_bottomMargin)
    {
        bottomMargin = a_bottomMargin;
        validateMargins();
    }

    private void validateMargins()
    {
        if (leftMargin >= rightMargin)
        {
            leftMargin = rightMargin + 1;
        }

        if (topMargin >= bottomMargin)
        {
            topMargin = bottomMargin - 1;
        }
    }
}
