/**
 * 
 */
package com.cyclone.terminal.emulator.cell;

/**
 * @author Phil.Baxter
 * 
 */
public class Attribute
{
    private boolean m_SelectiveErase;

    /**
     * Constructor for Attribute
     */
    public Attribute()
    {
        m_SelectiveErase = false;
    }

    /**
     * @return true if selective erase is enabled
     */
    public final boolean isSelectiveErase()
    {
        return m_SelectiveErase;
    }

    /**
     * @param a_selectiveErase
     */
    public final void setSelectiveErase(boolean a_selectiveErase)
    {
        m_SelectiveErase = a_selectiveErase;
    }
}
