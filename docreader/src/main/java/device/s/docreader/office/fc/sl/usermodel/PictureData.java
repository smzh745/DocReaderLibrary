package device.s.docreader.office.fc.sl.usermodel;

public interface PictureData {
	public int getType();
	public byte[] getUID();

	public byte[] getData();
	public void setData(byte[] data);
}
