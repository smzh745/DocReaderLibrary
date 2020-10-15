package device.s.docreader.office.fc.hssf.record;

import java.util.Iterator;

/**
 * HorizontalPageBreak (0x001B) record that stores page breaks at rows <p/>
 * 
 * @see PageBreakRecord
 * @author Danny Mui (dmui at apache dot org)
 */
public final class HorizontalPageBreakRecord extends PageBreakRecord {

	public static final short sid = 0x001B;

	/**
	 * Creates an empty horizontal page break record
	 */
	public HorizontalPageBreakRecord() {
		//
	}

	/**
	 * @param in
	 *            the RecordInputstream to read the record from
	 */
	public HorizontalPageBreakRecord(RecordInputStream in) {
		super(in);
	}

	public short getSid() {
		return sid;
	}

	public Object clone() {
		PageBreakRecord result = new HorizontalPageBreakRecord();
		Iterator iterator = getBreaksIterator();
		while (iterator.hasNext()) {
			Break original = (Break) iterator.next();
			result.addBreak(original.main, original.subFrom, original.subTo);
		}
		return result;
	}
}
