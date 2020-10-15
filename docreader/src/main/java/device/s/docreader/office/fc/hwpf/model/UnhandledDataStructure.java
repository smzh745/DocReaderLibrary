package device.s.docreader.office.fc.hwpf.model;

import device.s.docreader.office.fc.util.Internal;

@ Internal
public final class UnhandledDataStructure
{
    byte[] _buf;

    public UnhandledDataStructure(byte[] buf, int offset, int length)
    {
        //    System.out.println("Yes, using my code");
        _buf = new byte[length];
        if (offset + length > buf.length)
        {
            throw new IndexOutOfBoundsException("buffer length is " + buf.length
                + "but code is trying to read " + length + " from offset " + offset);
        }
        System.arraycopy(buf, offset, _buf, 0, length);
    }

    byte[] getBuf()
    {
        return _buf;
    }
}
