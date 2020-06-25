package com.cyclone.terminal.parser;

/**
 * @author Phil.Baxter
 * 
 */
public enum Action
{
    /**
     * 
     */
    NONE,

    /**
     * 
     */
    CLEAR,

    /**
     * 
     */
    COLLECT,

    /**
     * 
     */
    CSI_DISPATCH,

    /**
     * 
     */
    ESC_DISPATCH,

    /**
     * 
     */
    EXECUTE,

    /**
     * 
     */
    HOOK,

    /**
     * 
     */
    IGNORE,

    /**
     * 
     */
    OSC_END,

    /**
     * 
     */
    OSC_PUT,

    /**
     * 
     */
    OSC_START,

    /**
     * 
     */
    PARAM,

    /**
     * 
     */
    PRINT,

    /**
     * 
     */
    PUT,

    /**
     * 
     */
    UNHOOK;

    /**
     * @param a_ordinal
     * @return the action at the specified ordinal
     */
    public static Action fromOrdinal(final int a_ordinal)
    {
        for (Action v : Action.values())
        {
            if (v.ordinal() == a_ordinal)
            {
                return v;
            }
        }

        return null;
    }

    /**
     * @param a_change
     * @return the action based upon a masked Action/State mask
     */
    public static Action fromMask(final int a_change)
    {
        return fromOrdinal(a_change & 0x0F);
    }
}
