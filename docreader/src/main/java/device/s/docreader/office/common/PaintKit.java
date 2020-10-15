package device.s.docreader.office.common;

import android.graphics.Paint;
import android.graphics.Typeface;

import device.s.docreader.office.constant.SSConstant;


public class PaintKit
{
    private Paint paint = null;
    private static PaintKit pk = new PaintKit();

    private PaintKit()
    {
        paint = new Paint();
        paint.setTextSize(SSConstant.HEADER_TEXT_FONTSZIE);
        paint.setTypeface(Typeface.SERIF);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }


    public static PaintKit instance()
    {
        return pk;
    }  
    
    public Paint getPaint()
    {
        paint.reset();
        paint.setAntiAlias(true);
        
        return paint;
    }
}
