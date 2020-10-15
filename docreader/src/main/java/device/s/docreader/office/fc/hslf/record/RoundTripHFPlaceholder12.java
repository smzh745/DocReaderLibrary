package device.s.docreader.office.fc.hslf.record;

/**
 * An atom record that specifies that a shape is a header or footer placeholder shape
 *
 * @since  PowerPoint 2007
 * @author Yegor Kozlov
 */
public final class RoundTripHFPlaceholder12 extends RecordAtom
{
    /**
     * Record header.
     */
    private byte[] _header;

    /**
     * Specifies the placeholder shape ID.
     *
     * MUST be {@link OEPlaceholderAtom#MasterDate},  {@link OEPlaceholderAtom#MasterSlideNumber},
     * {@link OEPlaceholderAtom#MasterFooter}, or {@link OEPlaceholderAtom#MasterHeader}
     */
    private byte _placeholderId;

    /**
     * Constructs the comment atom record from its source data.
     *
     * @param source the source data as a byte array.
     * @param start the start offset into the byte array.
     * @param len the length of the slice in the byte array.
     */
    protected RoundTripHFPlaceholder12(byte[] source, int start, int len)
    {
        // Get the header.
        _header = new byte[8];
        System.arraycopy(source, start, _header, 0, 8);

        // Get the record data.
        _placeholderId = source[start + 8];
    }

    /**
     * Gets the comment number (note - each user normally has their own count).
     * @return the comment number.
     */
    public int getPlaceholderId()
    {
        return _placeholderId;
    }

    /**
     * Sets the comment number (note - each user normally has their own count).
     * @param number the comment number.
     */
    public void setPlaceholderId(int number)
    {
        _placeholderId = (byte)number;
    }

    /**
     * Gets the record type.
     * @return the record type.
     */
    public long getRecordType()
    {
        return RecordTypes.RoundTripHFPlaceholder12.typeID;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        _header = null;
    }
}
