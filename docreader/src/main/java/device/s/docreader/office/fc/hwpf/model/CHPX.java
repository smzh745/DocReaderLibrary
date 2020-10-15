package device.s.docreader.office.fc.hwpf.model;

import device.s.docreader.office.fc.hwpf.sprm.CharacterSprmUncompressor;
import device.s.docreader.office.fc.hwpf.sprm.SprmBuffer;
import device.s.docreader.office.fc.hwpf.usermodel.CharacterProperties;
import device.s.docreader.office.fc.util.Internal;


/**
 * DANGER - works in bytes!
 *
 * Make sure you call getStart() / getEnd() when you want characters
 *  (normal use), but getStartByte() / getEndByte() when you're
 *  reading in / writing out!
 *
 * @author Ryan Ackley
 */
@ Internal
@SuppressWarnings("deprecation")
public final class CHPX extends BytePropertyNode<CHPX>
{

    @Deprecated
    public CHPX(int fcStart, int fcEnd, CharIndexTranslator translator, byte[] grpprl)
    {
        super(fcStart, translator.lookIndexBackward(fcEnd), translator, new SprmBuffer(grpprl, 0));
    }

    @Deprecated
    public CHPX(int fcStart, int fcEnd, CharIndexTranslator translator, SprmBuffer buf)
    {
        super(fcStart, translator.lookIndexBackward(fcEnd), translator, buf);
    }

    CHPX(int charStart, int charEnd, SprmBuffer buf)
    {
        super(charStart, charEnd, buf);
    }

    public byte[] getGrpprl()
    {
        return ((SprmBuffer)_buf).toByteArray();
    }

    public SprmBuffer getSprmBuf()
    {
        return (SprmBuffer)_buf;
    }

    public CharacterProperties getCharacterProperties(StyleSheet ss, short istd)
    {
        if (ss == null)
        {
            // TODO Fix up for Word 6/95
            return new CharacterProperties();
        }

        CharacterProperties baseStyle = ss.getCharacterStyle(istd);
        if (baseStyle == null)
            baseStyle = new CharacterProperties();

        CharacterProperties props = CharacterSprmUncompressor.uncompressCHP(baseStyle, getGrpprl(),
            0);
        return props;
    }

    public String toString()
    {
        return "CHPX from " + getStart() + " to " + getEnd() + " (in bytes " + getStartBytes()
            + " to " + getEndBytes() + ")";
    }
}
