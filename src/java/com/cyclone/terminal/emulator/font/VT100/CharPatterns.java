/**
 * 
 */
package com.cyclone.terminal.emulator.font.VT100;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.cyclone.terminal.emulator.EmulatorException;
import com.cyclone.terminal.emulator.cell.BlinkState;
import com.cyclone.terminal.emulator.cell.Cell;

/**
 * @author Phil.Baxter
 * 
 */
public interface CharPatterns
{
    /**
     * The width of each character
     */
    int CHAR_WIDTH = 15;

    /**
     * The height of each character
     */
    int CHAR_HEIGHT = 36;

    /**
     * 
     */

    /**
     * @param a_cell
     * @param a_selected
     * @param a_blinkState
     * @return the image to display for the specified cell
     * @throws EmulatorException
     */
    Image getImage(Cell a_cell, final boolean a_selected,
            final BlinkState a_blinkState) throws EmulatorException;

    /**
     * @return the size of each character
     */
    Point getCharSize();
}
