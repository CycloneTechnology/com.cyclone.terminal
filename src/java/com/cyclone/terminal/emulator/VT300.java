package com.cyclone.terminal.emulator;

import org.eclipse.swt.widgets.Composite;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class VT300 extends VT200
{
    /**
     * @param a_parent
     */
    public VT300(final Composite a_parent)
    {
        super(a_parent);
    }

    /**
     * @param a_parent
     * @param a_width
     * @param a_height
     * @param a_history
     */
    public VT300(final Composite a_parent, final int a_width,
            final int a_height, final int a_history)
    {
        super(a_parent, a_width, a_height, a_history);
    }

}
