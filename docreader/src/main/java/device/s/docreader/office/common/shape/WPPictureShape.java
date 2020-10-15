package device.s.docreader.office.common.shape;

public class WPPictureShape extends WPAutoShape
{

    public short getType()
    {
        return SHAPE_PICTURE;
    }
    
    public void setPictureShape(PictureShape pictureShape)
    {
        this.pictureShape = pictureShape;
        
        if(rect == null)
        {
        	rect = pictureShape.getBounds();
        }
    }
    
    public PictureShape getPictureShape()
    {
        return pictureShape;
    }
    


    public boolean isWatermarkShape()
    {
        return false;
    }
    
    public void dispose()
    {
        if(pictureShape != null)
        {
            pictureShape.dispose();
            pictureShape = null;
        }
    }
    
    private PictureShape pictureShape;
}
