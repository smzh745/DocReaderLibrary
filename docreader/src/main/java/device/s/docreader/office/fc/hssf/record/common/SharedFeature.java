package device.s.docreader.office.fc.hssf.record.common;

import device.s.docreader.office.fc.util.LittleEndianOutput;

/**
 * Common Interface for all Shared Features
 */
public interface SharedFeature {
	public String toString();
	public void serialize(LittleEndianOutput out);
	public int getDataSize();
}
