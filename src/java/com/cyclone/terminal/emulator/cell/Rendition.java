/**
 * 
 */
package com.cyclone.terminal.emulator.cell;

import com.cyclone.terminal.emulator.Colours;

/**
 * @author Phil.Baxter
 * 
 */
public class Rendition implements Colours, Cloneable
{
    private boolean bold;

    private boolean italics;

    private boolean underline;

    private boolean blink;

    private boolean inverse;

    private boolean concealed;

    private int foregroundIndex;

    private int backgroundIndex;

    /**
     * Constructor for Rendition.
     */
    public Rendition()
    {
        bold = false;
        italics = false;
        underline = false;
        blink = false;
        inverse = false;
        concealed = false;

        foregroundIndex = 2;
        backgroundIndex = 0;
    }

    /**
     * @return the cell background color
     */
    public final int getBackgroundIndex()
    {
        return backgroundIndex;
    }

    /**
     * Set the background color to use for the shell
     * 
     * @param a_backgroundIndex
     */
    public final void setBackgroundIndex(final int a_backgroundIndex)
    {
        backgroundIndex = a_backgroundIndex;
    }

    /**
     * @return whether the cell is blinking
     */
    public final boolean isBlink()
    {
        return blink;
    }

    /**
     * Set the blink state for the cell
     * 
     * @param a_blink
     */
    public final void setBlink(final boolean a_blink)
    {
        blink = a_blink;
    }

    /**
     * @return whether characters drawn in the cell will be bold
     */
    public final boolean isBold()
    {
        return bold;
    }

    /**
     * Set the bold state of the cell.
     * 
     * @param a_bold
     */
    public final void setBold(final boolean a_bold)
    {
        bold = a_bold;
    }

    /**
     * @return wether the cell is concealed or not
     */
    public final boolean isConcealed()
    {
        return concealed;
    }

    /**
     * @param a_concealed
     */
    public final void setConcealed(final boolean a_concealed)
    {
        concealed = a_concealed;
    }

    /**
     * @return the foreground color for the cell
     */
    public final int getForegroundIndex()
    {
        return foregroundIndex;
    }

    /**
     * @param a_foregroundIndex
     */
    public final void setForegroundIndex(final int a_foregroundIndex)
    {
        foregroundIndex = a_foregroundIndex;
    }

    /**
     * @return whether the contents of the cell should be drawn inverse i.e. use
     *         background color for foreground and vice-versa
     */
    public final boolean isInverse()
    {
        return inverse;
    }

    /**
     * @param a_inverse
     */
    public final void setInverse(final boolean a_inverse)
    {
        inverse = a_inverse;
    }

    /**
     * @return whether the contents of the cell should be italicised
     */
    public final boolean isItalics()
    {
        return italics;
    }

    /**
     * @param a_italics
     */
    public final void setItalics(final boolean a_italics)
    {
        italics = a_italics;
    }

    /**
     * @return whether the cell should be underlined
     */
    public final boolean isUnderline()
    {
        return underline;
    }

    /**
     * @param a_underline
     */
    public final void setUnderline(final boolean a_underline)
    {
        underline = a_underline;
    }

    /**
     * Creates a clone of a Rendition
     * 
     * @param a_Rendition the Rendition to clone
     * @return the cloned Rendition
     */
    public static Rendition createClone(final Rendition a_Rendition)
    {
        Rendition rendition = null;
        try
        {
            rendition = (Rendition) a_Rendition.clone();
        }
        catch (final CloneNotSupportedException e)
        {
            // TODO log
        }

        return rendition;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object a_obj)
    {
        if (a_obj instanceof Rendition)
        {
            final Rendition toCompare = (Rendition) a_obj;

            if ((bold == toCompare.bold)
                    && (italics == toCompare.italics)
                    && (underline == toCompare.underline)
                    && (blink == toCompare.blink)
                    && (inverse == toCompare.inverse)
                    && (concealed == toCompare.concealed)
                    && (foregroundIndex == toCompare.foregroundIndex)
                    && (backgroundIndex == toCompare.backgroundIndex))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode()
    {
        return super.hashCode();
    }

    /**
     * @return true if the rendition has no special attributes set
     */
    public final boolean isPlain()
    {
        final boolean plain;
        if (bold || italics || underline || blink || inverse
                || concealed)
        {
            plain = false;
        }
        else if (foregroundIndex != 2)
        {
            plain = false;
        }
        else if (backgroundIndex != 0)
        {
            plain = false;
        }
        else
        {
            plain = true;
        }

        return plain;
    }
}
