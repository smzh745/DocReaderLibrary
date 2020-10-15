package device.s.docreader.office.fc.hssf.record.chart;


import device.s.docreader.office.fc.hssf.record.RecordInputStream;
import device.s.docreader.office.fc.hssf.record.StandardRecord;
import device.s.docreader.office.fc.util.BitField;
import device.s.docreader.office.fc.util.BitFieldFactory;
import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndianOutput;


/**
 * The area record is used to define a area chart.<p/>
 * 
 * @author Glen Stampoultzis (glens at apache.org)
 */
public final class AreaRecord extends StandardRecord {
    public final static short      sid                             = 0x101A;
    private  short      field_1_formatFlags;
    private static final BitField stacked             = BitFieldFactory.getInstance(0x1);
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(0x2);
    private static final BitField shadow              = BitFieldFactory.getInstance(0x4);


    public AreaRecord()
    {

    }

    public AreaRecord(RecordInputStream in)
    {

        field_1_formatFlags            = in.readShort();
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("[AREA]\n");
        buffer.append("    .formatFlags          = ")
            .append("0x").append(HexDump.toHex(  getFormatFlags ()))
            .append(" (").append( getFormatFlags() ).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("         .stacked                  = ").append(isStacked()).append('\n'); 
        buffer.append("         .displayAsPercentage      = ").append(isDisplayAsPercentage()).append('\n'); 
        buffer.append("         .shadow                   = ").append(isShadow()).append('\n'); 

        buffer.append("[/AREA]\n");
        return buffer.toString();
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(field_1_formatFlags);
    }

    protected int getDataSize() {
        return 2;
    }

    public short getSid()
    {
        return sid;
    }

    public Object clone() {
        AreaRecord rec = new AreaRecord();
    
        rec.field_1_formatFlags = field_1_formatFlags;
        return rec;
    }




    /**
     * Get the format flags field for the Area record.
     */
    public short getFormatFlags()
    {
        return field_1_formatFlags;
    }

    /**
     * Set the format flags field for the Area record.
     */
    public void setFormatFlags(short field_1_formatFlags)
    {
        this.field_1_formatFlags = field_1_formatFlags;
    }

    /**
     * Sets the stacked field value.
     * series is stacked
     */
    public void setStacked(boolean value)
    {
        field_1_formatFlags = stacked.setShortBoolean(field_1_formatFlags, value);
    }

    /**
     * series is stacked
     * @return  the stacked field value.
     */
    public boolean isStacked()
    {
        return stacked.isSet(field_1_formatFlags);
    }

    /**
     * Sets the display as percentage field value.
     * results displayed as percentages
     */
    public void setDisplayAsPercentage(boolean value)
    {
        field_1_formatFlags = displayAsPercentage.setShortBoolean(field_1_formatFlags, value);
    }

    /**
     * results displayed as percentages
     * @return  the display as percentage field value.
     */
    public boolean isDisplayAsPercentage()
    {
        return displayAsPercentage.isSet(field_1_formatFlags);
    }

    /**
     * Sets the shadow field value.
     * display a shadow for the chart
     */
    public void setShadow(boolean value)
    {
        field_1_formatFlags = shadow.setShortBoolean(field_1_formatFlags, value);
    }

    /**
     * display a shadow for the chart
     * @return  the shadow field value.
     */
    public boolean isShadow()
    {
        return shadow.isSet(field_1_formatFlags);
    }
}
