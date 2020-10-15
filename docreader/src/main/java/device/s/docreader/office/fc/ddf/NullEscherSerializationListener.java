package device.s.docreader.office.fc.ddf;


public class NullEscherSerializationListener implements EscherSerializationListener {
    public void beforeRecordSerialize(int offset, short recordId, EscherRecord record) {
        // do nothing
    }

    public void afterRecordSerialize(int offset, short recordId, int size, EscherRecord record) {
        // do nothing
    }
}
