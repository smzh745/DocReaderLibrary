package device.s.docreader.office.common.shape;

import java.util.ArrayList;
import java.util.List;


public class SmartArt extends AbstractShape
{



    public SmartArt()
    {
        shapes = new ArrayList<IShape>();
    }
    

    public short getType()
    {
        return SHAPE_SMARTART;
    }

    public void appendShapes(IShape shape)
    {
        this.shapes.add(shape);
    }
    

    public IShape[] getShapes()
    {
        return shapes.toArray(new IShape[shapes.size()]);
    }
    
    private int offX, offY;
    // shapes of this slide
    private List<IShape> shapes;
}
