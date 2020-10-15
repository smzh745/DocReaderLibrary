
package device.s.docreader.office.common.pictureefftect;



public class PictureEffectInfo
{
    public PictureEffectInfo()
    {
        
    }
     public void setPictureCroppedInfor(PictureCroppedInfo rect)
     {
         this.croppedRect = rect;
     }
     
     public PictureCroppedInfo getPictureCroppedInfor()
     {
         return croppedRect;
     }
     
     public PictureStretchInfo getPictureStretchInfo() 
     {
 		return stretch;
 	}
 	public void setPictureStretchInfo(PictureStretchInfo stretch) 
 	{
 		this.stretch = stretch;
 	}
 	
     public void setGrayScale(boolean grayscl)
     {
         this.grayscl = grayscl;
     }
     
     public Boolean isGrayScale()
     {
         return grayscl;
     }
     
     public void setBlackWhiteThreshold(float threshold)
     {
         this.threshold = threshold;
     }
     
     public Float getBlackWhiteThreshold()
     {
         return threshold;
     }
     
     public void setSaturation(float sat)
     {
         this.sat = sat;
     }
     
     public Float getSaturation()
     {
         return sat;
     }
     
     public void setBrightness(float bright)
     {
         this.bright = bright;
     }
     
     public Float getBrightness()
     {
         return bright;
     }
     
     public void setContrast(float contrast)
     {
         this.contrast = contrast;
     }
     
     public Float getContrast()
     {
         return contrast;
     }
     
     public void setTransparentColor(int color)
     {
         this.transparentColor = color;
     }
     
     public Integer getTransparentColor()
     {
         return transparentColor;
     }
     
     public Integer getAlpha()
     {
 		return alpha;
 	}
 	public void setAlpha(Integer alpha)
 	{
 		this.alpha = alpha;
 	}
 	
     public void dispose()
     {
         croppedRect = null;
         grayscl = null;
         threshold = null;
         sat = null;
         bright = null;
         contrast = null;
         alpha = null;
     }
     
     //the interesting rect after image cropped
     private PictureCroppedInfo croppedRect;
     //stretch
     private PictureStretchInfo stretch;

	//whether apply gray scale effct
     private Boolean grayscl;
     //black&white threshold
     private Float threshold;
     //saturation([0,>=2])
     private Float sat;
     //[-255,255]
     private Float bright;
     //[0, 10]
     private Float contrast;
     
     private Integer transparentColor;
     
     private Integer alpha;
}
