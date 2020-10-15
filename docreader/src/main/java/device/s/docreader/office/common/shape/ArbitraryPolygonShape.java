package device.s.docreader.office.common.shape;

import device.s.docreader.office.common.autoshape.ExtendPath;

import java.util.ArrayList;
import java.util.List;


public class ArbitraryPolygonShape extends LineShape
{   
    public ArbitraryPolygonShape()
    {
        paths = new ArrayList<ExtendPath>();
    }
    
    public void appendPath(ExtendPath path)
    {
        this.paths.add(path);
    }
    
    public List<ExtendPath> getPaths()
    {
        return paths;
    }
    
    //
    private List<ExtendPath> paths;
}
