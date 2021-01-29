package com.cyclone.terminal.emulator;

/**
 * @author Phil.Baxter
 * 
 */
public class EmulatorException extends Exception
{
    private static final long serialVersionUID = -2437229690957351316L;

    /**
     * Constructor for EmulatorException.
     * 
     * @param a_Message
     */
    public EmulatorException(final String a_Message)
    {
        super(a_Message);
    }
}
