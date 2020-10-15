package device.s.docreader.office.fc.hssf.record.pivottable;


import device.s.docreader.office.fc.hssf.record.RecordInputStream;
import device.s.docreader.office.fc.hssf.record.StandardRecord;
import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndianOutput;


/**
 * SXIDSTM - Stream ID (0x00D5)<br/>
 * 
 * @author Patrick Cheng
 */
public final class StreamIDRecord extends StandardRecord {
	public static final short sid = 0x00D5;

	private int idstm;
	
	public StreamIDRecord(RecordInputStream in) {
		idstm = in.readShort();
	}
	
	@Override
	protected void serialize(LittleEndianOutput out) {
		out.writeShort(idstm);
	}

	@Override
	protected int getDataSize() {
		return 2;
	}

	@Override
	public short getSid() {
		return sid;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[SXIDSTM]\n");
		buffer.append("    .idstm      =").append(HexDump.shortToHex(idstm)).append('\n');

		buffer.append("[/SXIDSTM]\n");
		return buffer.toString();
	}
}
