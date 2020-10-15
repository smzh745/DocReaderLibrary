package device.s.docreader.office.fc.hssf.eventusermodel.dummyrecord;


/**
 * A dummy record for when we're missing a cell in a row,
 *  but still want to trigger something
 */
public final class MissingCellDummyRecord extends DummyRecordBase {
	private int row;
	private int column;
	
	public MissingCellDummyRecord(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public int getRow() { return row; }
	public int getColumn() { return column; }
}
