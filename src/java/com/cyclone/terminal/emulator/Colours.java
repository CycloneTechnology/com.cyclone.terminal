/**
 * 
 */
package com.cyclone.terminal.emulator;

import org.eclipse.swt.graphics.RGB;

/**
 * @author Phil.Baxter
 * 
 */
public interface Colours
{
    /**
     * 
     */
    int BACKGROUND_COLOUR_INDEX = 0;

    /**
     * 
     */
    int FOREGROUND_COLOUR_INDEX = 2;

    /**
     * Colour names
     */
    String[] NAMES =
    {"Black", "Red", "Green", "Yellow", "Blue", "Magenta", "Cyan", "White"};

    /**
     * Normal colours
     */
    RGB[] NORMAL =
    {
    // These colours should match the ones in our names array above
            new RGB(0, 0, 0), // Black
            new RGB(127, 0, 0), // Red
            new RGB(0, 127, 0), // Green
            new RGB(127, 127, 0), // Yellow
            new RGB(0, 0, 127), // Blue
            new RGB(127, 0, 127), // Magenta
            new RGB(0, 127, 127), // Cyan
            new RGB(255, 255, 255) // White
    };

    /**
     * Bold colours
     */
    RGB[] BOLD =
    {
    // These colours should match the ones in our names array above
            new RGB(0, 0, 0), // Black
            new RGB(255, 0, 0), // Red
            new RGB(0, 255, 0), // Green
            new RGB(255, 255, 0), // Yellow
            new RGB(0, 0, 255), // Blue
            new RGB(255, 0, 255), // Magenta
            new RGB(0, 255, 255), // Cyan
            new RGB(255, 255, 255) // White
    };
}
