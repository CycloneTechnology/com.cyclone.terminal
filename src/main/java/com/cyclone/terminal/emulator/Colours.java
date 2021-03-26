package com.cyclone.terminal.emulator;

import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Phil.Baxter
 * 
 */
public enum Colours
{
 Black(0, new RGB(0, 0, 0), new RGB(0, 0, 0)),
 Red(1, new RGB(127, 0, 0), new RGB(255, 0, 0)),
 Green(2, new RGB(0, 127, 0), new RGB(0, 255, 0)),
 Yellow(3, new RGB(127, 127, 0), new RGB(255, 255, 0)),
 Blue(4, new RGB(0, 0, 127), new RGB(0, 0, 255)),
 Magenta(5, new RGB(127, 0, 127), new RGB(255, 0, 255)),
 Cyan(6, new RGB(0, 127, 127), new RGB(0, 255, 255)),
 White(7, new RGB(255, 255, 255), new RGB(255, 255, 255));

    /**
     * 
     */
    public static final int DEFAULT_BACKGROUND_COLOUR_INDEX = 0;

    /**
     * 
     */
    public static final int DEFAULT_FOREGROUND_COLOUR_INDEX = 2;

    private final int index;

    private final Color normal;

    private final Color bold;

    private Colours(final int a_index, final RGB a_normal, final RGB a_bold)
    {
        index = a_index;
        normal = new Color(a_normal);
        bold = new Color(a_bold);
    }

    public int getIndex()
    {
        return index;
    }

    public Color getNormal()
    {
        return normal;
    }

    public Color getBold()
    {
        return bold;
    }

    /**
     * Returns the Colours entry with the specified index value
     * 
     * @param a_index the index required in the range specified against the enum
     *            entries
     * @return the enumeration corresponding to the specified index
     */
    public static Colours get(int a_index)
    {
        return List.of(Colours.class.getEnumConstants()).stream()
                .filter(colour -> colour.getIndex() == a_index).findAny()
                .orElseThrow(() -> new RuntimeException(new EmulatorException(
                        "Invalid colour index requested : " + a_index)));
    }
}
