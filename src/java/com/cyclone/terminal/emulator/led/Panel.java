/**
 * 
 */
package com.cyclone.terminal.emulator.led;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

/**
 * @author Phil.Baxter
 * 
 */
public class Panel
{
    private List<LED> m_LEDs;

    /**
     * @param a_parent
     * @param a_count
     */
    public Panel(final Composite a_parent, final int a_count)
    {
        m_LEDs = new ArrayList<LED>();
        for (int i = 0; i < a_count; i++)
        {
            m_LEDs.add(new LED(a_parent));
        }
    }

    /**
     * @param a_index
     * @param a_on
     */
    public final void setLED(final int a_index, final boolean a_on)
    {
        if (a_index < m_LEDs.size())
        {
            m_LEDs.get(a_index).setOn(a_on);
        }
    }

    /**
     * 
     */
    public final void refresh()
    {
        for (LED led : m_LEDs)
        {
            led.redraw();
        }
    }

    /**
     * Show or hide the panel of LED's.
     * 
     * @param a_show
     */
    public final void show(boolean a_show)
    {
        for (LED led : m_LEDs)
        {
            led.show(a_show);
        }
    }
}
