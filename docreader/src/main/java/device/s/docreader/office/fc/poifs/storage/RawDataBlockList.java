
package device.s.docreader.office.fc.poifs.storage;

import device.s.docreader.office.fc.poifs.common.POIFSBigBlockSize;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A list of RawDataBlocks instances, and methods to manage the list
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class RawDataBlockList
    extends BlockListImpl
{

    /**
     * Constructor RawDataBlockList
     *
     * @param stream the InputStream from which the data will be read
     * @param bigBlockSize The big block size, either 512 bytes or 4096 bytes
     *
     * @exception IOException on I/O errors, and if an incomplete
     *            block is read
     */

    public RawDataBlockList(final InputStream stream, POIFSBigBlockSize bigBlockSize)
        throws IOException
    {
        List<RawDataBlock> blocks = new ArrayList<RawDataBlock>();

        while (true)
        {
            RawDataBlock block = new RawDataBlock(stream, bigBlockSize.getBigBlockSize());
            
            // If there was data, add the block to the list
            if(block.hasData()) {
            	blocks.add(block);
            }

            // If the stream is now at the End Of File, we're done
            if (block.eof()) {
                break;
            }
        }
        setBlocks( blocks.toArray(new RawDataBlock[ blocks.size() ]) );
    }
}   // end public class RawDataBlockList

