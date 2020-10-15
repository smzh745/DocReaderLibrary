package device.s.docreader.office.fc.hslf.model;

/**
 * This class represents a slide's notes in a PowerPoint Document. It
 *  allows access to the text within, and the layout. For now, it only
 *  does the text side of things though
 *
 * @author Nick Burch
 */

public final class Notes extends Sheet
{
    private TextRun[] _runs;

    /**
     * Constructs a Notes Sheet from the given Notes record.
     * Initialises TextRuns, to provide easier access to the text
     *
     * @param notes the Notes record to read from
     */
    public Notes(device.s.docreader.office.fc.hslf.record.Notes notes)
    {
        super(notes, notes.getNotesAtom().getSlideID());

        // Now, build up TextRuns from pairs of TextHeaderAtom and
        //  one of TextBytesAtom or TextCharsAtom, found inside
        //  EscherTextboxWrapper's in the PPDrawing
        _runs = findTextRuns(getPPDrawing());

        // Set the sheet on each TextRun
        for (int i = 0; i < _runs.length; i++)
            _runs[i].setSheet(this);
    }

    // Accesser methods follow

    /**
     * Returns an array of all the TextRuns found
     */
    public TextRun[] getTextRuns()
    {
        return _runs;
    }

    /**
     * Return <code>null</code> - Notes Masters are not yet supported
     */
    public MasterSheet getMasterSheet()
    {
        return null;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        super.dispose();
        if (_runs != null)
        {
            for (TextRun tr : _runs)
            {
                tr.dispose();
            }
            _runs = null;
        }
    }

}
