package device.s.docreader.office.fc.hslf.model.textproperties;

/** 
 * Definition for the common character text property bitset, which
 *  handles bold/italic/underline etc.
 */
public class CharFlagsTextProp extends BitMaskTextProp
{
    public static final int BOLD_IDX = 0;
    public static final int ITALIC_IDX = 1;
    public static final int UNDERLINE_IDX = 2;
    public static final int SHADOW_IDX = 4;
    public static final int STRIKETHROUGH_IDX = 8;
    public static final int RELIEF_IDX = 9;
    public static final int RESET_NUMBERING_IDX = 10;
    public static final int ENABLE_NUMBERING_1_IDX = 11;
    public static final int ENABLE_NUMBERING_2_IDX = 12;

    public static String NAME = "char_flags";

    public CharFlagsTextProp()
    {
        super(2, 0xffff, NAME, new String[]{"bold", // 0x0001  A bit that specifies whether the characters are bold.
            "italic", // 0x0002  A bit that specifies whether the characters are italicized.
            "underline", // 0x0004  A bit that specifies whether the characters are underlined.
            "char_unknown_1", // 0x0008  Undefined and MUST be ignored.
            "shadow", // 0x0010  A bit that specifies whether the characters have a shadow effect.
            "fehint", // 0x0020  A bit that specifies whether characters originated from double-byte input.
            "char_unknown_2", // 0x0040  Undefined and MUST be ignored.
            "kumi", // 0x0080  A bit that specifies whether Kumimoji are used for vertical text.
            "strikethrough", // 0x0100  Undefined and MUST be ignored.
            "emboss", // 0x0200  A bit that specifies whether the characters are embossed.
            "char_unknown_3", // 0x0400  Undefined and MUST be ignored.
            "char_unknown_4", // 0x0800  Undefined and MUST be ignored.
            "char_unknown_5", // 0x1000  Undefined and MUST be ignored.
        });
    }
}
