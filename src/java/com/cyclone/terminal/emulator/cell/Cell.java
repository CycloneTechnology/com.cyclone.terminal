/**
 * 
 */
package com.cyclone.terminal.emulator.cell;

import com.cyclone.terminal.emulator.CharacterSet;
import com.cyclone.terminal.emulator.CharacterSetSequence;

/**
 * @author Phil.Baxter
 * 
 */
public class Cell
{
    private int m_Row;

    private int m_Column;

    private char m_Character;

    private Rendition m_Rendition;

    private Attribute m_Attribute;

    private CharacterSetSequence m_characterSetSequence;

    private CharacterSet m_characterSet;

    private LineWidth m_lineWidth;

    private LineHeight m_lineHeight;

    /**
     * Constructor for Cell.
     * 
     * @param a_Row
     * @param a_Column
     */
    public Cell(final int a_Row, final int a_Column)
    {
        m_Row = a_Row;
        m_Column = a_Column;
        reset();
    }

    /**
     * @return the attributes for the cell
     */
    public final Attribute getAttribute()
    {
        return m_Attribute;
    }

    /**
     * @return the character to display in the cell
     */
    public final char getCharacter()
    {
        return m_Character;
    }

    /**
     * Set the character to display in the cell
     * 
     * @param a_character
     */
    public final void setCharacter(char a_character)
    {
        m_Character = a_character;
    }

    /**
     * @return the rendition of the character in this cell
     */
    public final Rendition getRendition()
    {
        return m_Rendition;
    }

    /**
     * Set the rendition to be used for this cells character
     * 
     * @param a_rendition
     */
    public final void setRendition(final Rendition a_rendition)
    {
        m_Rendition = Rendition.createClone(a_rendition);
    }

    /**
     * reset the cell to its default values, attributes etc.
     */
    public final void reset()
    {
        m_Character = 32; // Set to space initially
        m_Rendition = new Rendition();
        m_Attribute = new Attribute();
        m_lineWidth = LineWidth.NORMAL;
        m_lineHeight = LineHeight.NORMAL;
        m_characterSetSequence = CharacterSetSequence.G0;
        m_characterSet = CharacterSet.USASCII;

    }

    /**
     * @return the column at which this cell is displayed
     */
    public final int getColumn()
    {
        return m_Column;
    }

    /**
     * @return the row on which this cell is displayed
     */
    public final int getRow()
    {
        return m_Row;
    }

    /**
     * @return whether the line this cell is on has been set to display double
     *         height characters and if so wether it will be the top or bottom
     *         half of the character that is displayed
     */
    public final LineHeight getLineHeight()
    {
        return m_lineHeight;
    }

    /**
     * @param a_doubleHeight
     */
    public final void setLineHeight(LineHeight a_doubleHeight)
    {
        m_lineHeight = a_doubleHeight;
    }

    /**
     * @return whether the line this cell is on has been set to display double
     *         width characters
     */
    public final LineWidth getLineWidth()
    {
        return m_lineWidth;
    }

    /**
     * @param a_lineWidth
     */
    public final void setLineWidth(LineWidth a_lineWidth)
    {
        m_lineWidth = a_lineWidth;
    }

    /**
     * @param a_row
     */
    public final void setRow(int a_row)
    {
        m_Row = a_row;
    }

    /**
     * @return the character set to use for this cell
     */
    public final CharacterSet getCharacterSet()
    {
        return m_characterSet;
    }

    /**
     * @param a_characterSet
     */
    public final void setCharacterSet(CharacterSet a_characterSet)
    {
        m_characterSet = a_characterSet;
    }

    /**
     * @return the character set sequence to use for this cell
     */
    public final CharacterSetSequence getCharacterSetsSequence()
    {
        return m_characterSetSequence;
    }

    /**
     * @param a_characterSetSequence
     */
    public final void setCharacterSetSequence(
            CharacterSetSequence a_characterSetSequence)
    {
        m_characterSetSequence = a_characterSetSequence;
    }
}
