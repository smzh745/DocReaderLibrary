package device.s.docreader.office.fc.hssf.eventusermodel.dummyrecord;


/**
 * A dummy record for when we're missing a row, but still
 *  want to trigger something
 */
public final class MissingRowDummyRecord extends DummyRecordBase {
	private int rowNumber;
	
	public MissingRowDummyRecord(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getRowNumber() {
		return rowNumber;
	}
}
