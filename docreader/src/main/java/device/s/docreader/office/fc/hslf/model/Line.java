package device.s.docreader.office.fc.hslf.model;

import device.s.docreader.office.fc.ShapeKit;
import device.s.docreader.office.fc.ddf.EscherContainerRecord;
import device.s.docreader.office.fc.ddf.EscherOptRecord;
import device.s.docreader.office.fc.ddf.EscherProperties;
import device.s.docreader.office.fc.ddf.EscherSpRecord;
import device.s.docreader.office.java.awt.geom.Line2D;
import device.s.docreader.office.java.awt.geom.Rectangle2D;


/**
 * Represents a line in a PowerPoint drawing
 *
 *  @author Yegor Kozlov
 */
public final class Line extends SimpleShape
{
    protected Line(EscherContainerRecord escherRecord, Shape parent)
    {
        super(escherRecord, parent);
    }
    
    public Float[] getAdjustmentValue()
    {
        return ShapeKit.getAdjustmentValue(getSpContainer());
    }

    
    
    
    /******************************************/
    public Line(Shape parent)
    {
        super(null, parent);
        _escherContainer = createSpContainer(parent instanceof ShapeGroup);
    }

    public Line()
    {
        this(null);
    }

    protected EscherContainerRecord createSpContainer(boolean isChild)
    {
        _escherContainer = super.createSpContainer(isChild);

        EscherSpRecord spRecord = _escherContainer.getChildById(EscherSpRecord.RECORD_ID);
        short type = (ShapeTypes.Line << 4) | 0x2;
        spRecord.setOptions(type);

        //set default properties for a line
        EscherOptRecord opt = (EscherOptRecord)ShapeKit.getEscherChild(_escherContainer,
            EscherOptRecord.RECORD_ID);

        //default line properties
        setEscherProperty(opt, EscherProperties.GEOMETRY__SHAPEPATH, 4);
        setEscherProperty(opt, EscherProperties.GEOMETRY__FILLOK, 0x10000);
        setEscherProperty(opt, EscherProperties.FILL__NOFILLHITTEST, 0x100000);
        setEscherProperty(opt, EscherProperties.LINESTYLE__COLOR, 0x8000001);
        setEscherProperty(opt, EscherProperties.LINESTYLE__NOLINEDRAWDASH, 0xA0008);
        setEscherProperty(opt, EscherProperties.SHADOWSTYLE__COLOR, 0x8000002);

        return _escherContainer;
    }

    public device.s.docreader.office.java.awt.Shape getOutline()
    {
        Rectangle2D anchor = getLogicalAnchor2D();
        return new Line2D.Double(anchor.getX(), anchor.getY(), anchor.getX() + anchor.getWidth(),
            anchor.getY() + anchor.getHeight());
    }
    
    public void dispose()
    {
        super.dispose();
    }
}
