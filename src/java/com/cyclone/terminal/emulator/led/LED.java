/**
 *
 */
package com.cyclone.terminal.emulator.led;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Phil.Baxter
 *
 */
public class LED extends Canvas
{
    private static final String LED_OFF_IMAGE_PATH = "images/led/off.gif";

    private static final String LED_ON_IMAGE_PATH = "images/led/on.gif";

    private boolean m_on;

    private final Image m_imageOn;

    private final Image m_imageOff;

    /**
     * @param a_parent
     */
    public LED(final Composite a_parent)
    {
        super(a_parent, SWT.NONE);

        final GridData ledGridData = new GridData(SWT.FILL, SWT.FILL, false, false);
        ledGridData.heightHint = 20;
        ledGridData.widthHint = 20;

        setLayoutData(ledGridData);
        addPaintListener(new PaintListener()
        {
            /**
             * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
             */
            @Override
            public void paintControl(PaintEvent a_e)
            {
                draw();
            }
        });

        m_imageOn = new Image(getDisplay(), getClass().getClassLoader()
                .getResourceAsStream(LED_ON_IMAGE_PATH));
        m_imageOff = new Image(getDisplay(), getClass().getClassLoader()
                .getResourceAsStream(LED_OFF_IMAGE_PATH));

    }

    private void draw()
    {
        final GC gc = new GC(this);
        try
        {
            if (m_on)
            {
                gc.drawImage(m_imageOn, 0, 0, 20, 20, 0, 0, 20, 20);
            }
            else
            {
                gc.drawImage(m_imageOff, 0, 0, 20, 20, 0, 0, 20, 20);
            }
        }
        finally
        {
            gc.dispose();
        }
    }

    /**
     * @return whether the LSED is on or off
     */
    public final boolean isOn()
    {
        return m_on;
    }

    /**
     * Switches the LED on or off.
     *
     * @param a_on
     */
    public final void setOn(final boolean a_on)
    {
        m_on = a_on;
        draw();
    }

    /**
     * Show or hide the LED.
     *
     * @param a_show
     */
    public final void show(final boolean a_show)
    {
        setVisible(a_show);
    }
}
