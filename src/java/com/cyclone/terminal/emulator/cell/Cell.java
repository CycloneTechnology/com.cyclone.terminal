package com.cyclone.terminal.emulator.cell;

import com.cyclone.terminal.emulator.CharacterSet;
import com.cyclone.terminal.emulator.CharacterSetSequence;

/**
 * @author Phil.Baxter
 * 
 */
public class Cell
{
    private int row;

    private int column;

    private char character;

    private Rendition rendition;

    private Attribute attribute;

    private CharacterSetSequence characterSetSequence;

    private CharacterSet characterSet;

    private LineWidth lineWidth;

    private LineHeight lineHeight;

    /**
     * Constructor for Cell.
     * 
     * @param a_Row
     * @param a_Column
     */
    public Cell(final int a_Row, final int a_Column)
    {
        row = a_Row;
        column = a_Column;
        reset();
    }

    /**
     * @return the attributes for the cell
     */
    public final Attribute getAttribute()
    {
        return attribute;
    }

    /**
     * @return the character to display in the cell
     */
    public final char getCharacter()
    {
        return character;
    }

    /**
     * Set the character to display in the cell
     * 
     * @param a_character
     */
    public final void setCharacter(char a_character)
    {
        character = a_character;
    }

    /**
     * @return the rendition of the character in this cell
     */
    public final Rendition getRendition()
    {
        return rendition;
    }

    /**
     * Set the rendition to be used for this cells character
     * 
     * @param a_rendition
     */
    public final void setRendition(final Rendition a_rendition)
    {
        rendition = Rendition.createClone(a_rendition);
    }

    /**
     * reset the cell to its default values, attributes etc.
     */
    public final void reset()
    {
        character = 32; // Set to space initially
        rendition = new Rendition();
        attribute = new Attribute();
        lineWidth = LineWidth.NORMAL;
        lineHeight = LineHeight.NORMAL;
        characterSetSequence = CharacterSetSequence.G0;
        characterSet = CharacterSet.USASCII;

    }

    /**
     * @return the column at which this cell is displayed
     */
    public final int getColumn()
    {
        return column;
    }

    /**
     * @return the row on which this cell is displayed
     */
    public final int getRow()
    {
        return row;
    }

    /**
     * @return whether the line this cell is on has been set to display double
     *         height characters and if so wether it will be the top or bottom
     *         half of the character that is displayed
     */
    public final LineHeight getLineHeight()
    {
        return lineHeight;
    }

    /**
     * @param a_doubleHeight
     */
    public final void setLineHeight(LineHeight a_doubleHeight)
    {
        lineHeight = a_doubleHeight;
    }

    /**
     * @return whether the line this cell is on has been set to display double
     *         width characters
     */
    public final LineWidth getLineWidth()
    {
        return lineWidth;
    }

    /**
     * @param a_lineWidth
     */
    public final void setLineWidth(LineWidth a_lineWidth)
    {
        lineWidth = a_lineWidth;
    }

    /**
     * @param a_row
     */
    public final void setRow(int a_row)
    {
        row = a_row;
    }

    /**
     * @return the character set to use for this cell
     */
    public final CharacterSet getCharacterSet()
    {
        return characterSet;
    }

    /**
     * @param a_characterSet
     */
    public final void setCharacterSet(CharacterSet a_characterSet)
    {
        characterSet = a_characterSet;
    }

    /**
     * @return the character set sequence to use for this cell
     */
    public final CharacterSetSequence getCharacterSetsSequence()
    {
        return characterSetSequence;
    }

    /**
     * @param a_characterSetSequence
     */
    public final void setCharacterSetSequence(
            CharacterSetSequence a_characterSetSequence)
    {
        characterSetSequence = a_characterSetSequence;
    }
}
