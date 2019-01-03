/**
 *
 */
package com.cyclone.terminal.emulator.font.VT100;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

import com.cyclone.terminal.emulator.EmulatorException;
import com.cyclone.terminal.emulator.cell.BlinkState;
import com.cyclone.terminal.emulator.cell.Cell;
import com.cyclone.terminal.emulator.font.VT100.characters.set1.Set1CharArray;
import com.cyclone.terminal.emulator.font.VT100.characters.set2.Set2CharArray;

/**
 * @author Phil.Baxter
 * 
 */
public class CharSet implements CharPatterns
{
    private final Device device;

    /**
     * Constructor for CharSet. This construct6or creates an empty char set, you
     * can only use the {@link #getImage(Cell, boolean, BlinkState)} method to
     * get charcaters when this constructor has been used.
     * 
     * @param a_device
     */
    public CharSet(final Device a_device)
    {
        device = a_device;
        // generateCharacterClasses("images/glyphs/glyph_strip.bmp", 255,
        // "com\\cyclone\\terminal\\emulator\\font\\VT100\\characters\\set1\\");
        // generateCharacterClasses("images/glyphs/glyph_strip2.bmp", 255,
        // "com\\cyclone\\terminal\\emulator\\font\\VT100\\characters\\set2\\");
    }

    /**
     * @see com.cyclone.terminal.emulator.font.VT100.CharPatterns#getImage(com.cyclone.terminal.emulator.cell.Cell,
     *      boolean, com.cyclone.terminal.emulator.cell.BlinkState)
     */
    @Override
    public final Image getImage(final Cell a_cell, final boolean a_selected,
            final BlinkState a_blinkState) throws EmulatorException
    {
        final Image image;

        switch (a_cell.getCharacterSetsSequence())
        {
            default:
            case G0:
                switch (a_cell.getCharacterSet())
                {
                    default:
                    case UK:
                    case USASCII:
                    case ALTERNATESET_STANDARD:
                        image = Set1CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                    case SPECIALGRAPHICS:
                    case ALTERNATESET_SPECIALGRAPHICS:
                        image = Set2CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                }
                break;
            case G1:
                switch (a_cell.getCharacterSet())
                {
                    default:
                    case UK:
                    case USASCII:
                    case ALTERNATESET_STANDARD:
                        image = Set1CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                    case SPECIALGRAPHICS:
                    case ALTERNATESET_SPECIALGRAPHICS:
                        image = Set2CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                }
                break;
            case G2:
                switch (a_cell.getCharacterSet())
                {
                    default:
                    case UK:
                    case USASCII:
                    case ALTERNATESET_STANDARD:
                        image = Set1CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                    case SPECIALGRAPHICS:
                    case ALTERNATESET_SPECIALGRAPHICS:
                        image = Set2CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                }
                break;
            case G3:
                switch (a_cell.getCharacterSet())
                {
                    default:
                    case UK:
                    case USASCII:
                    case ALTERNATESET_STANDARD:
                        image = Set1CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                    case SPECIALGRAPHICS:
                    case ALTERNATESET_SPECIALGRAPHICS:
                        image = Set2CharArray.PATTERN[a_cell.getCharacter()]
                                .getImage(device, a_cell.getRendition(),
                                        a_selected, a_blinkState);
                        break;
                }
                break;
        }

        return image;
    }

    /**
     * @see com.cyclone.terminal.emulator.font.VT100.CharPatterns#getCharSize()
     */
    @Override
    public final Point getCharSize()
    {
        return Set1CharArray.PATTERN[0].getSize();
    }

    /**
     * @param a_glyphFile
     * @param a_charCount
     * @param a_package
     */
    public final void generateCharacterClasses(final String a_glyphFile,
            final int a_charCount, final String a_package)
    {
        final MessageFormat characterIdFormatter = new MessageFormat(
                "{0,number,000}");

        final Image characterImage = new Image(device, a_glyphFile);

        final ImageData imageData = characterImage.getImageData();

        int charNumber = 0;
        while (charNumber <= a_charCount)
        {

            final int[][] character = new int[15][36];

            for (int column = 0; column < 15; column++)
            {
                for (int row = 0; row < 36; row++)
                {
                    character[column][row] = imageData.data[(row * imageData.bytesPerLine)
                            + (charNumber * 15) + column] > 1 ? 0 : 1;
                }
            }
            // Character done, output code...
            final Integer[] charNum = new Integer[]
            {charNumber};
            final String thisCharacter = characterIdFormatter.format(charNum);

            try (final FileOutputStream out = new FileOutputStream(
                    "D:\\netPrefect\\cyclone_terminal\\src\\java\\" + a_package
                            + "\\CHAR" + thisCharacter + ".java"))
            {
                try (final PrintStream p = new PrintStream(out))
                {
                    p.println("/**");
                    p.println(" * ");
                    p.println(" */");
                    p.println("package "
                            + a_package.replace('\\', '.').substring(0,
                                    a_package.length() - 1) + ";");
                    p.println();
                    p.println("import org.eclipse.swt.graphics.Point;");
                    p.println();
                    p.println("import com.cyclone.terminal.emulator.font.VT100.characters.RenderImage;");
                    p.println("/**");
                    p.println(" * @author Phil.Baxter");
                    p.println(" * ");
                    p.println(" */");
                    p.println("public class CHAR" + thisCharacter
                            + " extends RenderImage");
                    p.println("{");
                    p.println("/**");
                    p.println(" * ");
                    p.println(" */");
                    p.println("private static final int[] DATA = new int[] {");
                    p.println("//");
                    for (int iRow = 0; iRow < 36; iRow++)
                    {
                        for (int iColumn = 0; iColumn < 15; iColumn++)
                        {
                            p.print(character[iColumn][iRow] + ", ");
                        }
                        p.println();
                    }
                    p.println("};");
                    p.println();
                    p.println("/**");
                    p.println(" * @see com.cyclone.netPrefect.gui.terminal.emulator.font.VT100.characters.RenderImage#getData()");
                    p.println(" */");
                    p.println("@Override");
                    p.println("public final int[] getData()");
                    p.println("{");
                    p.println("    return DATA;");
                    p.println("}");
                    p.println("");
                    p.println("/**");
                    p.println(" * @see com.cyclone.netPrefect.gui.terminal.emulator.font.VT100.characters.RenderImage#getSize()");
                    p.println(" */");
                    p.println("@Override");
                    p.println("public final Point getSize()");
                    p.println("{");
                    p.println("    return new Point(15, 36);");
                    p.println("}");
                    p.println("}");
                }
            }
            catch (FileNotFoundException e)
            {
            }
            catch (IOException e1)
            {
            }
            charNumber++;
        }

        System.out.println();
        System.out.println("/**");
        System.out.println(" * ");
        System.out.println(" */");
        System.out.println("int[][] CharPatterns = new int[][]{");

        for (charNumber = 0; charNumber <= a_charCount; charNumber++)
        {
            final Integer[] charNum = new Integer[]
            {charNumber};
            System.out.println("new CHAR_"
                    + characterIdFormatter.format(charNum) + "(),");
        }
        System.out.println("};");

        characterImage.dispose();
    }
}
