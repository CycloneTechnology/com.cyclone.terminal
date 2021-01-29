package com.cyclone.terminal.parser;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class Parser implements ParseTable
{
    static final int MAX_INTERMEDIATE_CHARS = 2;

    private State state;

    private final char[] intermediateChars = new char[MAX_INTERMEDIATE_CHARS + 1];

    private int intermediateCharPos;

    private boolean ignoreFlagged;

    private int[] params = new int[256];

    private int numParams;

    /**
     * 
     */
    public Parser()
    {
        state = State.GROUND;
        intermediateCharPos = 0;
        intermediateChars[intermediateCharPos] = '\0';
        numParams = 0;
        ignoreFlagged = false;
    }

    /**
     * @param a_parser
     * @param a_action
     * @param a_ch
     */
    public abstract void execute(Parser a_parser, final Action a_action,
            final char a_ch);

    private void clear()
    {
        intermediateCharPos = 0;
        intermediateChars[intermediateCharPos] = '\0';
        numParams = 0;
        ignoreFlagged = false;
    }

    /**
     * @param a_data
     * @param a_length
     */
    public final void parse(final byte[] a_data, final int a_length)
    {
        for (int i = 0; i < a_length; i++)
        {
            parse((char) (a_data[i] & 0xff));
        }
    }

    /**
     * @param a_data
     */
    public final void parse(char a_data)
    {
        /*
         * If a transition is defined from the "anywhere" state, always use
         * that. Otherwise use the transition from the current state.
         */

        int change = 0;
        try
        {
            change = STATE_TABLE[State.ANYWHERE.ordinal()][a_data];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("STATE_TABLE ERROR (ANYWHERE) " + a_data);
        }

        if (change == 0)
        {
            try
            {
                change = STATE_TABLE[state.ordinal()][a_data];
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("STATE_TABLE ERROR (" + state.ordinal()
                        + ") " + a_data);
            }
        }

        doStateChange(this, change, a_data);
    }

    /**
     * Some actions we handle internally (like parsing parameters), others we
     * hand to our client for processing.
     * 
     * @param a_parser
     * @param a_action
     * @param a_ch
     */
    private static void doAction(Parser a_parser, final Action a_action,
            final char a_ch)
    {
        switch (a_action)
        {
            case NONE:
            case PRINT:
            case EXECUTE:
            case HOOK:
            case PUT:
            case OSC_START:
            case OSC_PUT:
            case OSC_END:
            case UNHOOK:
            case CSI_DISPATCH:
            case ESC_DISPATCH:
                a_parser.execute(a_parser, a_action, a_ch);
                break;

            case IGNORE:
                /* do nothing */
                break;

            case COLLECT:
                // Append the character to the intermediate params
                if (a_parser.getIntermediateCharPos()
                        + 1 > MAX_INTERMEDIATE_CHARS)
                {
                    a_parser.setIgnoreFlagged(true);
                }
                else
                {
                    a_parser.addIntermediateChar(a_ch);
                }

                break;

            case PARAM:
                // process the param character
                if (a_ch == ';')
                {
                    a_parser.incrementNumParams();
                    a_parser.setParam(a_parser.getNumParams() - 1, 0);
                }
                else
                {
                    /* the character is a digit */
                    if (a_parser.getNumParams() == 0)
                    {
                        a_parser.setNumParams(1);
                        a_parser.setParam(0, 0);
                    }

                    final int currentParamIndex = a_parser.getNumParams() - 1;
                    final int currentParamValue = a_parser
                            .getParam(currentParamIndex);
                    a_parser.setParam(currentParamIndex,
                            (currentParamValue * 10) + (a_ch - '0'));
                }

                break;

            case CLEAR:
                a_parser.clear();
                break;

            default:
                System.out.println(
                        "Internal error, unknown action " + a_action.ordinal());
        }
    }

    private static void doStateChange(final Parser a_parser, final int a_change,
            final char a_ch)
    {
        // A state change is an action and/or a new state to transition to.
        final State newState = State.fromMask(a_change);
        final Action action = Action.fromMask(a_change);

        if (newState != State.ANYWHERE)
        {
            // Perform up to three actions:
            //
            // 1. the exit action of the old state.
            //
            // 2. the action associated with the transition
            //
            // 3. the entry action of the new action

            final Action exitAction = EXIT_ACTIONS[a_parser.getState()
                    .ordinal()];
            final Action entryAction = ENTRY_ACTIONS[newState.ordinal()];

            if (exitAction != Action.NONE)
            {
                doAction(a_parser, exitAction, '\0');
            }

            if (action != Action.NONE)
            {
                doAction(a_parser, action, a_ch);
            }

            if (entryAction != Action.NONE)
            {
                doAction(a_parser, entryAction, '\0');
            }

            a_parser.setState(newState);
        }
        else
        {
            doAction(a_parser, action, a_ch);
        }
    }

    /**
     * @return if ignore further intermediate characters
     */
    public final boolean isIgnoreFlagged()
    {
        return ignoreFlagged;
    }

    /**
     * @param a_ignoreFlagged
     */
    public final void setIgnoreFlagged(boolean a_ignoreFlagged)
    {
        ignoreFlagged = a_ignoreFlagged;
    }

    /**
     * @return the current intermediate character position
     */
    public final int getIntermediateCharPos()
    {
        return intermediateCharPos;
    }

    /**
     * @param a_intermediateCharPos
     */
    public final void setIntermediateCharPos(int a_intermediateCharPos)
    {
        intermediateCharPos = a_intermediateCharPos;
    }

    /**
     * @return the intermediate characters
     */
    public final char[] getIntermediateChars()
    {
        return intermediateChars;
    }

    /**
     * @param a_ch
     */
    public final void addIntermediateChar(final char a_ch)
    {
        intermediateChars[intermediateCharPos++] = a_ch;
    }

    /**
     * 
     */
    public final void clearIntermediateChars()
    {
        intermediateCharPos = 0;
        intermediateChars[intermediateCharPos] = '\0';
    }

    /**
     * @return the number of params
     */
    public final int getNumParams()
    {
        return numParams;
    }

    /**
     * @param a_numParams
     */
    public final void setNumParams(int a_numParams)
    {
        numParams = a_numParams;
    }

    /**
     * 
     */
    public final void incrementNumParams()
    {
        numParams++;
    }

    /**
     * @return the array of params
     */
    public final int[] getParams()
    {
        return params;
    }

    /**
     * @param a_index
     * @return the param at the specified index
     */
    public final int getParam(final int a_index)
    {
        return params[a_index];
    }

    /**
     * @param a_params
     */
    public final void setParams(int[] a_params)
    {
        params = a_params;
    }

    /**
     * @param a_index
     * @param a_value
     */
    public final void setParam(final int a_index, int a_value)
    {
        params[a_index] = a_value;
    }

    /**
     * @return the state of the parser
     */
    public final State getState()
    {
        return state;
    }

    /**
     * @param a_state
     */
    public final void setState(State a_state)
    {
        state = a_state;
    }
}
