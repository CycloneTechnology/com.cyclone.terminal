package com.cyclone.terminal.emulator.led;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.eclipse.swt.widgets.Composite;

/**
 * @author Phil.Baxter
 */
public class Panel
{
    private List<LED> leds;

    /**
     * @param a_parent
     * @param a_count
     */
    public Panel(final Composite a_parent, final int a_count)
    {
        leds = new ArrayList<>();
        IntStream.range(0, a_count).forEach(i ->
                leds.add(new LED(a_parent)));
    }

    /**
     * @param a_index
     * @param a_on
     */
    public final void setLED(final int a_index, final boolean a_on)
    {
        if (a_index < leds.size())
        {
            leds.get(a_index).setOn(a_on);
        }
    }

    /**
     *
     */
    public final void refresh()
    {
        for (LED led : leds)
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
        for (LED led : leds)
        {
            led.show(a_show);
        }
    }
}
