package device.s.docreader.office.fc.hslf.model.textproperties;

/**
 * Definition for the alignment text property.
 */
public class AlignmentTextProp extends TextProp
{
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int JUSTIFY = 3;
    public static final int THAIDISTRIBUTED = 5;
    public static final int JUSTIFYLOW = 6;

    public AlignmentTextProp()
    {
        super(2, 0x800, "alignment");
    }
}
