package device.s.docreader.office.common.autoshape.pathbuilder.arrow;

import android.graphics.Rect;

import device.s.docreader.office.common.shape.AutoShape;

public class ArrowPathBuilder
{    

    public static Object getArrowPath(AutoShape shape, Rect rect)
    {
        if(shape.isAutoShape07())
        {
            return LaterArrowPathBuilder.getArrowPath(shape, rect);
        }
        else
        {
            return EarlyArrowPathBuilder.getArrowPath(shape, rect);
        }
    }
}
