package device.s.docreader.office.common.bookmark;


public class Bookmark
{

    public Bookmark(String name, long start, long end)
    {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public long getStart()
    {
        return start;
    }

    public void setStart(long start)
    {
        this.start = start;
    }


    public long getEnd()
    {
        return end;
    }

    public void setEnd(long end)
    {
        this.end = end;
    }

    //
    private long start;
    //
    private long end;
    //
    private String name;

}
