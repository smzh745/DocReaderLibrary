package device.s.docreader.office.fc.hwpf.usermodel;

import device.s.docreader.office.fc.hwpf.model.FieldsDocumentPart;

import java.util.Collection;


/**
 * User-friendly interface to access document {@link Field}s
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */
public interface Fields
{
    Field getFieldByStartOffset(FieldsDocumentPart documentPart, int offset);

    Collection<Field> getFields(FieldsDocumentPart part);
}
