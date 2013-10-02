package com.cyclone.terminal.emulator.datascope;

import com.cyclone.terminal.emulator.datascope.SimpleDataScopeDialog.Direction;

/**
 * @author Phil.Baxter
 * 
 */
public interface DataScope
{
    /**
     * The translation of ascii code to a string representation of it
     */
    String[] ASCII_CODES = new String[]
    {
            // Ascii code 0 thru 127
            "NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL", "BS ",
            "HT ", "LF ", "VT ", "FF ", "CR ", "SO ", "SI ", "DLE", "DC1",
            "DC2", "DC3", "DC4", "NAK", "SYN", "ETB", "CAN", "EM ", "SUB",
            "ESC", "FS ", "GS ", "RS ", "US ", "\' \'", " ! ", " \" ", " # ",
            " $ ", " % ", " & ", " ' ", " ( ", " ) ", " * ", " + ", " , ",
            " - ", " . ", " / ", " 0 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ",
            " 6 ", " 7 ", " 8 ", " 9 ", " : ", " ; ", " < ", " = ", " > ",
            " ? ", " @ ", " A ", " B ", " C ", " D ", " E ", " F ", " G ",
            " H ", " I ", " J ", " K ", " L ", " M ", " N ", " O ", " P ",
            " Q ", " R ", " S ", " T ", " U ", " V ", " W ", " X ", " Y ",
            " Z ", " [ ", " \\ ", " ] ",
            " ^ ",
            " _ ",
            " ` ",
            " a ",
            " b ",
            " c ",
            " d ",
            " e ",
            " f ",
            " g ",
            " h ",
            " i ",
            " j ",
            " k ",
            " l ",
            " m ",
            " n ",
            " o ",
            " p ",
            " q ",
            " r ",
            " s ",
            " t ",
            " u ",
            " v ",
            " w ",
            " x ",
            " y ",
            " z ",
            " { ",
            " | ",
            " } ",
            " ~ ",
            "DEL",
            // Ascii code 128 thru 255
            " \u000080 ", " \u000081 ", " \u000082 ", " \u000083 ",
            " \u000084 ", " \u000085 ", " \u000086 ", " \u000087 ",
            " \u000088 ", " \u000089 ", " \u00008A ", " \u00008B ",
            " \u00008C ", " \u00008D ", " \u00008E ", " \u00008F ",
            " \u000090 ", " \u000091 ", " \u000092 ", " \u000093 ",
            " \u000094 ", " \u000095 ", " \u000096 ", " \u000097 ",
            " \u000098 ", " \u000099 ", " \u00009A ", " \u00009B ",
            " \u00009C ", " \u00009D ", " \u00009E ", " \u00009F ",
            " \u0000A0 ", " \u0000A1 ", " \u0000A2 ", " \u0000A3 ",
            " \u0000A4 ", " \u0000A5 ", " \u0000A6 ", " \u0000A7 ",
            " \u0000A8 ", " \u0000A9 ", " \u0000AA ", " \u0000AB ",
            " \u0000AC ", " \u0000AD ", " \u0000AE ", " \u0000AF ",
            " \u0000B0 ", " \u0000B1 ", " \u0000B2 ", " \u0000B3 ",
            " \u0000B4 ", " \u0000B5 ", " \u0000B6 ", " \u0000B7 ",
            " \u0000B8 ", " \u0000B9 ", " \u0000BA ", " \u0000BB ",
            " \u0000BC ", " \u0000BD ", " \u0000BE ", " \u0000BF ",
            " \u0000C0 ", " \u0000C1 ", " \u0000C2 ", " \u0000C3 ",
            " \u0000C4 ", " \u0000C5 ", " \u0000C6 ", " \u0000C7 ",
            " \u0000C8 ", " \u0000C9 ", " \u0000CA ", " \u0000CB ",
            " \u0000CC ", " \u0000CD ", " \u0000CE ", " \u0000CF ",
            " \u0000D0 ", " \u0000D1 ", " \u0000D2 ", " \u0000D3 ",
            " \u0000D4 ", " \u0000D5 ", " \u0000D6 ", " \u0000D7 ",
            " \u0000D8 ", " \u0000D9 ", " \u0000DA ", " \u0000DB ",
            " \u0000DC ", " \u0000DD ", " \u0000DE ", " \u0000DF ",
            " \u0000E0 ", " \u0000E1 ", " \u0000E2 ", " \u0000E3 ",
            " \u0000E4 ", " \u0000E5 ", " \u0000E6 ", " \u0000E7 ",
            " \u0000E8 ", " \u0000E9 ", " \u0000EA ", " \u0000EB ",
            " \u0000EC ", " \u0000ED ", " \u0000EE ", " \u0000EF ",
            " \u0000F0 ", " \u0000F1 ", " \u0000F2 ", " \u0000F3 ",
            " \u0000F4 ", " \u0000F5 ", " \u0000F6 ", " \u0000F7 ",
            " \u0000F8 ", " \u0000F9 ", " \u0000FA ", " \u0000FB ",
            " \u0000FC ", " \u0000FD ", " \u0000FE ", " \u0000FF "};

    /**
     * Method used to process data and add it to the datascope display
     * 
     * @param a_data
     * @param a_count
     * @param a_direction
     */
    void add(final byte[] a_data, final int a_count, Direction a_direction);

    /**
     * Add a message to the message pane of the datascope window.
     * 
     * @param a_message
     */
    void addMessage(final String a_message);

    /**
     * Close the data scope window
     */
    void close();
}
