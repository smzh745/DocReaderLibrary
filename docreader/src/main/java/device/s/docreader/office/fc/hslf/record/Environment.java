package device.s.docreader.office.fc.hslf.record;

/**
 * Environment, which contains lots of settings for the document.
 *
 * @author Nick Burch
 */

public final class Environment extends PositionDependentRecordContainer
{

    /**
     * Returns the FontCollection of this Environment
     */
    public FontCollection getFontCollection()
    {
        return fontCollection;
    }

    /**
     * Set things up, and find our more interesting children
     */
    protected Environment(byte[] source, int start, int len)
    {
        // Grab the header
        _header = new byte[8];
        System.arraycopy(source, start, _header, 0, 8);

        // Find our children
        _children = Record.findChildRecords(source, start + 8, len - 8);

        // Find our FontCollection record
        for (int i = 0; i < _children.length; i++)
        {
            if (_children[i] instanceof FontCollection)
            {
                fontCollection = (FontCollection)_children[i];
            }
            else if (_children[i] instanceof TxMasterStyleAtom)
            {
                txmaster = (TxMasterStyleAtom)_children[i];
            }
        }

        if (fontCollection == null)
        {
            throw new IllegalStateException("Environment didn't contain a FontCollection record!");
        }
    }

    public TxMasterStyleAtom getTxMasterStyleAtom()
    {
        return txmaster;
    }

    /**
     * We are of type 1010
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
        if (fontCollection != null)
        {
            fontCollection.dispose();
            fontCollection = null;
        }
        if (txmaster != null)
        {
            txmaster.dispose();
            txmaster = null;
        }
    }
    
    private byte[] _header;
    private static long _type = 1010;

    // Links to our more interesting children
    private FontCollection fontCollection;
    //master style for text with type=TextHeaderAtom.OTHER_TYPE
    private TxMasterStyleAtom txmaster;
}
