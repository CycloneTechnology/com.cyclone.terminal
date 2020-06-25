package com.cyclone.terminal.emulator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Phil.Baxter
 * 
 */
public abstract class VT52 extends BaseTerminal
{
    protected static final byte[] UP_ARROW_ESCAPE_SEQ = new byte[]
    {27, '[', 'A'};

    protected static final byte[] DOWN_ARROW_ESCAPE_SEQ = new byte[]
    {27, '[', 'B'};

    protected static final byte[] RIGHT_ARROW_ESCAPE_SEQ = new byte[]
    {27, '[', 'C'};

    protected static final byte[] LEFT_ARROW_ESCAPE_SEQ = new byte[]
    {27, '[', 'D'};

    protected static final byte[] KEYPAD_0_ESCAPE_SEQ = new byte[]
    {27, '?', 'p'};

    protected static final byte[] KEYPAD_1_ESCAPE_SEQ = new byte[]
    {27, '?', 'q'};

    protected static final byte[] KEYPAD_2_ESCAPE_SEQ = new byte[]
    {27, '?', 'r'};

    protected static final byte[] KEYPAD_3_ESCAPE_SEQ = new byte[]
    {27, '?', 's'};

    protected static final byte[] KEYPAD_4_ESCAPE_SEQ = new byte[]
    {27, '?', 't'};

    protected static final byte[] KEYPAD_5_ESCAPE_SEQ = new byte[]
    {27, '?', 'u'};

    protected static final byte[] KEYPAD_6_ESCAPE_SEQ = new byte[]
    {27, '?', 'v'};

    protected static final byte[] KEYPAD_7_ESCAPE_SEQ = new byte[]
    {27, '?', 'w'};

    protected static final byte[] KEYPAD_8_ESCAPE_SEQ = new byte[]
    {27, '?', 'x'};

    protected static final byte[] KEYPAD_9_ESCAPE_SEQ = new byte[]
    {27, '?', 'y'};

    protected static final byte[] KEYPAD_ADD_ESCAPE_SEQ = new byte[]
    {27, '?', 'k'};

    protected static final byte[] KEYPAD_CR_ESCAPE_SEQ = new byte[]
    {27, '?', 'M'};

    protected static final byte[] KEYPAD_DECIMAL_ESCAPE_SEQ = new byte[]
    {'.'};

    protected static final byte[] KEYPAD_DIVIDE_ESCAPE_SEQ = new byte[]
    {27, '?', 'o'};

    protected static final byte[] KEYPAD_EQUAL_ESCAPE_SEQ = new byte[]
    {27, '?', 'X'};

    protected static final byte[] KEYPAD_MULTIPLY_ESCAPE_SEQ = new byte[]
    {27, '?', 'j'};

    protected static final byte[] KEYPAD_SUBTRACT_ESCAPE_SEQ = new byte[]
    {27, '?', 'm'};

    protected static final byte[] KEYPAD_PF1_ESCAPE_SEQ = new byte[]
    {27, 'P'};

    protected static final byte[] KEYPAD_PF2_ESCAPE_SEQ = new byte[]
    {27, 'Q'};

    protected static final byte[] KEYPAD_PF3_ESCAPE_SEQ = new byte[]
    {27, 'R'};

    protected static final byte[] KEYPAD_PF4_ESCAPE_SEQ = new byte[]
    {27, 'S'};

    protected static final byte[] KEYPAD_SPACE_ESCAPE_SEQ = new byte[]
    {27, '?', ' '};

    protected static final byte[] KEYPAD_TAB_ESCAPE_SEQ = new byte[]
    {27, '?', 'I'};

    /**
     * @param a_parent
     */
    public VT52(final Composite a_parent)
    {
        super(a_parent);
    }

    /**
     * @param a_parent
     * @param a_width
     * @param a_height
     * @param a_history
     */
    public VT52(final Composite a_parent, final int a_width, final int a_height,
            final int a_history)
    {
        super(a_parent, a_width, a_height, a_history);
    }

    /**
     * 
     */
    public final void cursorUp()
    {
        getLogicalScreen().getCursor().up();
    }

    /**
     * 
     */
    public final void cursorDown()
    {
        getLogicalScreen().getCursor().down();
    }

    /**
     * 
     */
    public final void cursorRight()
    {
        getLogicalScreen().getCursor().right();
    }

    /**
     * 
     */
    public final void cursorLeft()
    {
        performBS();
    }

    /**
     * 
     */
    public final void cursorHome()
    {
        getLogicalScreen().getCursor().home();
    }

    /**
     * 
     */
    public final void reverseLineFeed()
    {
        final Scroll scroll = getLogicalScreen().getCursor().up();
        switch (scroll.getScrollDirection())
        {
            case DOWN:
                for (int i = 0; i < scroll.getScrollAmount(); i++)
                {
                    scrollDown();
                }
                break;
            case UP:
                for (int i = 0; i < scroll.getScrollAmount(); i++)
                {
                    scrollUp();
                }
                break;
            case NONE:
            default:
                break;
        }
    }

    /**
     * 
     */
    public final void eraseToEndOfScreen()
    {
        eraseCells(getLogicalScreen().getCursor().getRow(),
                getLogicalScreen().getCursor().getColumn(),
                getLogicalScreen().getHeight() - 1,
                getLogicalScreen().lastColumn());
    }

    /**
     * 
     */
    public final void eraseToEndOfLine()
    {
        eraseCells(getLogicalScreen().getCursor().getRow(),
                getLogicalScreen().getCursor().getColumn(),
                getLogicalScreen().getCursor().getRow(),
                getLogicalScreen().lastColumn());
    }

    @Override
    protected final byte[] translateKeyCode(final int a_keyCode,
            final char a_character, final boolean a_applicationKeys)
    {
        switch (a_keyCode)
        {
            case SWT.SHIFT:
                break;
            case SWT.CONTROL:
                break;
            case SWT.ARROW_UP:
                return UP_ARROW_ESCAPE_SEQ;
            case SWT.ARROW_DOWN:
                return DOWN_ARROW_ESCAPE_SEQ;
            case SWT.ARROW_RIGHT:
                return RIGHT_ARROW_ESCAPE_SEQ;
            case SWT.ARROW_LEFT:
                return LEFT_ARROW_ESCAPE_SEQ;
            default:
                if (a_applicationKeys)
                {
                    switch (a_keyCode)
                    {
                        case SWT.KEYPAD_0:
                            return KEYPAD_0_ESCAPE_SEQ;
                        case SWT.KEYPAD_1:
                            return KEYPAD_1_ESCAPE_SEQ;
                        case SWT.KEYPAD_2:
                            return KEYPAD_2_ESCAPE_SEQ;
                        case SWT.KEYPAD_3:
                            return KEYPAD_3_ESCAPE_SEQ;
                        case SWT.KEYPAD_4:
                            return KEYPAD_4_ESCAPE_SEQ;
                        case SWT.KEYPAD_5:
                            return KEYPAD_5_ESCAPE_SEQ;
                        case SWT.KEYPAD_6:
                            return KEYPAD_6_ESCAPE_SEQ;
                        case SWT.KEYPAD_7:
                            return KEYPAD_7_ESCAPE_SEQ;
                        case SWT.KEYPAD_8:
                            return KEYPAD_8_ESCAPE_SEQ;
                        case SWT.KEYPAD_9:
                            return KEYPAD_9_ESCAPE_SEQ;
                        case SWT.KEYPAD_ADD:
                            return KEYPAD_ADD_ESCAPE_SEQ;
                        case SWT.KEYPAD_CR:
                            return KEYPAD_CR_ESCAPE_SEQ;
                        case SWT.KEYPAD_DECIMAL:
                            return KEYPAD_DECIMAL_ESCAPE_SEQ;
                        case SWT.KEYPAD_DIVIDE:
                            return KEYPAD_DIVIDE_ESCAPE_SEQ;
                        case SWT.KEYPAD_EQUAL:
                            return KEYPAD_EQUAL_ESCAPE_SEQ;
                        case SWT.KEYPAD_MULTIPLY:
                            return KEYPAD_MULTIPLY_ESCAPE_SEQ;
                        case SWT.KEYPAD_SUBTRACT:
                            return KEYPAD_SUBTRACT_ESCAPE_SEQ;
                        case SWT.PRINT_SCREEN:
                            return KEYPAD_PF1_ESCAPE_SEQ;
                        case SWT.SCROLL_LOCK:
                            return KEYPAD_PF2_ESCAPE_SEQ;
                        case SWT.BREAK:
                            return KEYPAD_PF3_ESCAPE_SEQ;
                        default:
                            switch (a_character)
                            {
                                case ' ':
                                    return KEYPAD_SPACE_ESCAPE_SEQ;
                                case '\t':
                                    return KEYPAD_TAB_ESCAPE_SEQ;
                                default:
                                    break;
                            }
                            break;
                    }
                }
                break;
        }

        return null;
    }

    /**
     * Direct Cursor Address.
     * 
     * ESC Y line column
     * 
     * Move the cursor to the specified line and column. The line and column
     * numbers are sent as ASCII codes whose values are the number plus 0378;
     * e.g., 0408 refers to the first line or column, 0508 refers to the eighth
     * line or column, etc.
     * 
     * @param a_line
     * @param a_column
     */
    public final void doDCA(final int a_line, final int a_column)
    {
        getLogicalScreen().getCursor().setPosition(a_line, a_column);
    }

    /**
     * Enter ANSI Mode
     */
    public final void enterANSIMode()
    {
        // TODO - Enter ANSI Mode - This is currently a NOOP
    }

    /**
     * ESC F
     */
    public final void enterGraphicsMode()
    {
        selectCharacterSet(CharacterSetSequence.G0,
                CharacterSet.SPECIALGRAPHICS);
    }

    /**
     * ESC G
     */
    public final void exitGraphicsMode()
    {
        selectCharacterSet(CharacterSetSequence.G0, CharacterSet.USASCII);
    }

}
