package com.cyclone.terminal.parser;

/**
 * @author Phil.Baxter
 * 
 */
public enum State
{
    /**
     * 
     */
    ANYWHERE,

    /**
     * 
     */
    CSI_ENTRY,

    /**
     * 
     */
    CSI_IGNORE,

    /**
     * 
     */
    CSI_INTERMEDIATE,

    /**
     * 
     */
    CSI_PARAM,

    /**
     * 
     */
    DCS_ENTRY,

    /**
     * 
     */
    DCS_IGNORE,

    /**
     * 
     */
    DCS_INTERMEDIATE,

    /**
     * 
     */
    DCS_PARAM,

    /**
     * 
     */
    DCS_PASSTHROUGH,

    /**
     * 
     */
    ESCAPE,

    /**
     * 
     */
    ESCAPE_INTERMEDIATE,

    /**
     * 
     */
    GROUND,

    /**
     * 
     */
    OSC_STRING,

    /**
     * 
     */
    SOS_PM_APC_STRING;

    /**
     * @param a_ordinal
     * @return the state at the specified ordinal
     */
    public static State fromOrdinal(final int a_ordinal)
    {
        for (State v : State.values())
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
     * @return the state based upon a masked Action/State mask
     */
    public static State fromMask(final int a_change)
    {
        return fromOrdinal(a_change >> 4);
    }

}
