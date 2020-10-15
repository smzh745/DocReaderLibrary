package device.s.docreader.office.fc.hslf.record;

/**
 * Is a container for all sound related atoms and containers. It contains:
 *<li>1. SoundCollAtom (2021)
 *<li>2. Sound (2022), for each sound, if any
 *
 * @author Yegor Kozlov
 */
public final class SoundCollection extends RecordContainer {
    /**
     * Record header data.
     */
    private byte[] _header;

    /**
     * Set things up, and find our more interesting children
     *
     * @param source the source data as a byte array.
     * @param start the start offset into the byte array.
     * @param len the length of the slice in the byte array.
     */
    protected SoundCollection(byte[] source, int start, int len) {
        // Grab the header
        _header = new byte[8];
        System.arraycopy(source,start,_header,0,8);

        // Find our children
        _children = Record.findChildRecords(source,start+8,len-8);
    }

    /**
     * Returns the type (held as a little endian in bytes 3 and 4)
     * that this class handles.
     *
     * @return the record type.
     */
    public long getRecordType() {
        return RecordTypes.SoundCollection.typeID;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        super.dispose();
        _header = null;
    }

}
