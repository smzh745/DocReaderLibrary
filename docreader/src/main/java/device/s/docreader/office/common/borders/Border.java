
package device.s.docreader.office.common.borders;


public class Border
{
    public int getColor()
    {
        return color;
    }


    public void setColor(int color)
    {
        this.color = color;
    }


    public int getLineWidth()
    {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth)
    {
        this.lineWidth = lineWidth;
    }


    public byte getLineType()
    {
        return lineType;
    }


    public void setLineType(byte lineType)
    {
        this.lineType = lineType;
    }


    public short getSpace()
    {
        return space;
    }


    public void setSpace(short space)
    {
        this.space = space;
    }

    //
    private int color;
    //
    private int lineWidth;
    //
    private byte lineType;
    //
    private short space;

}
