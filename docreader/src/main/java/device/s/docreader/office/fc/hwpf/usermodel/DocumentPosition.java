package device.s.docreader.office.fc.hwpf.usermodel;

import device.s.docreader.office.fc.hwpf.HWPFDocument;

public final class DocumentPosition extends Range
{
    public DocumentPosition(HWPFDocument doc, int pos)
    {
        super(pos, pos, doc);
    }

}
