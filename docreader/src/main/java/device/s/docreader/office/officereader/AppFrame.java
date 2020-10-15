/*
 * 文件名称:           Frame.java
 * 
 * 编译器:             android2.2
 * 时间:               下午1:34:44
 */
package device.s.docreader.office.officereader;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;


public class AppFrame extends LinearLayout
{

    public AppFrame(Context context)
    {
        super(context);
        setOrientation(VERTICAL);
        setBackgroundColor(Color.WHITE);

    }
    
    /**
     * 
     */
    public void dispose()
    {
        //removeAllViews();
    }
}
