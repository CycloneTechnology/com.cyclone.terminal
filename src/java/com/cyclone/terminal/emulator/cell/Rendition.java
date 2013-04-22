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
    private boolean m_Bold;

    private boolean m_Italics;

    private boolean m_Underline;

    private boolean m_Blink;

    private boolean m_Inverse;

    private boolean m_Concealed;

    private int m_ForegroundIndex;

    private int m_BackgroundIndex;

    /**
     * Constructor for Rendition.
     */
    public Rendition()
    {
        m_Bold = false;
        m_Italics = false;
        m_Underline = false;
        m_Blink = false;
        m_Inverse = false;
        m_Concealed = false;

        m_ForegroundIndex = 2;
        m_BackgroundIndex = 0;
    }

    /**
     * @return the cell background color
     */
    public final int getBackgroundIndex()
    {
        return m_BackgroundIndex;
    }

    /**
     * Set the background color to use for the shell
     * 
     * @param a_backgroundIndex
     */
    public final void setBackgroundIndex(final int a_backgroundIndex)
    {
        m_BackgroundIndex = a_backgroundIndex;
    }

    /**
     * @return whether the cell is blinking
     */
    public final boolean isBlink()
    {
        return m_Blink;
    }

    /**
     * Set the blink state for the cell
     * 
     * @param a_blink
     */
    public final void setBlink(final boolean a_blink)
    {
        m_Blink = a_blink;
    }

    /**
     * @return whether characters drawn in the cell will be bold
     */
    public final boolean isBold()
    {
        return m_Bold;
    }

    /**
     * Set the bold state of the cell.
     * 
     * @param a_bold
     */
    public final void setBold(final boolean a_bold)
    {
        m_Bold = a_bold;
    }

    /**
     * @return wether the cell is concealed or not
     */
    public final boolean isConcealed()
    {
        return m_Concealed;
    }

    /**
     * @param a_concealed
     */
    public final void setConcealed(final boolean a_concealed)
    {
        m_Concealed = a_concealed;
    }

    /**
     * @return the foreground color for the cell
     */
    public final int getForegroundIndex()
    {
        return m_ForegroundIndex;
    }

    /**
     * @param a_foregroundIndex
     */
    public final void setForegroundIndex(final int a_foregroundIndex)
    {
        m_ForegroundIndex = a_foregroundIndex;
    }

    /**
     * @return whether the contents of the cell should be drawn inverse i.e. use
     *         background color for foreground and vice-versa
     */
    public final boolean isInverse()
    {
        return m_Inverse;
    }

    /**
     * @param a_inverse
     */
    public final void setInverse(final boolean a_inverse)
    {
        m_Inverse = a_inverse;
    }

    /**
     * @return whether the contents of the cell should be italicised
     */
    public final boolean isItalics()
    {
        return m_Italics;
    }

    /**
     * @param a_italics
     */
    public final void setItalics(final boolean a_italics)
    {
        m_Italics = a_italics;
    }

    /**
     * @return whether the cell should be underlined
     */
    public final boolean isUnderline()
    {
        return m_Underline;
    }

    /**
     * @param a_underline
     */
    public final void setUnderline(final boolean a_underline)
    {
        m_Underline = a_underline;
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

            if ((m_Bold == toCompare.m_Bold)
                    && (m_Italics == toCompare.m_Italics)
                    && (m_Underline == toCompare.m_Underline)
                    && (m_Blink == toCompare.m_Blink)
                    && (m_Inverse == toCompare.m_Inverse)
                    && (m_Concealed == toCompare.m_Concealed)
                    && (m_ForegroundIndex == toCompare.m_ForegroundIndex)
                    && (m_BackgroundIndex == toCompare.m_BackgroundIndex))
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
        if (m_Bold || m_Italics || m_Underline || m_Blink || m_Inverse
                || m_Concealed)
        {
            plain = false;
        }
        else if (m_ForegroundIndex != 2)
        {
            plain = false;
        }
        else if (m_BackgroundIndex != 0)
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
