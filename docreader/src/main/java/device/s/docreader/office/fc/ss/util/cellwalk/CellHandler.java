package device.s.docreader.office.fc.ss.util.cellwalk;

import device.s.docreader.office.fc.ss.usermodel.ICell;

/**
 * Represents callback for CellWalk traverse method.  
 * @author Roman Kashitsyn
 */
public interface CellHandler {

    /**
     * @param cell current cell
     * @param ctx information about invokation context
     */
    void onCell(ICell cell, CellWalkContext ctx);

}
