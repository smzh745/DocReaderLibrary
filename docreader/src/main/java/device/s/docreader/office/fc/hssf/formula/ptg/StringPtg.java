package device.s.docreader.office.fc.hssf.formula.ptg;

import device.s.docreader.office.fc.util.LittleEndianInput;
import device.s.docreader.office.fc.util.LittleEndianOutput;
import device.s.docreader.office.fc.util.StringUtil;

/**
 * String Stores a String value in a formula value stored in the format
 * &lt;length 2 bytes&gt;char[]
 * 
 * @author Werner Froidevaux
 * @author Jason Height (jheight at chariot dot net dot au)
 * @author Bernard Chesnoy
 */
public final class StringPtg extends ScalarConstantPtg {
     public final static byte sid = 0x17;
    /** the character (") used in formulas to delimit string literals */
    private static final char FORMULA_DELIMITER = '"';

    private final boolean _is16bitUnicode;
    /**
     * NOTE: OO doc says 16bit length, but BiffViewer says 8 Book says something
     * totally different, so don't look there!
     */
    private final String field_3_string;

    /** Create a StringPtg from a stream */
    public StringPtg(LittleEndianInput in)  {
    	int nChars = in.readUByte(); // Note - nChars is 8-bit
    	_is16bitUnicode = (in.readByte() & 0x01) != 0;
    	if (_is16bitUnicode) {
    		field_3_string = StringUtil.readUnicodeLE(in, nChars);
    	} else {
    		field_3_string = StringUtil.readCompressedUnicode(in, nChars);
    	}
    }

    /**
     * Create a StringPtg from a string representation of the number Number
     * format is not checked, it is expected to be validated in the parser that
     * calls this method.
     * 
     * @param value :
     *            String representation of a floating point number
     */
    public StringPtg(String value) {
        if (value.length() > 255) {
            throw new IllegalArgumentException(
                    "String literals in formulas can't be bigger than 255 characters ASCII");
        }
        _is16bitUnicode = StringUtil.hasMultibyte(value);
        field_3_string = value;
    }

    public String getValue() {
        return field_3_string;
    }

    public void write(LittleEndianOutput out) {
        out.writeByte(sid + getPtgClass());
        out.writeByte(field_3_string.length()); // Note - nChars is 8-bit
        out.writeByte(_is16bitUnicode ? 0x01 : 0x00);
        if (_is16bitUnicode) {
        	StringUtil.putUnicodeLE(field_3_string, out);
        } else {
        	StringUtil.putCompressedUnicode(field_3_string, out);
        }
    }

    public int getSize() {
    	return 3 +  field_3_string.length() * (_is16bitUnicode ? 2 : 1);
    }

    public String toFormulaString() {
        String value = field_3_string;
        int len = value.length();
        StringBuffer sb = new StringBuffer(len + 4);
        sb.append(FORMULA_DELIMITER);

        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if (c == FORMULA_DELIMITER) {
                sb.append(FORMULA_DELIMITER);
            }
            sb.append(c);
        }

        sb.append(FORMULA_DELIMITER);
        return sb.toString();
    }
}
