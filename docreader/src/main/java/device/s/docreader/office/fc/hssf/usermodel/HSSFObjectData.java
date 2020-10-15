package device.s.docreader.office.fc.hssf.usermodel;

import device.s.docreader.office.fc.hssf.record.EmbeddedObjectRefSubRecord;
import device.s.docreader.office.fc.hssf.record.ObjRecord;
import device.s.docreader.office.fc.hssf.record.SubRecord;
import device.s.docreader.office.fc.poifs.filesystem.DirectoryEntry;
import device.s.docreader.office.fc.poifs.filesystem.Entry;
import device.s.docreader.office.fc.util.HexDump;

import java.io.IOException;
import java.util.Iterator;


/**
 * Represents binary object (i.e. OLE) data stored in the file.  Eg. A GIF, JPEG etc...
 *
 * @author Daniel Noll
 */
public final class HSSFObjectData {
    /**
     * Underlying object record ultimately containing a reference to the object.
     */
    private final ObjRecord _record;

    /**
     * Reference to the filesystem root, required for retrieving the object data.
     */
    private final DirectoryEntry _root;

    /**
     * Constructs object data by wrapping a lower level object record.
     *
     * @param record the low-level object record.
     * @param root the root of the filesystem, required for retrieving the object data.
     */
    public HSSFObjectData(ObjRecord record, DirectoryEntry root)
    {
        _record = record;
        _root = root;
    }

    /**
     * Returns the OLE2 Class Name of the object
     */
    public String getOLE2ClassName() {
        return findObjectRecord().getOLEClassName();
    }

    /**
     * Gets the object data. Only call for ones that have
     *  data though. See {@link #hasDirectoryEntry()}
     *
     * @return the object data as an OLE2 directory.
     * @throws IOException if there was an error reading the data.
     */
    public DirectoryEntry getDirectory() throws IOException {
        EmbeddedObjectRefSubRecord subRecord = findObjectRecord();

        int streamId = subRecord.getStreamId().intValue();
        String streamName = "MBD" + HexDump.toHex(streamId);

        Entry entry = _root.getEntry(streamName);
        if (entry instanceof DirectoryEntry) {
            return (DirectoryEntry) entry;
        }
        throw new IOException("Stream " + streamName + " was not an OLE2 directory");
    }

    /**
     * Returns the data portion, for an ObjectData
     *  that doesn't have an associated POIFS Directory
     *  Entry
     */
    public byte[] getObjectData() {
        return findObjectRecord().getObjectData();
    }

    /**
     * Does this ObjectData have an associated POIFS
     *  Directory Entry?
     * (Not all do, those that don't have a data portion)
     */
    public boolean hasDirectoryEntry() {
        EmbeddedObjectRefSubRecord subRecord = findObjectRecord();

        // 'stream id' field tells you
        Integer streamId = subRecord.getStreamId();
        return streamId != null && streamId.intValue() != 0;
    }

    /**
     * Finds the EmbeddedObjectRefSubRecord, or throws an
     *  Exception if there wasn't one
     */
    protected EmbeddedObjectRefSubRecord findObjectRecord() {
        Iterator<SubRecord> subRecordIter = _record.getSubRecords().iterator();

        while (subRecordIter.hasNext()) {
            Object subRecord = subRecordIter.next();
            if (subRecord instanceof EmbeddedObjectRefSubRecord) {
                return (EmbeddedObjectRefSubRecord)subRecord;
            }
        }

        throw new IllegalStateException("Object data does not contain a reference to an embedded object OLE2 directory");
    }
}
