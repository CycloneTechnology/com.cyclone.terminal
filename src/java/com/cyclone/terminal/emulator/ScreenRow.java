/**
 * 
 */
package com.cyclone.terminal.emulator;

import com.cyclone.terminal.emulator.cell.Cell;

/**
 * @author Phil.Baxter
 * 
 */
public class ScreenRow
{
    private Cell[] m_Cells;

    private int m_Row;

    /**
     * Constructor for ScreenRow
     * 
     * @param a_row
     * @param a_columns
     */
    public ScreenRow(final int a_row, final int a_columns)
    {
        m_Row = a_row;

        m_Cells = new Cell[a_columns];
        for (int iCol = 0; iCol < a_columns; iCol++)
        {
            m_Cells[iCol] = new Cell(a_row, iCol);
        }
    }

    final Cell getCell(final int a_column)
    {
        int col = a_column;

        if (col >= m_Cells.length)
        {
            col = m_Cells.length - 1;
        }
        return m_Cells[col];
    }

    /**
     * @return the actual row on screen for this ScreenRow
     */
    public final int getRow()
    {
        return m_Row;
    }

    /**
     * @param a_row
     */
    public final void setRow(int a_row)
    {
        m_Row = a_row;

        for (int iCol = 0; iCol < m_Cells.length; iCol++)
        {
            m_Cells[iCol].setRow(a_row);
        }
    }
}
