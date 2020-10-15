package device.s.docreader.office.fc.hslf.record;

/**
 * The superclass of all sheet container records - Slide, Notes, MainMaster, etc.
 */
public abstract class SheetContainer extends PositionDependentRecordContainer
{

    /**
     * Returns the PPDrawing of this sheet, which has all the
     *  interesting data in it
     */
    public abstract PPDrawing getPPDrawing();

    public abstract ColorSchemeAtom getColorScheme();
    
    

}
