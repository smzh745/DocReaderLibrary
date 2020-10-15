package device.s.docreader.office.fc.hssf.record;

import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndianOutput;
import device.s.docreader.office.fc.util.StringUtil;

/**
 * Title:        Format Record (0x041E) <p/>
 * Description:  describes a number format -- those goofy strings like $(#,###)<p/>
 *
 * REFERENCE:  PG 317 Microsoft Excel 97 Developer's Kit (ISBN: 1-57231-498-2)<p/>
 * @author Andrew C. Oliver (acoliver at apache dot org)
 * @author Shawn M. Laubach (slaubach at apache dot org)
 */
public final class FormatRecord extends StandardRecord {
    public final static short sid = 0x041E;

    private final int field_1_index_code;
    private final boolean field_3_hasMultibyte;
    private final String field_4_formatstring;

    public FormatRecord(int indexCode, String fs) {
        field_1_index_code = indexCode;
        field_4_formatstring = fs;
        field_3_hasMultibyte = StringUtil.hasMultibyte(fs);
    }

    public FormatRecord(RecordInputStream in) {
        field_1_index_code = in.readShort();
        int field_3_unicode_len = in.readUShort();
        field_3_hasMultibyte = (in.readByte() & 0x01) != 0;

        if (field_3_hasMultibyte) {
            field_4_formatstring = in.readUnicodeLEString(field_3_unicode_len);
        } else {
            field_4_formatstring = in.readCompressedUnicode(field_3_unicode_len);
        }
    }

    /**
     * get the format index code (for built in formats)
     *
     * @return the format index code
     * @see device.s.docreader.office.fc.hssf.model.InternalWorkbook
     */
    public int getIndexCode() {
        return field_1_index_code;
    }

    /**
     * get the format string
     *
     * @return the format string
     */
    public String getFormatString() {
        return field_4_formatstring;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("[FORMAT]\n");
        buffer.append("    .indexcode       = ").append(HexDump.shortToHex(getIndexCode())).append("\n");
        buffer.append("    .isUnicode       = ").append(field_3_hasMultibyte ).append("\n");
        buffer.append("    .formatstring    = ").append(getFormatString()).append("\n");
        buffer.append("[/FORMAT]\n");
        return buffer.toString();
    }

    public void serialize(LittleEndianOutput out) {
        String formatString = getFormatString();
        out.writeShort(getIndexCode());
        out.writeShort(formatString.length());
        out.writeByte(field_3_hasMultibyte ? 0x01 : 0x00);

      if ( field_3_hasMultibyte ) {
          StringUtil.putUnicodeLE( formatString, out);
      }  else {
          StringUtil.putCompressedUnicode( formatString, out);
      }
    }
    protected int getDataSize() {
        return 5 // 2 shorts + 1 byte
            + getFormatString().length() * (field_3_hasMultibyte ? 2 : 1);
    }

    public short getSid() {
        return sid;
    }
    @Override
    public Object clone() {
        // immutable
        return this;
    }
}
