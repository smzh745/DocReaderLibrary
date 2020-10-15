package device.s.docreader.office.fc.hslf.exceptions;

import device.s.docreader.office.fc.EncryptedDocumentException;

/**
 * This exception is thrown when we try to open a PowerPoint file, and
 *  discover that it is encrypted
 */
public final class EncryptedPowerPointFileException extends EncryptedDocumentException
{
    public EncryptedPowerPointFileException(String s)
    {
        super(s);
    }
}
