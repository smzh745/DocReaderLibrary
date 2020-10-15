package device.s.docreader.office.fc.hslf.record;


import device.s.docreader.office.fc.util.StringUtil;

/**
 * A Document Encryption Atom (type 12052). Holds information
 *  on the Encryption of a Document
 *
 * @author Nick Burch
 */
public final class DocumentEncryptionAtom extends RecordAtom
{
    private byte[] _header;
    private static long _type = 12052l;

    private byte[] data;
    private String encryptionProviderName;

    /**
     * For the Document Encryption Atom
     */
    protected DocumentEncryptionAtom(byte[] source, int start, int len)
    {
        // Get the header
        _header = new byte[8];
        System.arraycopy(source, start, _header, 0, 8);

        // Grab everything else, for now
        data = new byte[len - 8];
        System.arraycopy(source, start + 8, data, 0, len - 8);

        // Grab the provider, from byte 8+44 onwards
        // It's a null terminated Little Endian String
        int endPos = -1;
        int pos = start + 8 + 44;
        while (pos < (start + len) && endPos < 0)
        {
            if (source[pos] == 0 && source[pos + 1] == 0)
            {
                // Hit the end
                endPos = pos;
            }
            pos += 2;
        }
        pos = start + 8 + 44;
        int stringLen = (endPos - pos) / 2;
        encryptionProviderName = StringUtil.getFromUnicodeLE(source, pos, stringLen);
    }

    /**
     * Return the length of the encryption key, in bits
     */
    public int getKeyLength()
    {
        return data[28];
    }

    /**
     * Return the name of the encryption provider used
     */
    public String getEncryptionProviderName()
    {
        return encryptionProviderName;
    }

    /**
     * We are of type 12052
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
        _header = null;
        data = null;
        encryptionProviderName = null;
    }

    
}
