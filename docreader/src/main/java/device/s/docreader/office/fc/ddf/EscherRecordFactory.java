
package device.s.docreader.office.fc.ddf;

public interface EscherRecordFactory {

    EscherRecord createRecord(byte[] data, int offset);
}
