package device.s.docreader.office.fc.hslf.record;


/**
 * A container record that specifies information about an ActiveX control. It contains:
 * <p>
 * 1. ExControlAtom (4091)
 * 2. ExOleObjAtom (4035)
 * 3. CString (4026), Instance MenuName (1) used for menus and the Links dialog box.
 * 4. CString (4026), Instance ProgID (2) that stores the OLE Programmatic Identifier.
 *  A ProgID is a string that uniquely identifies a given object.
 * 5. CString (4026), Instance ClipboardName (3) that appears in the paste special dialog.
 * 6. MetaFile( 4033), optional
 * </p>
 *
 *
 * @author Yegor kozlov
 */
public final class ExControl extends ExEmbed {

    /**
     * Set things up, and find our more interesting children
     *
     * @param source the source data as a byte array.
     * @param start the start offset into the byte array.
     * @param len the length of the slice in the byte array.
     */
    protected ExControl(byte[] source, int start, int len) {
        super(source, start, len);
    }

    /**
     * Create a new ExEmbed, with blank fields
     */
    public ExControl() {
        super();

        _children[0] = embedAtom = new ExControlAtom();
    }

    /**
     * Gets the {@link ExControlAtom}.
     *
     * @return the {@link ExControlAtom}.
     */
    public ExControlAtom getExControlAtom()
    {
        return (ExControlAtom)_children[0];
    }

    /**
     * Returns the type (held as a little endian in bytes 3 and 4)
     * that this class handles.
     *
     * @return the record type.
     */
    public long getRecordType() {
        return RecordTypes.ExControl.typeID;
    }
}
