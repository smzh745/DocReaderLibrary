package device.s.docreader.office.common.hyperlink;


public class Hyperlink
{
    // Link to a existing file or web page
    public static final int LINK_URL = 1;
    // Link to a place in this document
    public static final int LINK_DOCUMENT = 2;
    // Link to an E-mail address
    public static final int LINK_EMAIL = 3;
    // Link to a file
    public static final int LINK_FILE = 4;
    // Link to a book mark
    public static final int LINK_BOOKMARK = 5;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    

    public int getLinkType()
    {
        return type;
    }

    public void setLinkType(int type)
    {
        this.type = type;
    }


    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    

    public void dispose()
    {
        address = null;
        title = null;
    }

    // ID
    private int id = -1;
    // hyperlink type
    private int type;
    // hyperlink address;
    private String address;
    // hyperlink title
    private String title;
}
