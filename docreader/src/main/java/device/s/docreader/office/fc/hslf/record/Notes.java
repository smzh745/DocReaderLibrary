package device.s.docreader.office.fc.hslf.record;

/**
 * Master container for Notes. There is one of these for every page of
 *  notes, and they have certain specific children
 *
 * @author Nick Burch
 */

public final class Notes extends SheetContainer
{
    private byte[] _header;
    private static long _type = 1008l;


    /**
     * Returns the NotesAtom of this Notes
     */
    public NotesAtom getNotesAtom()
    {
        return notesAtom;
    }

    /**
     * Returns the PPDrawing of this Notes, which has all the
     *  interesting data in it
     */
    public PPDrawing getPPDrawing()
    {
        return ppDrawing;
    }

    /**
     * Set things up, and find our more interesting children
     */
    protected Notes(byte[] source, int start, int len)
    {
        // Grab the header
        _header = new byte[8];
        System.arraycopy(source, start, _header, 0, 8);

        // Find our children
        _children = Record.findChildRecords(source, start + 8, len - 8);

        // Find the interesting ones in there
        for (int i = 0; i < _children.length; i++)
        {
            if (_children[i] instanceof NotesAtom)
            {
                notesAtom = (NotesAtom)_children[i];
                //System.out.println("Found notes for sheet " + notesAtom.getSlideID());
            }
            if (_children[i] instanceof PPDrawing)
            {
                ppDrawing = (PPDrawing)_children[i];
            }
            if (ppDrawing != null && _children[i] instanceof ColorSchemeAtom)
            {
                _colorScheme = (ColorSchemeAtom)_children[i];
            }
        }
    }

    /**
     * We are of type 1008
     */
    public long getRecordType()
    {
        return _type;
    }

    public ColorSchemeAtom getColorScheme()
    {
        return _colorScheme;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        super.dispose();
        if (notesAtom != null)
        {
            notesAtom.dispose();
            notesAtom = null;
        }
        if (ppDrawing != null)
        {
            ppDrawing.dispose();
            ppDrawing = null;
        }
        if (_colorScheme != null)
        {
            _colorScheme.dispose();
            _colorScheme = null;
        }
    }
    

    // Links to our more interesting children
    private NotesAtom notesAtom;
    private PPDrawing ppDrawing;
    private ColorSchemeAtom _colorScheme;
}
