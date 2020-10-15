package device.s.docreader.office.common.shape;

import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.pg.animate.IAnimation;


public interface IShape
{


    public void setGroupShapeID(int id);
    

    public int getGroupShapeID();
    

    public void setShapeID(int id);
    

    public int getShapeID();
    

    public short getType();

    public IShape getParent();
    

    public void setParent(IShape shape);
    

    public Rectangle getBounds();

    public void setBounds(Rectangle rect);

    public Object getData();

    public void setData(Object data);

    public boolean getFlipHorizontal();

    public void setFlipHorizontal(boolean flipH);


    public boolean getFlipVertical();

    public void setFlipVertical(boolean flipV);

    public float getRotation();

    public void setRotation(float angle);
    

    public void setHidden(boolean hidden);
    

    public boolean isHidden();
    

    public void setAnimation(IAnimation animation);
    

    public IAnimation getAnimation();

    public int getPlaceHolderID();
    

    public void setPlaceHolderID(int placeHolderID);
    

    public void dispose();
    
}
