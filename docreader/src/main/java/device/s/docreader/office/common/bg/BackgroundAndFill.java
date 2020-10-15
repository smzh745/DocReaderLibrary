package device.s.docreader.office.common.bg;

import device.s.docreader.office.common.picture.Picture;
import device.s.docreader.office.common.pictureefftect.PictureStretchInfo;
import device.s.docreader.office.common.shape.AbstractShape;
import device.s.docreader.office.system.IControl;


public class BackgroundAndFill
{
	// no Fill
    public static final byte FILL_NO = -1;
    // Fill with a solid color
    public static final byte FILL_SOLID = 0;
    // Fill with a pattern (bitmap)
    public static final byte FILL_PATTERN = 1;
    // shade to title
    public static final byte FILL_SHADE_TILE = 2;
    // Center a picture in the shape
    public static final byte FILL_PICTURE = 3;
    // Similar to FILL_SHADE, but the fill angle
    // is additionally scaled by the aspect ratio of
    // the shape. If shape is square, it is the same as FILL_SHADE
    public static final byte FILL_SHADE_RECT = 5;
    // Shade from bounding rectangle to end point
    public static final byte FILL_SHADE_RADIAL = 4;
    // Shade from shape outline to end point
    public static final byte FILL_SHADE_SHAPE = 6;    
    // Shade from start to end points
    public static final byte FILL_SHADE_LINEAR = 7;
    // A texture (pattern with its own color map)
    public static final byte FILL_TEXTURE = 8;
    // Use the background fill color/pattern
    public static final byte FILL_BACKGROUND = 9;
    
    /**
     * 
     *
     */
    public short getType()
    {
        return AbstractShape.SHAPE_BG_FILL;
    }
    
    public boolean isSlideBackgroundFill()
    {
		return isSlideBackgroundFill;
	}

	public void setSlideBackgroundFill(boolean isSlideBackgroundFill)
	{
		this.isSlideBackgroundFill = isSlideBackgroundFill;
	}

    public byte getFillType()
    {
        return fillType;
    }

    public void setFillType(byte fillType)
    {
        this.fillType = fillType;
    }


    public int getForegroundColor()
    {
        return fgColor;
    }

    public void setForegroundColor(int fgColor)
    {
        this.fgColor = fgColor;
    }


    public int getBackgoundColor()
    {
        return bgColor;
    }


    public void setBackgoundColor(int bgColor)
    {
        this.bgColor = bgColor;
    }


    public int getPictureIndex()
    {
        return pictureIndex;
    }


    public void setPictureIndex(int pictureIndex)
    {
        this.pictureIndex = pictureIndex;
    }

    public Picture getPicture(IControl control)
    {
        return control.getSysKit().getPictureManage().getPicture(pictureIndex);
    }
    
    public AShader getShader() 
    {
		return shader;
	}

	public void setShader(AShader shader) 
	{
		this.shader = shader;
	}
	
	public PictureStretchInfo getStretch() 
	{
		return stretch;
	}

	public void setStretch(PictureStretchInfo stretch) 
	{
		this.stretch = stretch;
	}
	

    public void dispose()
    {
    	stretch =  null;
    	if(shader != null)
    	{
    		shader.dispose();
    		shader = null;
    	}
    }
    
    //is slide background fill
    private boolean isSlideBackgroundFill;

	private PictureStretchInfo stretch;
	//
    private byte fillType;
    // BackgroundColor;
    private int bgColor;
    // filled by color
    private int fgColor;
    //filled by picture
    private int pictureIndex;
    //filled by gradient color, tile
    private AShader shader;
}
