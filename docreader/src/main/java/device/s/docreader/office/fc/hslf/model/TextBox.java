package device.s.docreader.office.fc.hslf.model;

import device.s.docreader.office.fc.ddf.EscherContainerRecord;
import device.s.docreader.office.fc.ddf.EscherProperties;


/**
 * Represents a TextFrame shape in PowerPoint.
 * <p>
 * Contains the text in a text frame as well as the properties and methods
 * that control alignment and anchoring of the text.
 * </p>
 *
 * @author Yegor Kozlov
 */
public class TextBox extends TextShape
{
    /**
     * Create a TextBox object and initialize it from the supplied Record container.
     *
     * @param escherRecord       <code>EscherSpContainer</code> container which holds information about this shape
     * @param parent    the parent of the shape
     */
    protected TextBox(EscherContainerRecord escherRecord, Shape parent)
    {
        super(escherRecord, parent);
    }

    /**
     * Create a new TextBox. This constructor is used when a new shape is created.
     *
     * @param parent    the parent of this Shape. For example, if this text box is a cell
     * in a table then the parent is Table.
     */
    public TextBox(Shape parent)
    {
        super(parent);
    }

    /**
     * Create a new TextBox. This constructor is used when a new shape is created.
     *
     */
    public TextBox()
    {
        this(null);
    }

    /**
     * Create a new TextBox and initialize its internal structures
     *
     * @return the created <code>EscherContainerRecord</code> which holds shape data
     */
    protected EscherContainerRecord createSpContainer(boolean isChild)
    {
        _escherContainer = super.createSpContainer(isChild);

        setShapeType(ShapeTypes.TextBox);

        //set default properties for a TextBox
        setEscherProperty(EscherProperties.FILL__FILLCOLOR, 0x8000004);
        setEscherProperty(EscherProperties.FILL__FILLBACKCOLOR, 0x8000000);
        setEscherProperty(EscherProperties.FILL__NOFILLHITTEST, 0x100000);
        setEscherProperty(EscherProperties.LINESTYLE__COLOR, 0x8000001);
        setEscherProperty(EscherProperties.LINESTYLE__NOLINEDRAWDASH, 0x80000);
        setEscherProperty(EscherProperties.SHADOWSTYLE__COLOR, 0x8000002);

        _txtrun = createTextRun();

        return _escherContainer;
    }

    protected void setDefaultTextProperties(TextRun _txtrun)
    {
        setVerticalAlignment(TextBox.AnchorTop);
        setEscherProperty(EscherProperties.TEXT__SIZE_TEXT_TO_FIT_SHAPE, 0x20002);
    }

}
