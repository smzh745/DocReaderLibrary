package device.s.docreader.office.fc.hwpf.usermodel;

import java.util.List;
import java.util.Map;

/**
 * User-friendly interface to access document bookmarks
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */
public interface Bookmarks
{
    /**
     * @param index
     *            bookmark document index
     * @return {@link POIBookmark} with specified index
     * @throws IndexOutOfBoundsException
     *             if bookmark with specified index not present in document
     */
    POIBookmark getBookmark(int index) throws IndexOutOfBoundsException;

    /**
     * @return count of {@link POIBookmark}s in document
     */
    int getBookmarksCount();

    /**
     * @return {@link Map} of bookmarks started in specified range, where key is
     *         start position and value is sorted {@link List} of
     *         {@link POIBookmark}
     */
    Map<Integer, List<POIBookmark>> getBookmarksStartedBetween(
            int startInclusive, int endExclusive);
}
