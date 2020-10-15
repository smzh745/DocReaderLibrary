
package device.s.docreader.office.fc.codec;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseNCodecInputStream extends FilterInputStream {

    private final boolean doEncode;

    private final BaseNCodec baseNCodec;

    private final byte[] singleByte = new byte[1];

    protected BaseNCodecInputStream(InputStream in, BaseNCodec baseNCodec, boolean doEncode) {
        super(in);
        this.doEncode = doEncode;
        this.baseNCodec = baseNCodec;
    }


    public int read() throws IOException {
        int r = read(singleByte, 0, 1);
        while (r == 0) {
            r = read(singleByte, 0, 1);
        }
        if (r > 0) {
            return singleByte[0] < 0 ? 256 + singleByte[0] : singleByte[0];
        }
        return -1;
    }

    public int read(byte b[], int offset, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (offset < 0 || len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (offset > b.length || offset + len > b.length) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        } else {
            int readLen = 0;
            /*
             Rationale for while-loop on (readLen == 0):
             -----
             Base32.readResults() usually returns > 0 or EOF (-1).  In the
             rare case where it returns 0, we just keep trying.

             This is essentially an undocumented contract for InputStream
             implementors that want their code to work properly with
             java.io.InputStreamReader, since the latter hates it when
             InputStream.read(byte[]) returns a zero.  Unfortunately our
             readResults() call must return 0 if a large amount of the data
             being decoded was non-base32, so this while-loop enables proper
             interop with InputStreamReader for that scenario.
             -----
             This is a fix for CODEC-101
            */
            while (readLen == 0) {
                if (!baseNCodec.hasData()) {
                    byte[] buf = new byte[doEncode ? 4096 : 8192];
                    int c = in.read(buf);
                    if (doEncode) {
                        baseNCodec.encode(buf, 0, c);
                    } else {
                        baseNCodec.decode(buf, 0, c);
                    }
                }
                readLen = baseNCodec.readResults(b, offset, len);
            }
            return readLen;
        }
    }
    /**
     * {@inheritDoc}
     * 
     * @return false
     */
    public boolean markSupported() {
        return false; // not an easy job to support marks
    }

}
