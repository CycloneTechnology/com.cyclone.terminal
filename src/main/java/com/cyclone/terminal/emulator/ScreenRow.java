package com.cyclone.terminal.emulator;

import com.cyclone.terminal.emulator.cell.Cell;

import java.util.stream.IntStream;

/**
 * @author Phil.Baxter
 */
public class ScreenRow
{
    private Cell[] cells;

    private int row;

    /**
     * Constructor for ScreenRow
     *
     * @param a_row
     * @param a_columns
     */
    public ScreenRow(final int a_row, final int a_columns)
    {
        row = a_row;

        cells = new Cell[a_columns];
        IntStream.range(0, a_columns).forEach(iCol ->
                cells[iCol] = new Cell(a_row, iCol));
    }

    final Cell getCell(final int a_column)
    {
        int col = a_column;

        if (col >= cells.length)
        {
            col = cells.length - 1;
        }
        return cells[col];
    }

    /**
     * @return the actual row on screen for this ScreenRow
     */
    public final int getRow()
    {
        return row;
    }

    /**
     * @param a_row
     */
    public final void setRow(int a_row)
    {
        row = a_row;

        for (Cell cell : cells)
        {
            cell.setRow(a_row);
        }
    }
}
