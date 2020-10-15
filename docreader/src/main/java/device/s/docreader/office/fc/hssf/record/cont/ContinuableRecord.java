package device.s.docreader.office.fc.hssf.record.cont;


import device.s.docreader.office.fc.hssf.record.ContinueRecord;
import device.s.docreader.office.fc.hssf.record.Record;
import device.s.docreader.office.fc.util.LittleEndianByteArrayOutputStream;
import device.s.docreader.office.fc.util.LittleEndianOutput;


/**
 * Common superclass of all records that can produce {@link ContinueRecord}s while being serialized.
 *
 * @author Josh Micich
 */
public abstract class ContinuableRecord extends Record {

	protected ContinuableRecord() {
		// no fields to initialise
	}
	/**
	 * Serializes this record's content to the supplied data output.<br/>
	 * The standard BIFF header (ushort sid, ushort size) has been handled by the superclass, so
	 * only BIFF data should be written by this method.  Simple data types can be written with the
	 * standard {@link LittleEndianOutput} methods.  Methods from {@link ContinuableRecordOutput}
	 * can be used to serialize strings (with {@link ContinueRecord}s being written as required).
	 * If necessary, implementors can explicitly start {@link ContinueRecord}s (regardless of the
	 * amount of remaining space).
	 *
	 * @param out a data output stream
	 */
	protected abstract void serialize(ContinuableRecordOutput out);


	/**
	 * @return the total length of the encoded record(s)
	 * (Note - if any {@link ContinueRecord} is required, this result includes the
	 * size of those too)
	 */
	public final int getRecordSize() {
		ContinuableRecordOutput out = ContinuableRecordOutput.createForCountingOnly();
		serialize(out);
		out.terminate();
		return out.getTotalSize();
	}

	public final int serialize(int offset, byte[] data) {

		LittleEndianOutput leo = new LittleEndianByteArrayOutputStream(data, offset);
		ContinuableRecordOutput out = new ContinuableRecordOutput(leo, getSid());
		serialize(out);
		out.terminate();
		return out.getTotalSize();
	}
}
