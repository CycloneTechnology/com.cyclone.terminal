package com.cyclone.terminal.parser;

/**
 * @author Phil.Baxter
 * 
 */
public class VTxxxParser
{
    // private static FSM createFSM()
    // {
    // StateMachineBuilder<FSM(""), State, Event, Void> builder =
    // StateMachineBuilderFactory
    // .create(FSM.class, State.class, Event.class);
    //
    // builder.externalTransition().from(State.Calm).to(State.GoingWild)
    // .on(Event.GoWild);
    // builder.externalTransition().from(State.GoingWild).to(State.GoneWild)
    // .on(Event.GoneWild);
    // builder.externalTransition().from(State.GoneWild).to(State.Calm)
    // .on(Event.CalmDown);
    //
    // builder.onEntry(State.GoingWild).perform(
    // new AnonymousAction<FSM, State, Event, Void>()
    // {
    // @Override
    // public void execute(State from, State to, Event event,
    // Void context, FSM stateMachine)
    // {
    // // let's assume we are ready to go wild already and just
    // // fire event
    // stateMachine.fire(Event.GoneWild);
    // }
    // });
    // builder.onEntry(State.GoneWild).perform(
    // new AnonymousAction<FSM, State, Event, Void>()
    // {
    // @Override
    // public void execute(State from, State to, Event event,
    // Void context, FSM stateMachine)
    // {
    // // throw new RuntimeException("Oops!");
    // }
    // });
    // return builder.newStateMachine(State.Calm);
    // }
    //
    // private static class FSM
    // extends
    // AbstractStateMachineWithoutContext<FSM, State, Event>
    // {
    // protected FSM(ImmutableState<FSM, State, Event, Void> initialState,
    // Map<State, ImmutableState<FSM, State, Event, Void>> states)
    // {
    // super(initialState, states);
    // }
    // }
    //

    @SuppressWarnings("unused")
    private static enum Event
    {
        // ASCII CODES
        BYTE_00("NUL", "Null char"),
        BYTE_01("SOH", "Start of Heading"),
        BYTE_02("STX", "Start of Text"),
        BYTE_03("ETX", "End of Text"),
        BYTE_04("EOT", "End of Transmission"),
        BYTE_05("ENQ", "Enquiry"),
        BYTE_06("ACK", "Acknowledge"),
        BYTE_07("BEL", "Bell"),
        BYTE_08("BS ", "Back Space"),
        BYTE_09("TAB", "Horizontal Tab"),
        BYTE_0A("LF ", "NL line feed, new line"),
        BYTE_0B("VT ", "Vertical Tab"),
        BYTE_0C("FF ", "NP form feed, new page"),
        BYTE_0D("CR ", "Carriage Return"),
        BYTE_0E("SO ", "Shift Out, X-On"),
        BYTE_0F("SI ", "Shift In, X-Off"),

        BYTE_10("DLE", "Data Link Escape"),
        BYTE_11("DC1", "Device Control 1"),
        BYTE_12("DC2", "Device Control 2"),
        BYTE_13("DC3", "Device Control 3"),
        BYTE_14("DC4", "Device Control 4"),
        BYTE_15("NAK", "Negative Acknowledge"),
        BYTE_16("SYN", "Synchronous Idle"),
        BYTE_17("ETB", "End of Transmission Block"),
        BYTE_18("CAN", "Cancel"),
        BYTE_19("EM ", "End of Medium"),
        BYTE_1A("SUB", "Substitute"),
        BYTE_1B("ESC", "Escape"),
        BYTE_1C("FS ", "File Separator"),
        BYTE_1D("GS ", "Group Separator"),
        BYTE_1E("RS ", "Record Separator"),
        BYTE_1F("US ", "Unit Separator"),

        BYTE_20("   ", "Space"),
        BYTE_21("!  ", "Exclamation mark"),
        BYTE_22("\"  ", "Double quotes (or speech marks)"),
        BYTE_23("#  ", "Number"),
        BYTE_24("$  ", "Dollar"),
        BYTE_25("%  ", "Percentage"),
        BYTE_26("&  ", "Ampersand"),
        BYTE_27("'  ", "Single quote"),
        BYTE_28("(  ", "Open parenthesis (or open bracket)"),
        BYTE_29(")  ", "Close parenthesis (or close bracket)"),
        BYTE_2A("*  ", "Asterisk"),
        BYTE_2B("+  ", "Plus"),
        BYTE_2C(",  ", "Comma"),
        BYTE_2D("-  ", "Hyphen"),
        BYTE_2E(".  ", "Period, dot or full stop"),
        BYTE_2F("/  ", "Slash or divide"),

        BYTE_30("0  ", "Zero"),
        BYTE_31("1  ", "One"),
        BYTE_32("2  ", "Two"),
        BYTE_33("3  ", "Three"),
        BYTE_34("4  ", "Four"),
        BYTE_35("5  ", "Five"),
        BYTE_36("6  ", "Six"),
        BYTE_37("7  ", "Seven"),
        BYTE_38("8  ", "Eight"),
        BYTE_39("9  ", "Nine"),
        BYTE_3A(":  ", "Colon"),
        BYTE_3B(";  ", "Semicolon"),
        BYTE_3C("<  ", "Less than (or open angled bracket)"),
        BYTE_3D("=  ", "Equals"),
        BYTE_3E(">  ", "Greater than (or close angled bracket)"),
        BYTE_3F("?  ", "Question mark"),

        BYTE_40("@  ", "At symbol"),
        BYTE_41("A  ", "Uppercase A"),
        BYTE_42("B  ", "Uppercase B"),
        BYTE_43("C  ", "Uppercase C"),
        BYTE_44("D  ", "Uppercase D"),
        BYTE_45("E  ", "Uppercase E"),
        BYTE_46("F  ", "Uppercase F"),
        BYTE_47("G  ", "Uppercase G"),
        BYTE_48("H  ", "Uppercase H"),
        BYTE_49("I  ", "Uppercase I"),
        BYTE_4A("J  ", "Uppercase J"),
        BYTE_4B("K  ", "Uppercase K"),
        BYTE_4C("L  ", "Uppercase L"),
        BYTE_4D("M  ", "Uppercase M"),
        BYTE_4E("N  ", "Uppercase N"),
        BYTE_4F("O  ", "Uppercase O"),

        BYTE_50("P  ", "Uppercase P"),
        BYTE_51("Q  ", "Uppercase Q"),
        BYTE_52("R  ", "Uppercase R"),
        BYTE_53("S  ", "Uppercase S"),
        BYTE_54("T  ", "Uppercase T"),
        BYTE_55("U  ", "Uppercase U"),
        BYTE_56("V  ", "Uppercase V"),
        BYTE_57("W  ", "Uppercase W"),
        BYTE_58("X  ", "Uppercase X"),
        BYTE_59("Y  ", "Uppercase Y"),
        BYTE_5A("Z  ", "Uppercase Z"),
        BYTE_5B("[  ", "Opening bracket"),
        BYTE_5C("\\  ", "Backslash"),
        BYTE_5D("]  ", "Closing bracket"),
        BYTE_5E("^  ", "Caret - circumflex"),
        BYTE_5F("_  ", "Underscore"),

        BYTE_60("`  ", "Grave accent"),
        BYTE_61("a  ", "Lowercase a"),
        BYTE_62("b  ", "Lowercase b"),
        BYTE_63("c  ", "Lowercase c"),
        BYTE_64("d  ", "Lowercase d"),
        BYTE_65("e  ", "Lowercase e"),
        BYTE_66("f  ", "Lowercase f"),
        BYTE_67("g  ", "Lowercase g"),
        BYTE_68("h  ", "Lowercase h"),
        BYTE_69("i  ", "Lowercase i"),
        BYTE_6A("j  ", "Lowercase j"),
        BYTE_6B("k  ", "Lowercase k"),
        BYTE_6C("l  ", "Lowercase l"),
        BYTE_6D("m  ", "Lowercase m"),
        BYTE_6E("n  ", "Lowercase n"),
        BYTE_6F("o  ", "Lowercase o"),

        BYTE_70("p  ", "Lowercase p"),
        BYTE_71("q  ", "Lowercase q"),
        BYTE_72("r  ", "Lowercase r"),
        BYTE_73("s  ", "Lowercase s"),
        BYTE_74("t  ", "Lowercase t"),
        BYTE_75("u  ", "Lowercase u"),
        BYTE_76("v  ", "Lowercase v"),
        BYTE_77("w  ", "Lowercase w"),
        BYTE_78("x  ", "Lowercase x"),
        BYTE_79("y  ", "Lowercase y"),
        BYTE_7A("z  ", "Lowercase z"),
        BYTE_7B("{  ", "Opening brace"),
        BYTE_7C("|  ", "Vertical bar"),
        BYTE_7D("}  ", "Closing brace"),
        BYTE_7E("~  ", "Equivalency sign - tilde"),
        BYTE_7F("DEL", "Delete"),

        // EXTENDED ASCII CODES START HERE
        BYTE_80("€  ", "Euro sign"),
        BYTE_81("  ", ""),
        BYTE_82("‚  ", "Single low-9 quotation mark"),
        BYTE_83("ƒ  ", "Latin small letter f with hook"),
        BYTE_84("„  ", "Double low-9 quotation mark"),
        BYTE_85("…  ", "Horizontal ellipsis"),
        BYTE_86("†  ", "Dagger"),
        BYTE_87("‡  ", "Double dagger"),
        BYTE_88("ˆ  ", "Modifier letter circumflex accent"),
        BYTE_89("‰  ", "Per mille sign"),
        BYTE_8A("Š  ", "Latin capital letter S with caron"),
        BYTE_8B("‹  ", "Single left-pointing angle quotation"),
        BYTE_8C("Œ  ", "Latin capital ligature OE"),
        BYTE_8D("   ", ""),
        BYTE_8E("Ž  ", "Latin captial letter Z with caron"),
        BYTE_8F("   ", ""),

        BYTE_90("   ", ""),
        BYTE_91("‘  ", "Left single quotation mark"),
        BYTE_92("’  ", "Right single quotation mark"),
        BYTE_93("“  ", "Left double quotation mark"),
        BYTE_94("”  ", "Right double quotation mark"),
        BYTE_95("•  ", "Bullet"),
        BYTE_96("–  ", "En dash"),
        BYTE_97("—  ", "Em dash"),
        BYTE_98("˜˜˜˜   ", "Small tilde"),
        BYTE_99("™  ", "Trade mark sign"),
        BYTE_9A("š  ", "Latin small letter S with caron"),
        BYTE_9B("›  ", "Single right-pointing angle quotation mark"),
        BYTE_9C("œ  ", "Latin small ligature oe"),
        BYTE_9D("   ", ""),
        BYTE_9E("ž  ", "Latin small letter z with caron"),
        BYTE_9F("Ÿ  ", "Latin capital letter Y with diaeresis"),

        BYTE_A0("   ", "Non-breaking space"),
        BYTE_A1("¡  ", "Inverted exclamation mark"),
        BYTE_A2("¢  ", "Cent sign"),
        BYTE_A3("£  ", "Pound sign"),
        BYTE_A4("¤  ", "Currency sign"),
        BYTE_A5("¥  ", "Yen sign"),
        BYTE_A6("¦  ", "Pipe, Broken vertical bar"),
        BYTE_A7("§  ", "Section sign"),
        BYTE_A8("¨  ", "Spacing diaeresis - umlaut"),
        BYTE_A9("©  ", "Copyright sign"),
        BYTE_AA("ª  ", "Feminine ordinal indicator"),
        BYTE_AB("«  ", "Left double angle quotes"),
        BYTE_AC("¬  ", "Not sign"),
        BYTE_AD("   ", "Soft hyphen"),
        BYTE_AE("®  ", "Registered trade mark sign"),
        BYTE_AF("¯  ", "Spacing macron - overline"),

        BYTE_B0("°  ", "Degree sign"),
        BYTE_B1("±  ", "Plus-or-minus sign"),
        BYTE_B2("²  ", "Superscript two - squared"),
        BYTE_B3("³  ", "Superscript three - cubed"),
        BYTE_B4("´  ", "Acute accent - spacing acute"),
        BYTE_B5("µ  ", "Micro sign"),
        BYTE_B6("¶  ", "Pilcrow sign - paragraph sign"),
        BYTE_B7("·  ", "Middle dot - Georgian comma"),
        BYTE_B8("¸  ", "Spacing cedilla"),
        BYTE_B9("¹  ", "Superscript one"),
        BYTE_BA("º  ", "Masculine ordinal indicator"),
        BYTE_BB("»  ", "Right double angle quotes"),
        BYTE_BC("¼  ", "Fraction one quarter"),
        BYTE_BD("½  ", "Fraction one half"),
        BYTE_BE("¾  ", "Fraction three quarters"),
        BYTE_BF("¿  ", "Inverted question mark"),

        BYTE_C0("À  ", "Latin capital letter A with grave"),
        BYTE_C1("Á  ", "Latin capital letter A with acute"),
        BYTE_C2("Â  ", "Latin capital letter A with circumflex"),
        BYTE_C3("Ã  ", "Latin capital letter A with tilde"),
        BYTE_C4("Ä  ", "Latin capital letter A with diaeresis"),
        BYTE_C5("Å  ", "Latin capital letter A with ring above"),
        BYTE_C6("Æ  ", "Latin capital letter AE"),
        BYTE_C7("Ç  ", "Latin capital letter C with cedilla"),
        BYTE_C8("È  ", "Latin capital letter E with grave"),
        BYTE_C9("É  ", "Latin capital letter E with acute"),
        BYTE_CA("Ê  ", "Latin capital letter E with circumflex"),
        BYTE_CB("Ë  ", "Latin capital letter E with diaeresis"),
        BYTE_CC("Ì  ", "Latin capital letter I with grave"),
        BYTE_CD("Í  ", "Latin capital letter I with acute"),
        BYTE_CE("Î  ", "Latin capital letter I with circumflex"),
        BYTE_CF("Ï  ", "Latin capital letter I with diaeresis"),

        BYTE_D0("Ð  ", "Latin capital letter ETH"),
        BYTE_D1("Ñ  ", "Latin capital letter N with tilde"),
        BYTE_D2("Ò  ", "Latin capital letter O with grave"),
        BYTE_D3("Ó  ", "Latin capital letter O with acute"),
        BYTE_D4("Ô  ", "Latin capital letter O with circumflex"),
        BYTE_D5("Õ  ", "Latin capital letter O with tilde"),
        BYTE_D6("Ö  ", "Latin capital letter O with diaeresis"),
        BYTE_D7("×  ", "Multiplication sign"),
        BYTE_D8("Ø  ", "Latin capital letter O with slash"),
        BYTE_D9("Ù  ", "Latin capital letter U with grave"),
        BYTE_DA("Ú  ", "Latin capital letter U with acute"),
        BYTE_DB("Û  ", "Latin capital letter U with circumflex"),
        BYTE_DC("Ü  ", "Latin capital letter U with diaeresis"),
        BYTE_DD("Ý  ", "Latin capital letter Y with acute"),
        BYTE_DE("Þ  ", "Latin capital letter THORN"),
        BYTE_DF("ß  ", "Latin small letter sharp s - ess-zed"),

        BYTE_E0("à  ", "Latin small letter a with grave"),
        BYTE_E1("á  ", "Latin small letter a with acute"),
        BYTE_E2("â  ", "Latin small letter a with circumflex"),
        BYTE_E3("ã  ", "Latin small letter a with tilde"),
        BYTE_E4("ä  ", "Latin small letter a with diaeresis"),
        BYTE_E5("å  ", "Latin small letter a with ring above"),
        BYTE_E6("æ  ", "Latin small letter ae"),
        BYTE_E7("ç  ", "Latin small letter c with cedilla"),
        BYTE_E8("è  ", "Latin small letter e with grave"),
        BYTE_E9("é  ", "Latin small letter e with acute"),
        BYTE_EA("ê  ", "Latin small letter e with circumflex"),
        BYTE_EB("ë  ", "Latin small letter e with diaeresis"),
        BYTE_EC("ì  ", "Latin small letter i with grave"),
        BYTE_ED("í  ", "Latin small letter i with acute"),
        BYTE_EE("î  ", "Latin small letter i with circumflex"),
        BYTE_EF("ï  ", "Latin small letter i with diaeresis"),

        BYTE_F0("ð  ", "Latin small letter eth"),
        BYTE_F1("ñ  ", "Latin small letter n with tilde"),
        BYTE_F2("ò  ", "Latin small letter o with grave"),
        BYTE_F3("ó  ", "Latin small letter o with acute"),
        BYTE_F4("ô  ", "Latin small letter o with circumflex"),
        BYTE_F5("õ  ", "Latin small letter o with tilde"),
        BYTE_F6("ö  ", "Latin small letter o with diaeresis"),
        BYTE_F7("÷  ", "Division sign"),
        BYTE_F8("ø  ", "Latin small letter o with slash"),
        BYTE_F9("ù  ", "Latin small letter u with grave"),
        BYTE_FA("ú  ", "Latin small letter u with acute"),
        BYTE_FB("û  ", "Latin small letter u with circumflex"),
        BYTE_FC("ü  ", "Latin small letter u with diaeresis"),
        BYTE_FD("ý  ", "Latin small letter y with acute"),
        BYTE_FE("þ  ", "Latin small letter thorn"),
        BYTE_FF("ÿ  ", "Latin small letter y with diaeresis");

        private final String m_symbol;

        private final String m_description;

        /**
         * @param a_symbol
         */
        private Event(final String a_symbol, final String a_description)
        {
            m_symbol = a_symbol;
            m_description = a_description;
        }

        /**
         * @return the symbol used for this character
         */
        public String getSymbol()
        {
            return m_symbol;
        }

        /**
         * @return the description of the character
         */
        public String getDescription()
        {
            return m_description;
        }
    }
}
