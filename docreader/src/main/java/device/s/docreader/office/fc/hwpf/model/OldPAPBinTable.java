package device.s.docreader.office.fc.hwpf.model;

import device.s.docreader.office.fc.poifs.common.POIFSConstants;
import device.s.docreader.office.fc.util.Internal;
import device.s.docreader.office.fc.util.LittleEndian;

import java.util.Collections;


/**
 * This class holds all of the paragraph formatting 
 *  properties from Old (Word 6 / Word 95) documents.
 * Unlike with Word 97+, it all gets held in the
 *  same stream.
 * In common with the rest of the old support, it 
 *  is read only
 */
@ Internal
public final class OldPAPBinTable extends PAPBinTable
{

    public OldPAPBinTable(byte[] documentStream, int offset, int size, int fcMin, TextPieceTable tpt)
    {
        PlexOfCps binTable = new PlexOfCps(documentStream, offset, size, 2);

        int length = binTable.length();
        for (int x = 0; x < length; x++)
        {
            GenericPropertyNode node = binTable.getProperty(x);

            int pageNum = LittleEndian.getShort(node.getBytes());
            int pageOffset = POIFSConstants.SMALLER_BIG_BLOCK_SIZE * pageNum;

            PAPFormattedDiskPage pfkp = new PAPFormattedDiskPage(documentStream, documentStream,
                pageOffset, tpt);

            int fkpSize = pfkp.size();

            for (int y = 0; y < fkpSize; y++)
            {
                PAPX papx = pfkp.getPAPX(y);
                if (papx != null)
                {
                    _paragraphs.add(papx);
                }
            }
        }
        Collections.sort(_paragraphs, PropertyNode.StartComparator.instance);
    }
}
