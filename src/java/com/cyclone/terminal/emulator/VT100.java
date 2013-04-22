/**
 *
 */
package com.cyclone.terminal.emulator;

import org.eclipse.swt.widgets.Composite;

import com.cyclone.terminal.emulator.cell.BlinkState;
import com.cyclone.terminal.emulator.cell.Cell;
import com.cyclone.terminal.emulator.cell.LineHeight;
import com.cyclone.terminal.emulator.cell.LineWidth;

/**
 * @author Phil.Baxter
 *
 */
public abstract class VT100 extends VT52
{
    /**
     * @param a_parent
     */
    public VT100(final Composite a_parent)
    {
        super(a_parent);
    }

    /**
     * @param a_parent
     * @param a_width
     * @param a_height
     * @param a_history
     */
    public VT100(final Composite a_parent, final int a_width,
            final int a_height, final int a_history)
    {
        super(a_parent, a_width, a_height, a_history);
    }

    /**
     * CPR - Cursor Position Report - VT100 to Host.
     *
     * ESC [ Pn ; Pn R
     *
     * default value: 1
     *
     * The CPR sequence reports the active position by means of the parameters.
     * This sequence has two parameter values, the first specifying the line and
     * the second specifying the column. The default condition with no
     * parameters present, or parameters of 0, is equivalent to a cursor at home
     * position.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doCPR(final int a_numParams, final int[] a_params)
    {
        // Response should be <ESC>[Pl;PcR (pl = line, Pc = column
        final StringBuilder sb = new StringBuilder();
        sb.append(ESC);
        sb.append('[');
        sb.append(getLogicalScreen().getCursor().getRow());
        sb.append(';');
        sb.append(getLogicalScreen().getCursor().getColumn());
        sb.append('R');

        onTerminalData(sb.toString().getBytes());
    }

    /**
     * CUB - Cursor Backward - Host to VT100 and VT100 to Host
     *
     * ESC [ Pn D
     *
     * default value: 1
     *
     * The CUB sequence moves the active position to the left. The distance
     * moved is determined by the parameter. If the parameter value is zero or
     * one, the active position is moved one position to the left. If the
     * parameter value is n, the active position is moved n positions to the
     * left. If an attempt is made to move the cursor to the left of the left
     * margin, the cursor stops at the left margin. Editor Function.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doCUB(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" CUB : 1");
                getLogicalScreen().getCursor().left();
                break;
            case 1:
                // System.out.println(" CUB : " + a_params[0]);
                getLogicalScreen().getCursor().left(a_params[0]);
                break;
        }
    }

    /**
     * CUD - Cursor Down - Host to VT100 and VT100 to Host
     *
     * ESC [ Pn B
     *
     * default value: 1
     *
     * The CUD sequence moves the active position downward without altering the
     * column position. The number of lines moved is determined by the
     * parameter. If the parameter value is zero or one, the active position is
     * moved one line downward. If the parameter value is n, the active position
     * is moved n lines downward. In an attempt is made to move the cursor below
     * the bottom margin, the cursor stops at the bottom margin. Editor
     * Function.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doCUD(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" CUD : 1");
                getLogicalScreen().getCursor().down();
                break;
            case 1:
                // System.out.println(" CUD : " + a_params[0]);
                getLogicalScreen().getCursor().down(a_params[0]);
                break;
        }
    }

    /**
     * CUF - Cursor Forward - Host to VT100 and VT100 to Host
     *
     * ESC [ Pn C
     *
     * default value: 1
     *
     * The CUF sequence moves the active position to the right. The distance
     * moved is determined by the parameter. A parameter value of zero or one
     * moves the active position one position to the right. A parameter value of
     * n moves the active position n positions to the right. If an attempt is
     * made to move the cursor to the right of the right margin, the cursor
     * stops at the right margin. Editor Function.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doCUF(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" CUF : 1");
                getLogicalScreen().getCursor().right();
                break;
            case 1:
                // System.out.println(" CUF : " + a_params[0]);
                getLogicalScreen().getCursor().right(a_params[0]);
                break;
        }
    }

    /**
     * CUP - Cursor Position
     *
     * ESC [ Pn ; Pn H
     *
     * default value: 1
     *
     * The CUP sequence moves the active position to the position specified by
     * the parameters.
     * <p>
     * This sequence has two parameter values, the first specifying the line
     * position and the second specifying the column position.
     * <p>
     * A parameter value of zero or one for the first or second parameter moves
     * the active position to the first line or column in the display,
     * respectively. The default condition with no parameters present is
     * equivalent to a cursor to home action.
     * <p>
     * In the VT100, this control behaves identically with its format effector
     * counterpart, HVP.
     * <p>
     * Editor Function
     * <p>
     * The numbering of lines depends on the state of the Origin Mode (DECOM).
     *
     * @param a_numParams the number of parameters present in the escape
     *            sequence
     * @param a_params the array of parameters
     */
    public final void doCUP(final int a_numParams, final int[] a_params)
    {
        final int row;
        final int column;

        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" CUP : 0, 0");
                row = 0;
                column = 0;
                break;
            case 1:
                // System.out.println(" CUP : " + (a_params[0] - 1) + ", 0");
                row = a_params[0] - 1;
                column = 0;
                break;
            case 2:
                // System.out.println(" CUP : " + (a_params[0] - 1) + ", "
                // + (a_params[1] - 1));
                row = a_params[0] - 1;
                column = a_params[1] - 1;
                break;
        }

        getLogicalScreen().getCursor().setPosition(row, column);
    }

    /**
     * CUU - Cursor Up - Host to VT100 and VT100 to Host ESC [ Pn A
     *
     * default value: 1
     *
     * Moves the active position upward without altering the column position.
     * The number of lines moved is determined by the parameter. A parameter
     * value of zero or one moves the active position one line upward. A
     * parameter value of n moves the active position n lines upward. If an
     * attempt is made to move the cursor above the top margin, the cursor stops
     * at the top margin. Editor Function
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doCUU(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" CUU : 1");
                getLogicalScreen().getCursor().up();
                break;
            case 1:
                // System.out.println(" CUU : " + a_params[0]);
                getLogicalScreen().getCursor().up(a_params[0]);
                break;
        }
    }

    /**
     * DA - Device Attributes
     *
     * ESC [ Pn c
     *
     * default value: 0
     *
     * The host requests the VT100 to send a device attributes (DA) control
     * sequence to identify itself by sending the DA control sequence with
     * either no parameter or a parameter of 0. Response to the request
     * described above (VT100 to host) is generated by the VT100 as a DA control
     * sequence with the numeric parameters as follows:
     * <ul>
     * <li>Option Present Sequence Sent
     * <li>No options ESC [?1;0c
     * <li>Processor option (STP) ESC [?1;1c
     * <li>Advanced video option (AVO) ESC [?1;2c
     * <li>AVO and STP ESC [?1;3c
     * <li>Graphics option (GPO) ESC [?1;4c
     * <li>GPO and STP ESC [?1;5c
     * <li>GPO and AVO ESC [?1;6c
     * <li>GPO, STP and AVO ESC [?1;7c
     * </ul>
     *
     * @param a_P1
     * @param a_P2
     */
    public final void doDA(final int a_P1, final int a_P2)
    {
        // Response should be <ESC>[Pl;PcR (pl = line, Pc = column
        final StringBuilder sb = new StringBuilder();
        sb.append(ESC);
        sb.append('[');
        sb.append('?');
        sb.append('1');
        sb.append(';');
        sb.append('0');
        sb.append('c');

        onTerminalData(sb.toString().getBytes());
    }

    /**
     * DECLL - Load LEDS (DEC Private)
     *
     * ESC [ Ps q
     *
     * default value: 0
     *
     * Load the four programmable LEDs on the keyboard according to the
     * parameter(s).
     *
     * <ul>
     * <li>Parameter Parameter Meaning
     * <li>0 Clear LEDs L1 through L4
     * <li>1 Light L1
     * <li>2 Light L2
     * <li>3 Light L3
     * <li>4 Light L4
     * </ul>
     *
     * LEDPanel numbers are indicated on the keyboard.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doDECLL(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                getLEDPanel().setLED(0, false);
                getLEDPanel().setLED(1, false);
                getLEDPanel().setLED(2, false);
                getLEDPanel().setLED(3, false);
                break;
            case 1:
                switch (a_params[0])
                {
                    default:
                    case 0:
                        getLEDPanel().setLED(0, false);
                        getLEDPanel().setLED(1, false);
                        getLEDPanel().setLED(2, false);
                        getLEDPanel().setLED(3, false);
                        break;
                    case 1:
                        getLEDPanel().setLED(0, true);
                        break;
                    case 2:
                        getLEDPanel().setLED(1, true);
                        break;
                    case 3:
                        getLEDPanel().setLED(2, true);
                        break;
                    case 4:
                        getLEDPanel().setLED(3, true);
                        break;
                }
                break;
        }
    }

    /**
     * ECH - Erase CharImage, Erase the number of characters specified, starting
     * at the cursor position. The cursor remain in the same position.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doECH(final int a_numParams, final int[] a_params)
    {
        final int row = getLogicalScreen().getCursor().getRow();
        final int startColumn = getLogicalScreen().getCursor().getColumn();
        final int endColumn;

        switch (a_numParams)
        {
            default:
            case 0:
                endColumn = startColumn + 1;
                break;
            case 1:
                switch (a_params[0])
                {
                    case 0:
                    case 1:
                        endColumn = startColumn + 1;
                        break;
                    default:
                        endColumn = startColumn + a_params[0];
                        break;
                }
                break;
        }
        eraseCells(row, startColumn, row, endColumn);
    }

    /**
     * EL - Erase in line.
     *
     * @param a_numParams the number of parameters present in our parameter
     *            array
     * @param a_params an array containing the portion of line to erase, only
     *            position 0 if present is valid and should only have one of the
     *            following values...
     *            <ul>
     *            <li>0 (default) - Erase from the cursor to the end of the
     *            line, (including the cursor position). Line attribute are not
     *            affected.
     *            <li>1 - Erase from the beginning of the line to the cursor
     *            (including the cursor position). Line attribute are not
     *            affected.
     *            <li>2 - Erase the complete line.
     *            </ul>
     */
    public final void doEL(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" EL : from "
                // + getLogicalScreen().getCursor().getRow() + ","
                // + getLogicalScreen().getCursor().getColumn() + " to "
                // + getLogicalScreen().getCursor().getRow() + ","
                // + (getLogicalScreen().getWidth() - 1));

                eraseCells(getLogicalScreen().getCursor().getRow(),
                        getLogicalScreen().getCursor().getColumn(),
                        getLogicalScreen().getCursor().getRow(),
                        getLogicalScreen().getWidth() - 1);
                break;
            case 1:
                switch (a_params[0])
                {
                    default:
                    case 0:
                        // System.out.println(" EL : from "
                        // + getLogicalScreen().getCursor().getRow() + ","
                        // + getLogicalScreen().getCursor().getColumn()
                        // + " to "
                        // + getLogicalScreen().getCursor().getRow() + ","
                        // + (getLogicalScreen().getWidth() - 1));

                        eraseCells(getLogicalScreen().getCursor().getRow(),
                                getLogicalScreen().getCursor().getColumn(),
                                getLogicalScreen().getCursor().getRow(),
                                getLogicalScreen().getWidth() - 1);
                        break;
                    case 1:
                        // System.out.println(" EL : from "
                        // + getLogicalScreen().getCursor().getRow()
                        // + ",0" + " to "
                        // + getLogicalScreen().getCursor().getRow() + ","
                        // + getLogicalScreen().getCursor().getColumn());

                        eraseCells(getLogicalScreen().getCursor().getRow(), 0,
                                getLogicalScreen().getCursor().getRow(),
                                getLogicalScreen().getCursor().getColumn());
                        break;
                    case 2:
                        // System.out.println(" EL : from "
                        // + getLogicalScreen().getCursor().getRow()
                        // + ",0" + " to "
                        // + getLogicalScreen().getCursor().getRow() + ","
                        // + (getLogicalScreen().getWidth() - 1));

                        eraseCells(getLogicalScreen().getCursor().getRow(), 0,
                                getLogicalScreen().getCursor().getRow(),
                                getLogicalScreen().getWidth() - 1);
                        break;
                }
                break;
        }
    }

    /**
     * ED - Erase in Display.
     *
     * @param a_numParams the number of parameters present for the escape
     *            sequence
     * @param a_params an array of parameters embedded in the escape sequence,
     *            only one parameter is valid for this sequence and if present,
     *            its value will be one of ...
     *            <ul>
     *            <li>0 (default) - Erase in Display: Erase from cursor to the
     *            end of the screen (including the cursor position). Line
     *            attribute become single height, single width for all
     *            completely erased lines.
     *            <li>1 - Erase in Display: Erase from the beginning of the
     *            screen to the cursor (including the cursor position). Line
     *            attribute become single height, single width for all
     *            completely erased lines.
     *            <li>2 - Erase in Display: Erase the complete display. All
     *            lines are erased and changed to single width. The cursor does
     *            not move.
     *            </ul>
     */
    public final void doED(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                // System.out.println(" ED : from "
                // + getLogicalScreen().getCursor().getRow() + ","
                // + getLogicalScreen().getCursor().getColumn() + " to "
                // + (getLogicalScreen().getHeight() - 1) + ","
                // + (getLogicalScreen().getWidth() - 1));

                eraseCells(getLogicalScreen().getCursor().getRow(),
                        getLogicalScreen().getCursor().getColumn(),
                        getLogicalScreen().getHeight() - 1, getLogicalScreen()
                                .getWidth() - 1);
                break;
            case 1:
                switch (a_params[0])
                {
                    default:
                    case 0:
                        // System.out.println(" ED : from "
                        // + getLogicalScreen().getCursor().getRow() + ","
                        // + getLogicalScreen().getCursor().getColumn()
                        // + " to " + (getLogicalScreen().getHeight() - 1)
                        // + "," + (getLogicalScreen().getWidth() - 1));

                        eraseCells(getLogicalScreen().getCursor().getRow(),
                                getLogicalScreen().getCursor().getColumn(),
                                getLogicalScreen().getHeight() - 1,
                                getLogicalScreen().getWidth() - 1);
                        break;
                    case 1:
                        // System.out.println(" ED : from " + "0,0" + " to "
                        // + getLogicalScreen().getCursor().getRow() + ","
                        // + getLogicalScreen().getCursor().getColumn());

                        eraseCells(0, 0, getLogicalScreen().getCursor()
                                .getRow(), getLogicalScreen().getCursor()
                                .getColumn());
                        break;
                    case 2:
                        // System.out.println(" ED : from " + "0,0" + " to "
                        // + (getLogicalScreen().getHeight() - 1) + ","
                        // + (getLogicalScreen().getWidth() - 1));

                        eraseCells(0, 0, getLogicalScreen().getHeight() - 1,
                                getLogicalScreen().getWidth() - 1);
                        break;
                }
                break;
        }
    }

    /**
     * DECDWL - Double-Width Line (DEC Private)
     *
     * ESC # 6
     *
     * This causes the line that contains the active position to become
     * double-width single-height. If the line was single-width single-height,
     * all characters to the right of the screen are lost. The cursor remains
     * over the same character position unless it would be to the right of the
     * right margin, in which case, it is moved to the right margin.
     *
     * NOTE: The use of double-width characters reduces the number of characters
     * per line by half.
     *
     * @param a_lineWidth
     */
    public final void doDECDWL(final LineWidth a_lineWidth)
    {
        // System.out.println(" DECDWL : " + a_lineWidth);

        setLineWidth(a_lineWidth);
    }

    /**
     * DECSC - Save Cursor
     *
     * ESC 7
     *
     * Saves the following items in the terminal's memory:
     *
     * Cursor position
     *
     * Character attributes set by the SGR command
     *
     * Character sets (G0, G1, G2, or G3) currently in GL and GR
     *
     * Wrap flag (autowrap or no autowrap)
     *
     * State of origin mode (DECOM)
     *
     * Selective erase attribute
     *
     * Any single shift 2 (SS2) or single shift 3 (SS3) functions sent
     *
     */
    public final void doDECSC()
    {
        doSaveCursor();
    }

    /**
     * The value of the left margin (Pl) must be less than the right margin
     * (Pr).
     *
     * The maximum size of the scrolling region is the page size, based on the
     * setting of set columns per page (DECSCPP).
     *
     * The minimum size of the scrolling region is two columns.
     *
     * The terminal only recognizes this control function if vertical split
     * screen mode (DECLRMM) is set.
     *
     * DECSLRM moves the cursor to column 1, line 1 of the page.
     *
     * If the left and right margins are set to columns other than 1 and 80 (or
     * 132), then the terminal cannot scroll smoothly.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doDECSLRM(final int a_numParams, final int[] a_params)
    {
        int leftMargin;
        int rightMargin;

        switch (a_numParams)
        {
            default:
            case 0:
                leftMargin = 0;
                rightMargin = 0;
                break;
            case 1:
                leftMargin = a_params[0] - 1;
                rightMargin = 0;
                break;
            case 2:
                leftMargin = a_params[0];
                rightMargin = a_params[1] - 1;
                break;
        }

        if (leftMargin >= rightMargin)
        {
            leftMargin = rightMargin + 1;
        }

        getLogicalScreen().getCursor().setLeftMargin(leftMargin);
        getLogicalScreen().getCursor().setRightMargin(rightMargin);
    }

    /**
     * DECDHL - Double Height Line (DEC Private)
     *
     * Top Half: ESC # 3 Bottom Half: ESC # 4
     *
     * These sequences cause the line containing the active position to become
     * the top or bottom half of a double-height double-width line. The
     * sequences must be used in pairs on adjacent lines and the same character
     * output must be sent to both lines to form full double-height characters.
     * If the line was single-width single-height, all characters to the right
     * of the center of the screen are lost. The cursor remains over the same
     * character position unless it would be to the right of the right margin,
     * in which case it is moved to the right margin.
     *
     * NOTE: The use of double-width characters reduces the number of characters
     * per line by half.
     *
     * @param a_height
     */
    public final void doDECDHL(final LineHeight a_height)
    {
        // System.out.println("DECDHL : " + a_height);

        setLineHeight(a_height);
    }

    /**
     * DECID - Identify Terminal (DEC Private)
     *
     * ESC Z
     *
     * This sequence causes the same response as the ANSI device attributes
     * (DA). This sequence will not be supported in future DEC terminals,
     * therefore, DA should be used by any new software.
     */
    public final void doDECID()
    {
        doDA(0, 0);
    }

    /**
     * SGR - Select Graphic Rendition
     *
     * ESC [ Ps ; . . . ; Ps m
     *
     * default value: 0
     *
     * Invoke the graphic rendition specified by the parameter(s). All following
     * characters transmitted to the VT100 are rendered according to the
     * parameter(s) until the next occurrence of SGR. Format Effector
     * <ul>
     * <li>Parameter Parameter Meaning
     * <li>0 Attributes off
     * <li>1 Bold or increased intensity
     * <li>4 Underscore
     * <li>5 Blink
     * <li>7 Negative (reverse) image
     * </ul>
     *
     * All other parameter values are ignored.
     *
     * With the Advanced Video Option, only one type of character attribute is
     * possible as determined by the cursor selection; in that case specifying
     * either the underscore or the reverse attribute will activate the
     * currently selected attribute. (See cursor selection in Chapter 1).
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doSGR(final int a_numParams, final int[] a_params)
    {
        final Cell currentCell = getCurrentCell();

        if (a_numParams > 0)
        {
            for (int i = 0; i < a_numParams; i++)
            {
                switch (a_params[i])
                {
                    default:
                        // System.out.println("Rendition not set for SGR value :
                        // "
                        // + a_params[i]);
                        break;
                    case 0:
                        getActiveRendition().setBold(false);
                        getActiveRendition().setUnderline(false);
                        getActiveRendition().setBlink(false);
                        getActiveRendition().setInverse(false);
                        getActiveRendition().setForegroundIndex(2);
                        getActiveRendition().setBackgroundIndex(0);
                        break;
                    case 1:
                        getActiveRendition().setBold(true);
                        break;
                    case 4:
                        getActiveRendition().setUnderline(true);
                        break;
                    case 5:
                        getActiveRendition().setBlink(true);
                        break;
                    case 7:
                        getActiveRendition().setInverse(true);
                        break;

                    // Change foreground colour
                    case 30:
                        // Black
                        getActiveRendition().setForegroundIndex(0);
                        break;
                    case 31:
                        // Red
                        getActiveRendition().setForegroundIndex(1);
                        break;
                    case 32:
                        // Green
                        getActiveRendition().setForegroundIndex(2);
                        break;
                    case 33:
                        // Yellow
                        getActiveRendition().setForegroundIndex(3);
                        break;
                    case 34:
                        // Blue
                        getActiveRendition().setForegroundIndex(4);
                        break;
                    case 35:
                        // Magenta
                        getActiveRendition().setForegroundIndex(5);
                        break;
                    case 36:
                        // Cyan
                        getActiveRendition().setForegroundIndex(6);
                        break;
                    case 37:
                        // White
                        getActiveRendition().setForegroundIndex(7);
                        break;

                    // Change background colour
                    case 40:
                        // Black
                        getActiveRendition().setBackgroundIndex(0);
                        break;
                    case 41:
                        // Red
                        getActiveRendition().setBackgroundIndex(1);
                        break;
                    case 42:
                        // Green
                        getActiveRendition().setBackgroundIndex(2);
                        break;
                    case 43:
                        // Yellow
                        getActiveRendition().setBackgroundIndex(3);
                        break;
                    case 44:
                        // Blue
                        getActiveRendition().setBackgroundIndex(4);
                        break;
                    case 45:
                        // Magenta
                        getActiveRendition().setBackgroundIndex(5);
                        break;
                    case 46:
                        // Cyan
                        getActiveRendition().setBackgroundIndex(6);
                        break;
                    case 47:
                        // White
                        getActiveRendition().setBackgroundIndex(7);
                        break;
                }
            }
        }
        else
        {
            getActiveRendition().setBold(false);
            getActiveRendition().setUnderline(false);
            getActiveRendition().setBlink(false);
            getActiveRendition().setInverse(false);
            getActiveRendition().setForegroundIndex(2);
            getActiveRendition().setBackgroundIndex(0);
        }

        drawCell(currentCell, true, BlinkState.OFF);
    }

    /**
     * DSR - Device Status Report
     *
     * ESC [ Ps n
     *
     * Requests and reports the general status of the VT100 according to the
     * following parameter(s).
     *
     * <ul>
     * <li>Parameter Parameter Meaning
     * <li>0 Response from VT100 - Ready, No malfunctions detected (default)
     * <li>3 Response from VT100 - Malfunction � retry
     * <li>5 Command from host � Please report status (using a DSR control
     * sequence)
     * <li>6 Command from host - Please report active position (using a CPR
     * control sequence)
     * </ul>
     * DSR with a parameter value of 0 or 3 is always sent as a response to a
     * requesting DSR with a parameter value of 5.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doDSR(final int a_numParams, final int[] a_params)
    {
        if (a_numParams > 0)
        {
            for (int i = 0; i < a_numParams; i++)
            {
                switch (a_params[i])
                {
                    default:
                        break;
                    case 0:
                    case 3:
                        // Should never get here, these are VTxx response
                        // sequences, so we should send then and not receive
                        // them
                        break;
                    case 5:
                        final StringBuilder sb = new StringBuilder();
                        sb.append(ESC);
                        sb.append('[');
                        sb.append(getLogicalScreen().getCursor().getRow());
                        sb.append('0');
                        sb.append(getLogicalScreen().getCursor().getColumn());
                        sb.append('n');

                        onTerminalData(sb.toString().getBytes());
                        break;
                    case 6:
                        doCPR(0, null);
                        break;
                }
            }
        }
    }

    /**
     * DECSTR - Soft Terminal Reset
     */
    public final void doDECSTR()
    {
        // TODO DECSTR
    }

    /**
     * DECSCL - Set Conformance Level
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doDECSCL(final int a_numParams, final int[] a_params)
    {
        // TODO DECSCL
    }

    /**
     * Set Column mode (normally 80 or 132 Columns).
     *
     * @param a_columns
     */
    public final void setColumns(final int a_columns)
    {
        setScreenWidth(a_columns);
    }

    /**
     * Clear tabs.
     *
     * Clear tab at current column ESC [ g or ESC [ 0 g
     *
     * Clear all tabs ESC [ 3 g
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doClearTab(final int a_numParams, final int[] a_params)
    {
        switch (a_numParams)
        {
            default:
            case 0:
                getLogicalScreen().getCursor().clearTab();
                break;
            case 1:
                switch (a_params[0])
                {
                    default:
                    case '0':
                        getLogicalScreen().getCursor().clearTab();
                        break;
                    case '3':
                        getLogicalScreen().getCursor().clearAllTabs();
                        break;
                }
        }
    }

    /**
     * Set Scrolling Region.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doSetScrollingRegion(final int a_numParams,
            final int[] a_params)
    {
        // TODO Set the scrolling region, probably need to tell our logical
        // screen about this so that it can do the right thing
        @SuppressWarnings("unused")
        final int top = a_params[0];

        int bottom = a_params[1];
        if (bottom == 0)
        {
            bottom = getLogicalScreen().getHeight();
        }
    }

    /**
     * Save the Cursor Position.
     *
     */
    public final void doSaveCursor()
    {
        getLogicalScreen().getCursor().saveCursor();
    }

    /**
     * Unsave the cursor position.
     *
     */
    public final void doUnsaveCursor()
    {
        getLogicalScreen().getCursor().unsaveCursor();
    }

    /**
     * Adjustments (DECALN)
     *
     * The terminal has a screen alignment pattern that service personnel use to
     * adjust the screen. You can display the screen alignment pattern with the
     * DECALN sequence.
     *
     * ESC # 8
     *
     * This sequence fills the screen with uppercase E's.
     *
     * @param a_numParams
     * @param a_params
     */
    public final void doDECALN(final int a_numParams, final int[] a_params)
    {
        // System.out.println(" DECALN (Fill screen with the letter E");

        fillScreen('E');
    }

    /**
     * Reset Keypad mode.
     */
    public final void doResetKeypadMode()
    {
        // TODO Reset Keypad mode
        // ESC >
    }

    /**
     * Set Keypad mode.
     */
    public final void doSetKeypadMode()
    {
        // TODO Set Keypad mode
        // ESC =
    }

    /**
     * Set Reverse Video.
     *
     * @param a_reverse true if reverse video is to be set.
     */
    public final void setReverseVideo(final boolean a_reverse)
    {
        if (a_reverse)
        {
            getActiveRendition().setForegroundIndex(0);
            getActiveRendition().setBackgroundIndex(2);
        }
        else
        {
            getActiveRendition().setForegroundIndex(2);
            getActiveRendition().setBackgroundIndex(0);
        }

        drawScreen();
    }

    /**
     * Set Origin Mode.
     *
     * @param a_origin true if origin mode is to be set.
     */
    public final void setOrigin(final boolean a_origin)
    {
        getLogicalScreen().getCursor().setOrigin(a_origin);
    }

    /**
     * @param a_numParams
     * @param a_params
     * @throws EmulatorException
     */
    public final void doSM(final int a_numParams, final int[] a_params)
            throws EmulatorException
    {
        for (int iParam = 0; iParam < a_numParams; iParam++)
        {
            switch (a_params[iParam])
            {
            // case 2:
            // TODO Set Keyboard Action Mode (AM)
            // break;
            // case 4:
            // TODO Set Insert Mode (IRM)
            // break;
            // case 12:
            // TODO Set Send/Receive (SRM)
            // break;
                case 20:
                    // Set Automatic Newline(LNM)
                    setLinefeed(true);
                    break;
                default:
                    throw new EmulatorException(
                            "ANSI SetMode NOT implemented for "
                                    + a_params[iParam]);
            }
        }
    }

    /**
     * @param a_numParams
     * @param a_params
     * @throws EmulatorException
     */
    public final void doRM(final int a_numParams, final int[] a_params)
            throws EmulatorException
    {
        for (int iParam = 0; iParam < a_numParams; iParam++)
        {
            // System.out.println(" RM :" + a_params[iParam]);
            switch (a_params[iParam])
            {
            // case 2:
            // TODO Set Keyboard Action Mode (AM)
            // break;
            // case 4:
            // TODO Set Replace Mode (IRM)
            // break;
            // case 12:
            // TODO Set Send/Receive (SRM)
            // break;
                case 20:
                    // System.out.println(" LNM");
                    // Normal Linefeed(LNM)
                    setLinefeed(false);
                    break;
                default:
                    throw new EmulatorException(
                            "ANSI ResetMode NOT implemented for "
                                    + a_params[iParam]);
            }
        }
    }

}
