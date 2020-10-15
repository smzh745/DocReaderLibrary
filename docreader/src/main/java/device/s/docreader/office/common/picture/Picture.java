package device.s.docreader.office.common.picture;


public class Picture
{
    // Windows Enhanced Metafile (EMF)
    public static final byte EMF = 2;
    // Windows Metafile (WMF)
    public static final byte WMF = 3;
    // Macintosh PICT
    public static final byte PICT = 4;
    // JPEG
    public static final byte JPEG = 5;
    //  PNG
    public static final byte PNG = 6;
    // Windows DIB (BMP)
    public static final byte DIB = 7;
    //  PNG
    public static final byte GIF = 8;
    
    public static final String EMF_TYPE = "emf";
    // Windows Metafile (WMF)
    public static final String WMF_TYPE = "wmf";
    // Macintosh PICT
    public static final String PICT_TYPE = "pict";
    // JPEG
    public static final String JPEG_TYPE = "jpeg";
    //  PNG
    public static final String PNG_TYPE = "png";
    // Windows DIB (BMP)
    public static final String DIB_TYPE = "dib";
    // GIF
    public static final String GIF_TYPE = "gif";

    public String getTempFilePath()
    {
        return tempFilePath;
    }


    public void setTempFilePath(String tempFilePath)
    {
        this.tempFilePath = tempFilePath;
    }


    public byte[] getData()
    {
        return data;
    }


    public void setData(byte[] data)
    {
        this.data = data;
    }


    public byte getPictureType()
    {
        return type;
    }


    public void setPictureType(byte type)
    {
        this.type = type;
    }


    public void setPictureType(String typeName)
    {
        if (typeName.equalsIgnoreCase(EMF_TYPE))
        {
            this.type = EMF;
        }
        else if (typeName.equalsIgnoreCase(WMF_TYPE))
        {
            this.type = WMF;
        }
        else if (typeName.equalsIgnoreCase(PICT_TYPE))
        {
            this.type = PICT;
        }
        else if (typeName.equalsIgnoreCase(JPEG_TYPE))
        {
            this.type = JPEG;
        }
        else if (typeName.equalsIgnoreCase(PNG_TYPE))
        {
            this.type = PNG;
        }
        else if (typeName.equalsIgnoreCase(DIB_TYPE))
        {
            this.type = DIB;
        }
        else if (typeName.equalsIgnoreCase(GIF_TYPE))
        {
            this.type = GIF;
        }
    }

    public void dispose()
    {
        tempFilePath = null;
    }
    

    public short getZoomX()
    {
        return zoomX;
    }


    public void setZoomX(short zoomX)
    {
        this.zoomX = zoomX;
    }


    public short getZoomY()
    {
        return zoomY;
    }


    public void setZoomY(short zoomY)
    {
        this.zoomY = zoomY;
    }

    //
    private byte type;
    //
    private byte[] data;
    // picture horizontal zoom
    private short zoomX;
    // picture vertical zoom 
    private short zoomY;
    // temp file path;
    private String tempFilePath;
}
