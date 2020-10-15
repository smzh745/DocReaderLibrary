package device.s.docreader.office.fc.hwpf.usermodel;

import java.util.Collection;

/**
 * User-friendly interface to access document part's office drawings
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */
public interface OfficeDrawings
{
    OfficeDrawing getOfficeDrawingAt(int characterPosition);

    Collection<OfficeDrawing> getOfficeDrawings();
}
