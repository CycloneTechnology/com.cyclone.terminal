package com.cyclone.terminal.emulator.cell;

/**
 * @author Phil.Baxter
 * 
 */
public class Attribute
{
    private boolean selectiveErase;

    /**
     * Constructor for Attribute
     */
    public Attribute()
    {
        selectiveErase = false;
    }

    /**
     * @return true if selective erase is enabled
     */
    public final boolean isSelectiveErase()
    {
        return selectiveErase;
    }

    /**
     * @param a_selectiveErase
     */
    public final void setSelectiveErase(boolean a_selectiveErase)
    {
        selectiveErase = a_selectiveErase;
    }
}
