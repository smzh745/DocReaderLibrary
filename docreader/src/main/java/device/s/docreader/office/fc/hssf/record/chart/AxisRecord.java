package device.s.docreader.office.fc.hssf.record.chart;


import device.s.docreader.office.fc.hssf.record.RecordInputStream;
import device.s.docreader.office.fc.hssf.record.StandardRecord;
import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndianOutput;


/**
 * The axis record defines the type of an axis.<p/>
 * 
 * @author Glen Stampoultzis (glens at apache.org)
 */
public final class AxisRecord extends StandardRecord {
    public final static short      sid                             = 0x101d;
    private  short      field_1_axisType;
    public final static short       AXIS_TYPE_CATEGORY_OR_X_AXIS   = 0;
    public final static short       AXIS_TYPE_VALUE_AXIS           = 1;
    public final static short       AXIS_TYPE_SERIES_AXIS          = 2;
    private  int        field_2_reserved1;
    private  int        field_3_reserved2;
    private  int        field_4_reserved3;
    private  int        field_5_reserved4;


    public AxisRecord()
    {

    }

    public AxisRecord(RecordInputStream in)
    {
        field_1_axisType               = in.readShort();
        field_2_reserved1              = in.readInt();
        field_3_reserved2              = in.readInt();
        field_4_reserved3              = in.readInt();
        field_5_reserved4              = in.readInt();
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("[AXIS]\n");
        buffer.append("    .axisType             = ")
            .append("0x").append(HexDump.toHex(  getAxisType ()))
            .append(" (").append( getAxisType() ).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved1            = ")
            .append("0x").append(HexDump.toHex(  getReserved1 ()))
            .append(" (").append( getReserved1() ).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved2            = ")
            .append("0x").append(HexDump.toHex(  getReserved2 ()))
            .append(" (").append( getReserved2() ).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved3            = ")
            .append("0x").append(HexDump.toHex(  getReserved3 ()))
            .append(" (").append( getReserved3() ).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved4            = ")
            .append("0x").append(HexDump.toHex(  getReserved4 ()))
            .append(" (").append( getReserved4() ).append(" )");
        buffer.append(System.getProperty("line.separator"));

        buffer.append("[/AXIS]\n");
        return buffer.toString();
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(field_1_axisType);
        out.writeInt(field_2_reserved1);
        out.writeInt(field_3_reserved2);
        out.writeInt(field_4_reserved3);
        out.writeInt(field_5_reserved4);
    }

    protected int getDataSize() {
        return 2 + 4 + 4 + 4 + 4;
    }

    public short getSid()
    {
        return sid;
    }

    public Object clone() {
        AxisRecord rec = new AxisRecord();
    
        rec.field_1_axisType = field_1_axisType;
        rec.field_2_reserved1 = field_2_reserved1;
        rec.field_3_reserved2 = field_3_reserved2;
        rec.field_4_reserved3 = field_4_reserved3;
        rec.field_5_reserved4 = field_5_reserved4;
        return rec;
    }




    /**
     * Get the axis type field for the Axis record.
     *
     * @return  One of 
     *        AXIS_TYPE_CATEGORY_OR_X_AXIS
     *        AXIS_TYPE_VALUE_AXIS
     *        AXIS_TYPE_SERIES_AXIS
     */
    public short getAxisType()
    {
        return field_1_axisType;
    }

    /**
     * Set the axis type field for the Axis record.
     *
     * @param field_1_axisType
     *        One of 
     *        AXIS_TYPE_CATEGORY_OR_X_AXIS
     *        AXIS_TYPE_VALUE_AXIS
     *        AXIS_TYPE_SERIES_AXIS
     */
    public void setAxisType(short field_1_axisType)
    {
        this.field_1_axisType = field_1_axisType;
    }

    /**
     * Get the reserved1 field for the Axis record.
     */
    public int getReserved1()
    {
        return field_2_reserved1;
    }

    /**
     * Set the reserved1 field for the Axis record.
     */
    public void setReserved1(int field_2_reserved1)
    {
        this.field_2_reserved1 = field_2_reserved1;
    }

    /**
     * Get the reserved2 field for the Axis record.
     */
    public int getReserved2()
    {
        return field_3_reserved2;
    }

    /**
     * Set the reserved2 field for the Axis record.
     */
    public void setReserved2(int field_3_reserved2)
    {
        this.field_3_reserved2 = field_3_reserved2;
    }

    /**
     * Get the reserved3 field for the Axis record.
     */
    public int getReserved3()
    {
        return field_4_reserved3;
    }

    /**
     * Set the reserved3 field for the Axis record.
     */
    public void setReserved3(int field_4_reserved3)
    {
        this.field_4_reserved3 = field_4_reserved3;
    }

    /**
     * Get the reserved4 field for the Axis record.
     */
    public int getReserved4()
    {
        return field_5_reserved4;
    }

    /**
     * Set the reserved4 field for the Axis record.
     */
    public void setReserved4(int field_5_reserved4)
    {
        this.field_5_reserved4 = field_5_reserved4;
    }
}
