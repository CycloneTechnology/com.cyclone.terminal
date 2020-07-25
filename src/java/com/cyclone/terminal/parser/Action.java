package com.cyclone.terminal.parser;

import java.util.Arrays;

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
        return Arrays.asList(Action.values()).stream()
                .filter(action -> action.ordinal() == a_ordinal).findAny()
                .orElse(null);
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
