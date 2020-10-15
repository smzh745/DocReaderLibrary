package device.s.docreader.office.fc.hwpf.usermodel;

/**
 * User friendly interface to access information about document bookmarks
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {doc} com)
 */
public interface POIBookmark
{
    int getEnd();

    String getName();

    int getStart();

    void setName(String name);
}
