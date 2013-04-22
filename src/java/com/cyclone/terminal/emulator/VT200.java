/**
 * 
 */
package com.cyclone.terminal.emulator;

import org.eclipse.swt.widgets.Composite;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class VT200 extends VT100
{
    /**
     * @param a_parent
     */
    public VT200(final Composite a_parent)
    {
        super(a_parent);
    }

    /**
     * @param a_parent
     * @param a_width
     * @param a_height
     * @param a_history
     */
    public VT200(final Composite a_parent, final int a_width,
            final int a_height, final int a_history)
    {
        super(a_parent, a_width, a_height, a_history);
    }

    /**
     * DECSEL - Erases all erasable characters in the line
     * 
     * @param a_numParams the number of parameters in the escape sequence
     * @param a_params an array of parameters present in the escape sequence,
     *            there should only be one and its value will be one of... <le>
     *            <li> 0 (default) - Erases all erasable characters (DECSCA)
     *            from the cursor to the end of the line. Does not affect video
     *            line attributes or video character attributes (SGR).
     *            <li> 1 - Erases all erasable characters (DECSCA) from the
     *            beginning of the line to and including the cursor position.
     *            Does not affect video line attributes or video character
     *            attributes.
     *            <li> 2 - Erases all erasable characters (DECSCA) on the line.
     *            Does not affect video line attributes or video character
     *            attributes. </le>
     */
    public final void doDECSEL(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // TODO
                break;
            case 1:
                switch (a_params[0])
                {
                    default:
                    case 0:
                        // TODO
                        break;
                    case 1:
                        // TODO
                        break;
                    case 2:
                        // TODO
                        break;
                }
                break;
        }
    }

    /**
     * DECSED - Erases all erasable characters (DECSCA).
     * 
     * @param a_numParams
     * @param a_params an array of parameters present in the escape sequence
     *            only position 0 should be valid and will be one of the
     *            following values... <le>
     *            <li> 0 (default) - Erases all erasable characters (DECSCA)
     *            from and including the cursor to the end of the screen. Does
     *            not affect video line attributes or video character attributes
     *            (SGR).
     *            <li> 1 - Erases all erasable characters (DECSCA) from the
     *            beginning of the screen to and including the cursor. Does not
     *            affect video line attributes or video character attributes
     *            (SGR).
     *            <li> 2 - Erases all erasable characters (DECSCA) in the entire
     *            display. Does not affect video character attributes or video
     *            line attributes (SGR). </le>
     */
    public final void doDECSED(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                break;
            case 1:
                switch (a_params[0])
                {
                    default:
                    case 0:
                        // TODO
                        break;
                    case 1:
                        // TODO
                        break;
                    case 2:
                        // TODO
                        break;
                }
                break;
        }
    }

}
