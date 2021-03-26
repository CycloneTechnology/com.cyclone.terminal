package com.cyclone.terminal.emulator.font.vt100.characters;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;

import com.cyclone.terminal.emulator.Colours;
import com.cyclone.terminal.emulator.cell.BlinkState;
import com.cyclone.terminal.emulator.cell.Rendition;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class RenderImage
{
    private Image image;

    /**
     * @param a_device
     * @param a_Rendition
     * @param a_selected
     * @param a_blinkState
     * @return an {@link Image} with the specified rendition
     */
    public final synchronized Image getImage(final Device a_device,
            final Rendition a_Rendition, final boolean a_selected,
            final BlinkState a_blinkState)
    {
        // We are going to create a new image, dispose of the old one...
        if (image != null)
        {
            image.dispose();
            image = null;
        }

        // Set up our foregound and background colours based upon the bold
        // and inverse attributes. The colour indexes will have been set via
        // an SGR sequence, or they may be the default values
        final Color fore;
        final Color back;
        if (!a_Rendition.isInverse() && !a_selected)
        {
            if (a_Rendition.isBold())
            {
                fore = Colours.get(a_Rendition.getForegroundIndex()).getBold();
            }
            else
            {
                fore = Colours.get(a_Rendition.getForegroundIndex())
                        .getNormal();
            }
            back = Colours.get(a_Rendition.getBackgroundIndex()).getNormal();
        }
        else
        {
            fore = Colours.get(a_Rendition.getBackgroundIndex()).getNormal();
            if (a_Rendition.isBold())
            {
                back = Colours.get(a_Rendition.getForegroundIndex()).getBold();
            }
            else
            {
                back = Colours.get(a_Rendition.getForegroundIndex())
                        .getNormal();
            }
        }

        final PaletteData paletteData;
        if (a_blinkState == BlinkState.OFF)
        {
            paletteData = new PaletteData(back.getRGB(), fore.getRGB());
        }
        else
        {
            paletteData = new PaletteData(fore.getRGB(), back.getRGB());
        }
        final ImageData imageData = new ImageData(getSize().x, getSize().y, 1,
                paletteData);
        imageData.setPixels(0, 0, getSize().x * getSize().y, getData(), 0);

        image = new Image(a_device, imageData);

        // If we need the image modifying in any way, do it now (i.e. we may
        // need to underline the character
        final GC gc = new GC(image);
        try
        {
            gc.setForeground(fore);
            gc.setBackground(back);

            if (a_Rendition.isUnderline())
            {
                gc.drawLine(0, getSize().y - 1, getSize().x, getSize().y - 1);
            }
        }
        finally
        {
            gc.dispose();
        }

        return image;
    }

    /**
     * @return an array of bits used to determine which pixels are drawn in the
     *         image
     */
    public abstract int[] getData();

    /**
     * @return the size of this image when it is rendered
     */
    public abstract Point getSize();
}
