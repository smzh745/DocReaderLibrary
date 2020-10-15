/*
 * 文件名称:           WPAutoshape.java
 *  
 * 编译器:             android2.2
 * 时间:               上午9:25:32
 */
package device.s.docreader.office.common.shape;

import device.s.docreader.office.java.awt.Rectangle;


public class WPAutoShape extends WPAbstractShape
{

    
    

    public WPAutoShape()
    {
        
    }
    

    public short getType()
    {
        return SHAPE_AUTOSHAPE;
    }
    

    public boolean isWatermarkShape()
    {
        return false;
    }

   public Rectangle getBounds()
   {
       if (groupShape != null)
       {
          return groupShape.getBounds();
       }
       return super.getBounds();
   }
    

    public void addGroupShape(WPGroupShape groupShape)
    {
        this.groupShape = groupShape;
    }
    

    public WPGroupShape getGroupShape()
    {
        return groupShape;
    }

    public void dispose()
    {
        super.dispose();
        if (groupShape != null)
        {
            groupShape.dispose();
            groupShape = null;
        }
    }    
    
    //
    private WPGroupShape groupShape;
}
