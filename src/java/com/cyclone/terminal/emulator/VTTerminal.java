/**
 *
 */
package com.cyclone.terminal.emulator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.cyclone.terminal.emulator.cell.LineHeight;
import com.cyclone.terminal.emulator.cell.LineWidth;
import com.cyclone.terminal.parser.Action;
import com.cyclone.terminal.parser.ParseTable;
import com.cyclone.terminal.parser.Parser;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class VTTerminal extends VT300
{
    // Need to special case ESC Y as it has two trailing values (line and
    // column)
    private static boolean s_ProcessingESCY;

    private static boolean s_ProcessingESCYhaveFirst;

    private static int s_ESCYfirst;

    /**
     * @param a_parent
     */
    public VTTerminal(final Composite a_parent)
    {
        super(a_parent);

        s_ProcessingESCY = false;
        s_ProcessingESCYhaveFirst = false;
        s_ESCYfirst = 0;
    }

    /**
     * @param a_parent
     * @param a_width
     * @param a_height
     * @param a_history
     */
    public VTTerminal(final Composite a_parent, final int a_width,
            final int a_height, final int a_history)
    {
        super(a_parent, a_width, a_height, a_history);
    }

    private void dump(final Parser a_parser, final Action a_action,
            final char a_ch)
    {

        System.out.println("Received action "
                + ParseTable.ACTION_NAMES[a_action.ordinal()] + " char:" + a_ch
                + "(" + Integer.toHexString(a_ch) + ")");
        for (int i = 0; i < a_parser.getIntermediateCharPos(); i++)
        {
            if (i == 0)
            {
                System.out.print("Intermediate chars: ");
            }
            System.out.print(a_parser.getIntermediateChars()[i] + "("
                    + Integer.toHexString(a_parser.getIntermediateChars()[i])
                    + ")");
        }
        System.out.println(a_parser.getNumParams() + " Parameters: ");
        for (int i = 0; i < a_parser.getNumParams(); i++)
        {
            System.out.println("\t" + a_parser.getParam(i));
        }
        System.out.println();

    }

    /**
     * @see com.cyclone.terminal.parser.Parser#execute(com.cyclone.terminal.parser.Parser,
     *      com.cyclone.terminal.parser.Action, char)
     */
    @Override
    public final void execute(Parser a_parser, Action a_action, char a_ch)
    {
        try
        {
            display(a_parser, a_action, a_ch);
        }
        catch (EmulatorException e)
        {
            addMessageToDataScope(e.getMessage());
        }
    }

    /**
     * @param a_parser
     * @param a_action
     * @param a_ch
     * @throws EmulatorException
     */
    public final void display(final Parser a_parser, final Action a_action,
            final char a_ch) throws EmulatorException
    {

        switch (a_action)
        {
            case NONE:
                // Are we processign a VT52 Escape-Y which is followed by two
                // parameters (line, column)
                if (s_ProcessingESCY)
                {
                    // Yes we are, is this the first parameter?
                    if (!s_ProcessingESCYhaveFirst)
                    {
                        // Yes, store it and set the flag indicating we have got
                        // it
                        s_ESCYfirst = a_ch - 31;
                        s_ProcessingESCYhaveFirst = true;
                    }
                    else
                    {
                        // No, it must be the second, so, execute the command
                        // and reset our state back to normal
                        doDCA(s_ESCYfirst, a_ch - 31);

                        s_ProcessingESCYhaveFirst = false;
                        s_ProcessingESCY = false;
                    }
                }
                else
                {
                    putCharacter(a_ch);
                }
                break;
            default:
                dump(a_parser, a_action, a_ch);
                break;
            // case CLEAR:
            // break;
            // case COLLECT:
            // break;
            case CSI_DISPATCH:
                // StringBuilder sb = new StringBuilder();
                // sb.append("CSI_DISPATCH : " + a_ch);
                // sb.append(" [" + a_parser.getNumParams() + " params]");
                // final int[] params = a_parser.getParams();
                // for (int i = 0; i < a_parser.getNumParams(); i++)
                // {
                // if (i != 0)
                // {
                // sb.append(", ");
                // }
                // sb.append(params[i]);
                // }
                // System.out.println(sb.toString());

                switch (a_ch)
                {
                    case 'A':
                        doCUU(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'B':
                        doCUD(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'C':
                        doCUF(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'D':
                        doCUB(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'H':
                        doCUP(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'J':
                        switch (a_parser.getIntermediateCharPos())
                        {
                            default:
                            case 0:
                                doED(a_parser.getNumParams(),
                                        a_parser.getParams());
                                break;
                            case 1:
                                switch (a_parser.getIntermediateChars()[0])
                                {
                                    case '?':
                                        doDECSED(a_parser.getNumParams(),
                                                a_parser.getParams());
                                        break;
                                    default:
                                        throw new EmulatorException(
                                                "Escape Sequence for "
                                                        + a_ch
                                                        + " (intermediate "
                                                        + a_parser
                                                                .getIntermediateChars()[0]
                                                        + ") NOT IMPLEMENTED!");
                                }
                        }
                        break;
                    case 'K':
                        switch (a_parser.getIntermediateCharPos())
                        {
                            default:
                            case 0:
                                doEL(a_parser.getNumParams(),
                                        a_parser.getParams());
                                break;
                            case 1:
                                switch (a_parser.getIntermediateChars()[0])
                                {
                                    case '?':
                                        doDECSEL(a_parser.getNumParams(),
                                                a_parser.getParams());
                                        break;
                                    default:
                                        throw new EmulatorException(
                                                "Escape Sequence for "
                                                        + a_ch
                                                        + " (intermediate "
                                                        + a_parser
                                                                .getIntermediateChars()[0]
                                                        + ") NOT IMPLEMENTED!");
                                }
                                break;
                        }
                        break;
                    case 'X':
                        doECH(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'c':
                        resetDevice();
                        break;
                    case 'f':
                        doCUP(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'g':
                        doClearTab(a_parser.getNumParams(),
                                a_parser.getParams());
                        break;
                    case 'h':
                        switch (a_parser.getIntermediateCharPos())
                        {
                            default:
                                throw new EmulatorException(
                                        "Escape Sequence for "
                                                + a_ch
                                                + " (intermediate "
                                                + a_parser
                                                        .getIntermediateChars()[0]
                                                + ") NOT IMPLEMENTED!");
                            case 0:
                                // ANSI modes
                                doSM(a_parser.getNumParams(),
                                        a_parser.getParams());
                                break;
                            case 1:
                                switch (a_parser.getIntermediateChars()[0])
                                {
                                    case '?':
                                        // Digital private modes
                                        setMode(a_parser.getNumParams(),
                                                a_parser.getParams());
                                        break;
                                    default:
                                        throw new EmulatorException(
                                                "Escape Sequence for "
                                                        + a_ch
                                                        + " (intermediate "
                                                        + a_parser
                                                                .getIntermediateChars()[0]
                                                        + ") NOT IMPLEMENTED!");
                                }
                                break;
                        }
                        break;
                    case 'l':
                        switch (a_parser.getIntermediateCharPos())
                        {
                            default:
                            case 0:
                                // ANSI modes
                                doRM(a_parser.getNumParams(),
                                        a_parser.getParams());
                                break;
                            case 1:
                                switch (a_parser.getIntermediateChars()[0])
                                {
                                    case '?':
                                        // Digital private modes
                                        resetMode(a_parser.getNumParams(),
                                                a_parser.getParams());
                                        break;
                                    default:
                                        throw new EmulatorException(
                                                "Escape Sequence for "
                                                        + a_ch
                                                        + " (intermediate "
                                                        + a_parser
                                                                .getIntermediateChars()[0]
                                                        + ") NOT IMPLEMENTED!");
                                }
                                break;
                        }
                        break;
                    case 'm':
                        doSGR(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'n':
                        doDSR(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'p':
                        switch (a_parser.getIntermediateCharPos())
                        {
                            default:
                            case 0:
                                setColumns(132);
                                break;
                            case 1:
                                switch (a_parser.getIntermediateChars()[0])
                                {
                                    case '!':
                                        doDECSTR();
                                        break;
                                    case '\"':
                                        doDECSCL(a_parser.getNumParams(),
                                                a_parser.getParams());
                                        break;
                                    default:
                                        throw new EmulatorException(
                                                "Escape Sequence for "
                                                        + a_ch
                                                        + " (intermediate "
                                                        + a_parser
                                                                .getIntermediateChars()[0]
                                                        + ") NOT IMPLEMENTED!");
                                }
                                break;
                        }
                        break;
                    case 'q':
                        doDECLL(a_parser.getNumParams(), a_parser.getParams());
                        break;
                    case 'r':
                        doSetScrollingRegion(a_parser.getNumParams(),
                                a_parser.getParams());
                        break;
                    case 's':
                        switch (a_parser.getIntermediateCharPos())
                        {
                            case 0:
                                doDECSLRM(a_parser.getNumParams(),
                                        a_parser.getParams());
                                break;
                            default:
                                throw new EmulatorException(
                                        "Escape Sequence for "
                                                + a_ch
                                                + " (intermediate "
                                                + a_parser
                                                        .getIntermediateChars()[0]
                                                + ") NOT IMPLEMENTED!");
                        }
                        break;
                    case 'u':
                        doUnsaveCursor();
                        break;
                    case '8':
                        doUnsaveCursor();
                        break;
                    default:
                        throw new EmulatorException("Escape Sequence for "
                                + a_ch + " NOT IMPLEMENTED!");
                }
                break;
            case ESC_DISPATCH:
                // System.out.println("ESC_DISPATCH : " + a_ch);

                switch (a_ch)
                {
                    case '0':
                        // Switch Character Set to DEC special graphics if
                        // intermediate character is ( or )
                        switch (a_parser.getIntermediateChars()[0])
                        {
                            case '(':
                                selectCharacterSet(CharacterSetSequence.G0,
                                        CharacterSet.SPECIALGRAPHICS);
                                break;
                            case ')':
                                selectCharacterSet(CharacterSetSequence.G1,
                                        CharacterSet.SPECIALGRAPHICS);
                                break;
                            case '*':
                                selectCharacterSet(CharacterSetSequence.G2,
                                        CharacterSet.SPECIALGRAPHICS);
                                break;
                            case '+':
                                selectCharacterSet(CharacterSetSequence.G3,
                                        CharacterSet.SPECIALGRAPHICS);
                                break;
                            default:
                                break;
                        }
                        break;
                    case '1':
                        // Switch Character Set to Standard Alternate if
                        // intermediate character is ( or )
                        switch (a_parser.getIntermediateChars()[0])
                        {
                            case '(':
                                selectCharacterSet(CharacterSetSequence.G0,
                                        CharacterSet.ALTERNATESET_STANDARD);
                                break;
                            case ')':
                                selectCharacterSet(CharacterSetSequence.G1,
                                        CharacterSet.ALTERNATESET_STANDARD);
                                break;
                            case '*':
                                selectCharacterSet(CharacterSetSequence.G2,
                                        CharacterSet.ALTERNATESET_STANDARD);
                                break;
                            case '+':
                                selectCharacterSet(CharacterSetSequence.G3,
                                        CharacterSet.ALTERNATESET_STANDARD);
                                break;
                            default:
                                break;
                        }
                        break;
                    case '2':
                        // Switch Character Set to Alternate special graphics if
                        // intermediate character is ( or )
                        switch (a_parser.getIntermediateChars()[0])
                        {
                            case '(':
                                selectCharacterSet(
                                        CharacterSetSequence.G0,
                                        CharacterSet.ALTERNATESET_SPECIALGRAPHICS);
                                break;
                            case ')':
                                selectCharacterSet(
                                        CharacterSetSequence.G1,
                                        CharacterSet.ALTERNATESET_SPECIALGRAPHICS);
                                break;
                            case '*':
                                selectCharacterSet(
                                        CharacterSetSequence.G2,
                                        CharacterSet.ALTERNATESET_SPECIALGRAPHICS);
                                break;
                            case '+':
                                selectCharacterSet(
                                        CharacterSetSequence.G3,
                                        CharacterSet.ALTERNATESET_SPECIALGRAPHICS);
                                break;
                            default:
                                break;
                        }
                        break;

                    case '3':
                        doDECDHL(LineHeight.TOP);
                        break;
                    case '4':
                        doDECDHL(LineHeight.BOTTOM);
                        break;
                    case '5':
                        doDECDHL(LineHeight.NORMAL);
                        break;
                    case '6':
                        doDECDWL(LineWidth.DOUBLE);
                        break;
                    case '7':
                        doDECSC();
                        break;
                    case '8':
                        if (a_parser.getIntermediateChars()[0] == '#')
                        {
                            doDECALN(a_parser.getNumParams(),
                                    a_parser.getParams());
                        }
                        break;
                    case 'A':
                        if (a_parser.getIntermediateCharPos() != 0)
                        {
                            switch (a_parser.getIntermediateChars()[0])
                            {
                                case '(':
                                    selectCharacterSet(CharacterSetSequence.G0,
                                            CharacterSet.UK);
                                    break;
                                case ')':
                                    selectCharacterSet(CharacterSetSequence.G1,
                                            CharacterSet.UK);
                                    break;
                                case '*':
                                    selectCharacterSet(CharacterSetSequence.G2,
                                            CharacterSet.UK);
                                    break;
                                case '+':
                                    selectCharacterSet(CharacterSetSequence.G3,
                                            CharacterSet.UK);
                                    break;
                                default:
                                    break;
                            }
                        }
                        else
                        {
                            cursorUp();
                        }
                        break;
                    case 'B':
                        if (a_parser.getIntermediateCharPos() != 0)
                        {
                            switch (a_parser.getIntermediateChars()[0])
                            {
                                case '(':
                                    selectCharacterSet(CharacterSetSequence.G0,
                                            CharacterSet.USASCII);
                                    break;
                                case ')':
                                    selectCharacterSet(CharacterSetSequence.G1,
                                            CharacterSet.USASCII);
                                    break;
                                case '*':
                                    selectCharacterSet(CharacterSetSequence.G2,
                                            CharacterSet.USASCII);
                                    break;
                                case '+':
                                    selectCharacterSet(CharacterSetSequence.G3,
                                            CharacterSet.USASCII);
                                    break;
                                default:
                                    break;
                            }
                        }
                        else
                        {
                            cursorDown();
                        }
                        break;
                    case 'C':
                        cursorRight();
                        break;
                    case 'D':
                        cursorLeft();
                        break;
                    case 'E':
                        cursorDown();
                        break;
                    case 'F':
                        enterGraphicsMode();
                        break;
                    case 'G':
                        exitGraphicsMode();
                        break;
                    case 'H':
                        cursorHome();
                        break;
                    case 'I':
                        reverseLineFeed();
                        break;
                    case 'J':
                        eraseToEndOfScreen();
                        break;
                    case 'K':
                        eraseToEndOfLine();
                        break;
                    case 'M':
                        cursorUp();
                        break;
                    case 'Y':
                        s_ProcessingESCY = true;
                        break;
                    case 'Z':
                        doDECID();
                        break;
                    case '>':
                        doResetKeypadMode();
                        break;
                    case '<':
                        enterANSIMode();
                        break;
                    case '=':
                        doSetKeypadMode();
                        break;
                    default:
                        throw new EmulatorException(
                                "Escape Sequence (ESC_DISPATCH) for " + a_ch
                                        + " (intermediate "
                                        + a_parser.getIntermediateChars()[0]
                                        + ") NOT IMPLEMENTED!");
                }
                break;
            case EXECUTE:
                switch (a_ch)
                {
                    case 0:
                        // NUL - Ignore
                        break;
                    case 7:
                        // Bell, make a noise
                        getDisplay().beep();
                        break;
                    case 14:
                        // SO - Shift out : locking shift 1, GL encodes G1 from
                        // now on
                        selectCharacterSet(CharacterSetSequence.G1,
                                CharacterSet.SPECIALGRAPHICS);
                        break;
                    case 15:
                        // SI - Shift in : locking shift 0, GL encodes G0 from
                        // now on
                        selectCharacterSet(CharacterSetSequence.G0,
                                CharacterSet.USASCII);
                        break;
                    case SWT.CR:
                        performCR();
                        break;
                    case SWT.LF:
                        performLF();
                        break;
                    case SWT.BS: // BS
                        performBS();
                        break;
                    case SWT.TAB: // TAB
                        performTAB();
                        break;
                    default:
                        addMessageToDataScope("Received " + (int) a_ch
                                + " DONT KNOW WHAT TO DO!");
                        break;
                }
                break;
            // case HOOK:
            // break;
            // case IGNORE:
            // break;
            // case OSC_END:
            // break;
            // case OSC_PUT:
            // break;
            // case OSC_START:
            // break;
            // case PARAM:
            // break;
            case PRINT:
                // Are we processign a VT52 Escape-Y which is followed by two
                // parameters (line, column)
                if (s_ProcessingESCY)
                {
                    // Yes we are, is this the first parameter?
                    if (!s_ProcessingESCYhaveFirst)
                    {
                        // Yes, store it and set the flag indicating we have got
                        // it
                        s_ESCYfirst = a_ch - 31;
                        s_ProcessingESCYhaveFirst = true;
                    }
                    else
                    {
                        // No, it must be the second, so, execute the command
                        // and reset our state back to normal
                        doDCA(s_ESCYfirst, a_ch - 31);

                        s_ProcessingESCYhaveFirst = false;
                        s_ProcessingESCY = false;
                    }
                }
                else
                {
                    // System.out.println("  Character : " + a_ch);
                    putCharacter(a_ch);
                }
                break;
        // case PUT:
        // break;
        // case UNHOOK:
        // break;
        }
    }

    private void setMode(final int a_numParams, final int[] a_params)
            throws EmulatorException
    {
        for (int iParam = 0; iParam < a_numParams; iParam++)
        {
            switch (a_params[iParam])
            {
                case 0:
                    // Error condition, ignore
                    break;
                case 1:
                    // Application Cursor Keys
                    setApplicationCursorKeys(true);
                    break;
                case 3:
                    // Set mode to 132 Columns
                    setColumns(132);
                    break;
                case 4:
                    // Smooth Scroll, this is a noop in this version of the
                    // emulator
                    break;
                case 5:
                    setReverseVideo(true);
                    break;
                case 6:
                    setOrigin(true);
                    break;
                case 7:
                    getLogicalScreen().getCursor().setAutoWrap(true);
                    break;
                case 8:
                    setAutoRepeat(true);
                    break;
                case 20:
                    setLinefeed(true);
                    break;
                case 25:
                    setInvisibleCursor(false);
                    break;
                case 40:
                    setAllowWidthChange(true);
                    break;
                case 45:
                    getLogicalScreen().getCursor().setReverseAutoWrap(true);
                    break;
                default:
                    throw new EmulatorException(
                            "DEC Private SetMode NOT implemented for "
                                    + a_params[iParam]);
            }
        }
    }

    private void resetMode(final int a_numParams, final int[] a_params)
            throws EmulatorException
    {
        for (int iParam = 0; iParam < a_numParams; iParam++)
        {
            switch (a_params[iParam])
            {
                case 0:
                    // Error condition, ignore
                    break;
                case 1:
                    // Normal Cursor Keys
                    setApplicationCursorKeys(false);
                    break;
                case 3:
                    // Set mode to 80 Columns
                    setColumns(80);
                    break;
                case 4:
                    // Jump Scroll, this is a noop in this version of the
                    // emulator
                    break;
                case 5:
                    setReverseVideo(false);
                    break;
                case 6:
                    setOrigin(false);
                    break;
                case 7:
                    getLogicalScreen().getCursor().setAutoWrap(false);
                    break;
                case 8:
                    setAutoRepeat(false);
                    break;
                case 20:
                    setLinefeed(false);
                    break;
                case 25:
                    setInvisibleCursor(true);
                    break;
                case 40:
                    setAllowWidthChange(false);
                    break;
                case 45:
                    getLogicalScreen().getCursor().setReverseAutoWrap(false);
                    break;
                default:
                    throw new EmulatorException(
                            "DEC Private ResetMode NOT implemented for "
                                    + a_params[iParam]);
            }
        }
    }
}
