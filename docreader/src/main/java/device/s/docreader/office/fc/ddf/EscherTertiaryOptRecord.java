package device.s.docreader.office.fc.ddf;

public class EscherTertiaryOptRecord extends AbstractEscherOptRecord
{
    public static final short RECORD_ID = (short) 0xF122;

    public String getRecordName()
    {
        return "TertiaryOpt";
    }
    /**
     * 
     *
     */
    public void dispose()
    {
    }
}
