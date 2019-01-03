/**
 * 
 */
package com.cyclone.terminal.emulator.font.VT100.characters;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.cyclone.terminal.emulator.Colours;
import com.cyclone.terminal.emulator.cell.BlinkState;
import com.cyclone.terminal.emulator.cell.Rendition;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class RenderImage implements Colours
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
        final RGB fore;
        final RGB back;
        if (!a_Rendition.isInverse() && !a_selected)
        {
            if (a_Rendition.isBold())
            {
                fore = Colours.BOLD[a_Rendition.getForegroundIndex()];
            }
            else
            {
                fore = Colours.NORMAL[a_Rendition.getForegroundIndex()];
            }
            back = Colours.NORMAL[a_Rendition.getBackgroundIndex()];
        }
        else
        {
            fore = Colours.NORMAL[a_Rendition.getBackgroundIndex()];
            if (a_Rendition.isBold())
            {
                back = Colours.BOLD[a_Rendition.getForegroundIndex()];
            }
            else
            {
                back = Colours.NORMAL[a_Rendition.getForegroundIndex()];
            }
        }

        final PaletteData paletteData;
        if (a_blinkState == BlinkState.OFF)
        {
            paletteData = new PaletteData(new RGB[]
            {back, fore});
        }
        else
        {
            paletteData = new PaletteData(new RGB[]
            {fore, back});
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
            gc.setForeground(new Color(a_device, fore));
            gc.setBackground(new Color(a_device, back));

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
