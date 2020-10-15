
package device.s.docreader.office.fc.ddf;

import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndian;
import device.s.docreader.office.fc.util.RecordFormatException;


public class EscherTextboxRecord extends EscherRecord
{
    public static final short RECORD_ID = (short)0xF00D;
    public static final String RECORD_DESCRIPTION = "msofbtClientTextbox";

    private static final byte[] NO_BYTES = new byte[0];

    /** The data for this record not including the the 8 byte header */
    private byte[] thedata = NO_BYTES;

    public EscherTextboxRecord()
    {
    }

    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader( data, offset );

        // Save the data, ready for the calling code to do something
        //  useful with it
        thedata = new byte[bytesRemaining];
        System.arraycopy( data, offset + 8, thedata, 0, bytesRemaining );
        return bytesRemaining + 8;
    }

    public int serialize( int offset, byte[] data, EscherSerializationListener listener )
    {
        listener.beforeRecordSerialize( offset, getRecordId(), this );

        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset+2, getRecordId());
        int remainingBytes = thedata.length;
        LittleEndian.putInt(data, offset+4, remainingBytes);
        System.arraycopy(thedata, 0, data, offset+8, thedata.length);
        int pos = offset+8+thedata.length;

        listener.afterRecordSerialize( pos, getRecordId(), pos - offset, this );
        int size = pos - offset;
        if (size != getRecordSize())
            throw new RecordFormatException(size + " bytes written but getRecordSize() reports " + getRecordSize());
        return size;
    }

    /**
     * Returns any extra data associated with this record.  In practice excel
     * does not seem to put anything here, but with PowerPoint this will
     * contain the bytes that make up a TextHeaderAtom followed by a
     * TextBytesAtom/TextCharsAtom
     */
    public byte[] getData()
    {
        return thedata;
    }

    /**
     * Sets the extra data (in the parent application's format) to be
     * contained by the record. Used when the parent application changes
     * the contents.
     */
    public void setData(byte[] b, int start, int length)
    {
        thedata = new byte[length];
        System.arraycopy(b,start,thedata,0,length);
    }
    public void setData(byte[] b) {
        setData(b,0,b.length);
    }

    public int getRecordSize()
    {
        return 8 + thedata.length;
    }

    public Object clone()
    {
        // shallow clone
        return super.clone();
    }

    public String getRecordName() {
        return "ClientTextbox";
    }

    public String toString()
    {
        String nl = System.getProperty( "line.separator" );

        String theDumpHex = "";
        try
        {
            if (thedata.length != 0)
            {
                theDumpHex = "  Extra Data:" + nl;
                theDumpHex += HexDump.dump(thedata, 0, 0);
            }
        }
        catch ( Exception e )
        {
            theDumpHex = "Error!!";
        }

        return getClass().getName() + ":" + nl +
                "  isContainer: " + isContainerRecord() + nl +
                "  options: 0x" + HexDump.toHex( getOptions() ) + nl +
                "  recordId: 0x" + HexDump.toHex( getRecordId() ) + nl +
                "  numchildren: " + getChildRecords().size() + nl +
                theDumpHex;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        thedata = null;
    }

}



