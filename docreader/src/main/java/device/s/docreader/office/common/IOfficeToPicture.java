package device.s.docreader.office.common;

import android.graphics.Bitmap;


public interface IOfficeToPicture
{
    // 实现IOfficeToPicture接口的类路径
    public static final String INSTANCE_CLASS_PATH = "device.s.docreader.office.officereader.OfficeToPicture";
    // 视图发生变化时生成picture，例如滚动、fling进行中
    public static final byte VIEW_CHANGING = 0;
    // 视图发生变化结束后，例如滚动、fling、横竖屏切换结束后
    public static final byte VIEW_CHANGE_END = VIEW_CHANGING + 1; 
    


    public void setModeType(byte modeType);
    

    public byte getModeType();


    public Bitmap getBitmap(int visibleWidth, int visibleHeight);


    public void callBack(Bitmap bitmap);


    public boolean isZoom();


    public void dispose();
}
