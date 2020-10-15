package device.s.docreader.office.fc.hssf.usermodel;
import device.s.docreader.office.fc.ss.usermodel.AutoFilter;

/**
 * Represents autofiltering for the specified worksheet.
 *
 * @author Yegor Kozlov
 */
public final class HSSFAutoFilter implements AutoFilter {
    private HSSFSheet _sheet;

    HSSFAutoFilter(HSSFSheet sheet){
        _sheet = sheet;
    }
}
