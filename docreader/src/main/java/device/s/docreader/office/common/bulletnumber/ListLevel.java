package device.s.docreader.office.common.bulletnumber;

public class ListLevel
{

    public int getStartAt()
    {
        return startAt;
    }

    public void setStartAt(int startAt)
    {
        this.startAt = startAt;
    }

    public int getNumberFormat()
    {
        return numberFormat;
    }


    public void setNumberFormat(int numberFormat)
    {
        this.numberFormat = numberFormat;
    }


    public char[] getNumberText()
    {
        return numberText;
    }


    public void setNumberText(char[] numberText)
    {
        this.numberText = numberText;
    }


    public byte getAlign()
    {
        return align;
    }


    public void setAlign(byte align)
    {
        this.align = align;
    }


    public byte getFollowChar()
    {
        return followChar;
    }


    public void setFollowChar(byte followChar)
    {
        this.followChar = followChar;
    }


    public int getTextIndent()
    {
        return textIndent;
    }


    public void setTextIndent(int textIndent)
    {
        this.textIndent = textIndent;
    }


    public int getSpecialIndent()
    {
        return specialIndent;
    }

    public void setSpecialIndent(int specialIndent)
    {
        this.specialIndent = specialIndent;
    }


    public int getParaCount()
    {
        return paraCount;
    }


    public void setParaCount(int paraCount)
    {
        this.paraCount = paraCount;
    }


    public int getNormalParaCount()
    {
        return normalParaCount;
    }

    public void setNormalParaCount(int normalParaCount)
    {
        this.normalParaCount = normalParaCount;
    }

    public void dispose()
    {
        numberText = null;
    }
    


    private int startAt;

    private int numberFormat;
    // number text
    private char[] numberText;
    // horizontal alignment
    private byte align;
    // The type of character following the number text for the paragraph: 0 == tab, 1 == space, 2 == nothing
    private byte followChar;
    //
    private int textIndent;
    //
    private int specialIndent;
    // previous paragraph count of same level
    private int paraCount;
    //
    private int normalParaCount;
    
}
