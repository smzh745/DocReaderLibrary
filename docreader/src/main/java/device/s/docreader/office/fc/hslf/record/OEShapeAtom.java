package device.s.docreader.office.fc.hslf.record;

import device.s.docreader.office.fc.util.LittleEndian;


/**
 * Atom that contains information that describes shape client data.
 *
 * @author Yegor Kozlov
 */
public final class OEShapeAtom extends RecordAtom
{

    /**
     * Record header.
     */
    private byte[] _header;

    /**
     * record data
     */
    private byte[] _recdata;

    /**
     * Constructs a brand new link related atom record.
     */
    public OEShapeAtom()
    {
        _recdata = new byte[4];

        _header = new byte[8];
        LittleEndian.putShort(_header, 2, (short)getRecordType());
        LittleEndian.putInt(_header, 4, _recdata.length);
    }

    /**
     * Constructs the link related atom record from its
     *  source data.
     *
     * @param source the source data as a byte array.
     * @param start the start offset into the byte array.
     * @param len the length of the slice in the byte array.
     */
    protected OEShapeAtom(byte[] source, int start, int len)
    {
        // Get the header
        _header = new byte[8];
        System.arraycopy(source, start, _header, 0, 8);

        // Grab the record data
        _recdata = new byte[len - 8];
        System.arraycopy(source, start + 8, _recdata, 0, len - 8);
    }

    /**
     * Gets the record type.
     * @return the record type.
     */
    public long getRecordType()
    {
        return RecordTypes.OEShapeAtom.typeID;
    }

    /**
     * shape flags.
     *
     * @return  shape flags.
     */
    public int getOptions()
    {
        return LittleEndian.getInt(_recdata, 0);
    }

    /**
     * shape flags.
     *
     * @param id  shape flags.
     */
    public void setOptions(int id)
    {
        LittleEndian.putInt(_recdata, 0, id);
    }
    
    /**
     * 
     */
    public void dispose()
    {
        _header = null;
        _recdata = null;
    }
}
