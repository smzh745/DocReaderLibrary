/**
 * Word document notes types (and their FIB field indices)
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {doc} com)
 */
package device.s.docreader.office.fc.hwpf.model;

import device.s.docreader.office.fc.util.Internal;

@Internal
public enum NoteType {
    /** Ending note */
    ENDNOTE( FIBFieldHandler.PLCFENDREF, FIBFieldHandler.PLCFENDTXT ),

    /** Footnote */
    FOOTNOTE( FIBFieldHandler.PLCFFNDREF, FIBFieldHandler.PLCFFNDTXT );

    private final int fibDescriptorsFieldIndex;
    private final int fibTextPositionsFieldIndex;

    private NoteType( int fibDescriptorsFieldIndex,
            int fibTextPositionsFieldIndex )
    {
        this.fibDescriptorsFieldIndex = fibDescriptorsFieldIndex;
        this.fibTextPositionsFieldIndex = fibTextPositionsFieldIndex;
    }

    public int getFibDescriptorsFieldIndex()
    {
        return fibDescriptorsFieldIndex;
    }

    public int getFibTextPositionsFieldIndex()
    {
        return fibTextPositionsFieldIndex;
    }
}
