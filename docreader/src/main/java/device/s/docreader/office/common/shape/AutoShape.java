package device.s.docreader.office.common.shape;



public class AutoShape extends AbstractShape
{

    public AutoShape()
    {
        
    }
    


    public AutoShape(int type)
    {
        this.type = type;
    }
    

    public short getType()
    {
        return SHAPE_AUTOSHAPE;
    }
    

    public int getShapeType()
    {
        return type;
    }

    public void setShapeType(int type)
    {
        this.type = type;
    }
    
    
    

    public void setAdjustData(Float[] values)
    {
        this.values = values;
    }

    public Float[] getAdjustData()
    {
        return values;
    }
    

    public void setAuotShape07(boolean shape07)
    {
        this.shape07 = shape07;
    }
    

    public boolean isAutoShape07()
    {
        return shape07;
    }
    

    public void dispose()
    {
        super.dispose();        
    }
    
    //
    private int type;
    
    // adjust values by clockwise(percent)
    private Float[] values;
    
    // autoShape is 07 or 03
    private boolean shape07 = true;
}
