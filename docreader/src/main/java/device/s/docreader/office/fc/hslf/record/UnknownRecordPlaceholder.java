package device.s.docreader.office.fc.hslf.record;

import device.s.docreader.office.fc.util.LittleEndian;

/**
 * If we come across a record we don't know about, we create one of
 *  these. It allows us to keep track of what it contains, so we can
 *  write it back out to disk unchanged
 *
 * @author Nick Burch
 */

public final class UnknownRecordPlaceholder extends RecordAtom
{
    private byte[] _contents;
    private long _type;

    /**
     * Create a new holder for a record we don't grok
     */
    protected UnknownRecordPlaceholder(byte[] source, int start, int len)
    {
        // Sanity Checking - including whole header, so treat
        //  length as based of 0, not 8 (including header size based)
        if (len < 0)
        {
            len = 0;
        }

        // Treat as an atom, grab and hold everything
        _contents = new byte[len];
        System.arraycopy(source, start, _contents, 0, len);
        _type = LittleEndian.getUShort(_contents, 2);
    }

    /**
     * Return the value we were given at creation
     */
    public long getRecordType()
    {
        return _type;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        _contents = null;
    }
}
