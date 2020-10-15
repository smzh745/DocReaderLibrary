
package device.s.docreader.office.common.shape;

import device.s.docreader.office.common.picture.Picture;
import device.s.docreader.office.common.pictureefftect.PictureEffectInfo;
import device.s.docreader.office.system.IControl;


public class PictureShape extends AbstractShape
{    

    public short getType()
    {
        return SHAPE_PICTURE;
    }
    

    public void setPictureIndex(int pictureIndex)
    {
        this.pictureIndex = pictureIndex;
    }


    public int getPictureIndex()
    {
        return pictureIndex;
    }

    public Picture getPicture(IControl control)
    {
        if (control == null)
        {
            return null;
        }
        return control.getSysKit().getPictureManage().getPicture(pictureIndex);
    }
    
    public static Picture getPicture(IControl control, int pictureIndex)
    {
    	if (control == null)
        {
            return null;
        }
        return control.getSysKit().getPictureManage().getPicture(pictureIndex);
    }
    

    public void setZoomX(short zoomX)
    {
        this.zoomX = zoomX;
    }
    

    public void setZoomY(short zoomY)
    {
        this.zoomY = zoomY;
    }
    

    

    public void setPictureEffectInfor(PictureEffectInfo effectInfor)
    {
        this.effectInfor = effectInfor;
    }

    public PictureEffectInfo getPictureEffectInfor()
    {
        return effectInfor;
    }
    
    public void dispose()
    {
        super.dispose();
    }
    
    private int pictureIndex;
    // Horizontal scaling factor supplied by user expressed in .001% units
    private short zoomX;
    // Vertical scaling factor supplied by user expressed in .001% units
    private short zoomY;

    //picture effect property
    private PictureEffectInfo effectInfor;
}
