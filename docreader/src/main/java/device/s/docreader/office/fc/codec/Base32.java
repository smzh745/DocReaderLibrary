
package device.s.docreader.office.fc.codec;

public class Base32 extends BaseNCodec {


    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;

    private static final byte[] CHUNK_SEPARATOR = {'\r', '\n'};


    private static final byte[] DECODE_TABLE = {
         //  0   1   2   3   4   5   6   7   8   9   A   B   C   D   E   F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00-0f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10-1f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, // 20-2f
            -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, // 30-3f 2-7
            -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, // 40-4f A-N
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,                     // 50-5a O-Z
    };


    private static final byte[] ENCODE_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '2', '3', '4', '5', '6', '7',
    };

    private static final byte[] HEX_DECODE_TABLE = {
         //  0   1   2   3   4   5   6   7   8   9   A   B   C   D   E   F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00-0f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10-1f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, // 20-2f
             0,  1,  2,  3,  4,  5,  6,  7,  8,  9, -1, -1, -1, -1, -1, -1, // 30-3f 2-7
            -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, // 40-4f A-N
            25, 26, 27, 28, 29, 30, 31, 32,                                 // 50-57 O-V
    };

    private static final byte[] HEX_ENCODE_TABLE = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
    };

    /** Mask used to extract 5 bits, used when encoding Base32 bytes */
    private static final int MASK_5BITS = 0x1f;

    // The static final fields above are used for the original static byte[] methods on Base32.
    // The private member fields below are used with the new streaming approach, which requires
    // some state be preserved between calls of encode() and decode().

    /**
     * Place holder for the bytes we're dealing with for our based logic. 
     * Bitwise operations store and extract the encoding or decoding from this variable.
     */
    private long bitWorkArea;

    private final int decodeSize;

    /**
     * Decode table to use.
     */
    private final byte[] decodeTable;

    private final int encodeSize;

    /**
     * Encode table to use.
     */
    private final byte[] encodeTable;

    /**
     * Line separator for encoding. Not used when decoding. Only used if lineLength > 0.
     */
    private final byte[] lineSeparator;


    public Base32() {
        this(false);
    }


    public Base32(boolean useHex) {
        this(0, null, useHex);
    }


    public Base32(int lineLength) {
        this(lineLength, CHUNK_SEPARATOR);
    }


    public Base32(int lineLength, byte[] lineSeparator) {
        this(lineLength, lineSeparator, false);
    }
    

    public Base32(int lineLength, byte[] lineSeparator, boolean useHex) {
        super(BYTES_PER_UNENCODED_BLOCK, BYTES_PER_ENCODED_BLOCK, 
                lineLength, 
                lineSeparator == null ? 0 : lineSeparator.length);
        if (useHex){
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;            
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;            
        }
        if (lineLength > 0) {
            if (lineSeparator == null) {
                throw new IllegalArgumentException("lineLength "+lineLength+" > 0, but lineSeparator is null");
            }
            // Must be done after initializing the tables
            if (containsAlphabetOrPad(lineSeparator)) {
                String sep = StringUtils.newStringUtf8(lineSeparator);
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + sep + "]");
            }
            this.encodeSize = BYTES_PER_ENCODED_BLOCK + lineSeparator.length;
            this.lineSeparator = new byte[lineSeparator.length];
            System.arraycopy(lineSeparator, 0, this.lineSeparator, 0, lineSeparator.length);
        } else {
            this.encodeSize = BYTES_PER_ENCODED_BLOCK;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
    }


    void decode(byte[] in, int inPos, int inAvail) { // package protected for access from I/O streams
        if (eof) {
            return;
        }
        if (inAvail < 0) {
            eof = true;
        }
        for (int i = 0; i < inAvail; i++) {
            byte b = in[inPos++];
            if (b == PAD) {
                // We're done.
                eof = true;
                break;
            } else {
                ensureBufferSize(decodeSize);
                if (b >= 0 && b < this.decodeTable.length) {
                    int result = this.decodeTable[b];
                    if (result >= 0) {
                        modulus = (modulus+1) % BYTES_PER_ENCODED_BLOCK;
                        bitWorkArea = (bitWorkArea << BITS_PER_ENCODED_BYTE) + result; // collect decoded bytes
                        if (modulus == 0) { // we can output the 5 bytes
                            buffer[pos++] = (byte) ((bitWorkArea >> 32) & MASK_8BITS);
                            buffer[pos++] = (byte) ((bitWorkArea >> 24) & MASK_8BITS);
                            buffer[pos++] = (byte) ((bitWorkArea >> 16) & MASK_8BITS);
                            buffer[pos++] = (byte) ((bitWorkArea >> 8) & MASK_8BITS);
                            buffer[pos++] = (byte) (bitWorkArea & MASK_8BITS);
                        }
                    }
                }
            }
        }
    
        // Two forms of EOF as far as Base32 decoder is concerned: actual
        // EOF (-1) and first time '=' character is encountered in stream.
        // This approach makes the '=' padding characters completely optional.
        if (eof && modulus >= 2) { // if modulus < 2, nothing to do
            ensureBufferSize(decodeSize);
    
            //  we ignore partial bytes, i.e. only multiples of 8 count
            switch (modulus) {
                case 2 : // 10 bits, drop 2 and output one byte
                    buffer[pos++] = (byte) ((bitWorkArea >> 2) & MASK_8BITS);
                    break;
                case 3 : // 15 bits, drop 7 and output 1 byte
                    buffer[pos++] = (byte) ((bitWorkArea >> 7) & MASK_8BITS);
                    break;
                case 4 : // 20 bits = 2*8 + 4
                    bitWorkArea = bitWorkArea >> 4; // drop 4 bits
                    buffer[pos++] = (byte) ((bitWorkArea >> 8) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea) & MASK_8BITS);
                    break;
                case 5 : // 25bits = 3*8 + 1
                    bitWorkArea = bitWorkArea >> 1;
                    buffer[pos++] = (byte) ((bitWorkArea >> 16) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea >> 8) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea) & MASK_8BITS);
                    break;
                case 6 : // 30bits = 3*8 + 6
                    bitWorkArea = bitWorkArea >> 6;
                    buffer[pos++] = (byte) ((bitWorkArea >> 16) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea >> 8) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea) & MASK_8BITS);
                    break;
                case 7 : // 35 = 4*8 +3
                    bitWorkArea = bitWorkArea >> 3;
                    buffer[pos++] = (byte) ((bitWorkArea >> 24) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea >> 16) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea >> 8) & MASK_8BITS);
                    buffer[pos++] = (byte) ((bitWorkArea) & MASK_8BITS);
                    break;
            }
        }
    }

    void encode(byte[] in, int inPos, int inAvail) { // package protected for access from I/O streams
        if (eof) {
            return;
        }
        // inAvail < 0 is how we're informed of EOF in the underlying data we're
        // encoding.
        if (inAvail < 0) {
            eof = true;
            if (0 == modulus && lineLength == 0) {
                return; // no leftovers to process and not using chunking
            }
            ensureBufferSize(encodeSize);
            int savedPos = pos;
            switch (modulus) { // % 5
                case 1 : // Only 1 octet; take top 5 bits then remainder
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 3) & MASK_5BITS]; // 8-1*5 = 3
                    buffer[pos++] = encodeTable[(int)(bitWorkArea << 2) & MASK_5BITS]; // 5-3=2
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    break;
    
                case 2 : // 2 octets = 16 bits to use
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 11) & MASK_5BITS]; // 16-1*5 = 11
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >>  6) & MASK_5BITS]; // 16-2*5 = 6
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >>  1) & MASK_5BITS]; // 16-3*5 = 1
                    buffer[pos++] = encodeTable[(int)(bitWorkArea <<  4) & MASK_5BITS]; // 5-1 = 4
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    break;
                case 3 : // 3 octets = 24 bits to use
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 19) & MASK_5BITS]; // 24-1*5 = 19
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 14) & MASK_5BITS]; // 24-2*5 = 14
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >>  9) & MASK_5BITS]; // 24-3*5 = 9
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >>  4) & MASK_5BITS]; // 24-4*5 = 4
                    buffer[pos++] = encodeTable[(int)(bitWorkArea <<  1) & MASK_5BITS]; // 5-4 = 1
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    buffer[pos++] = PAD;
                    break;
                case 4 : // 4 octets = 32 bits to use
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 27) & MASK_5BITS]; // 32-1*5 = 27
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 22) & MASK_5BITS]; // 32-2*5 = 22
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 17) & MASK_5BITS]; // 32-3*5 = 17
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 12) & MASK_5BITS]; // 32-4*5 = 12
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >>  7) & MASK_5BITS]; // 32-5*5 =  7
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >>  2) & MASK_5BITS]; // 32-6*5 =  2
                    buffer[pos++] = encodeTable[(int)(bitWorkArea <<  3) & MASK_5BITS]; // 5-2 = 3
                    buffer[pos++] = PAD;
                    break;
            }
            currentLinePos += pos - savedPos; // keep track of current line position
            // if currentPos == 0 we are at the start of a line, so don't add CRLF
            if (lineLength > 0 && currentLinePos > 0){ // add chunk separator if required
                System.arraycopy(lineSeparator, 0, buffer, pos, lineSeparator.length);
                pos += lineSeparator.length;
            }            
        } else {
            for (int i = 0; i < inAvail; i++) {
                ensureBufferSize(encodeSize);
                modulus = (modulus+1) % BYTES_PER_UNENCODED_BLOCK;
                int b = in[inPos++];
                if (b < 0) {
                    b += 256;
                }
                bitWorkArea = (bitWorkArea << 8) + b; // BITS_PER_BYTE
                if (0 == modulus) { // we have enough bytes to create our output 
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 35) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 30) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 25) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 20) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 15) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 10) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)(bitWorkArea >> 5) & MASK_5BITS];
                    buffer[pos++] = encodeTable[(int)bitWorkArea & MASK_5BITS];
                    currentLinePos += BYTES_PER_ENCODED_BLOCK;
                    if (lineLength > 0 && lineLength <= currentLinePos) {
                        System.arraycopy(lineSeparator, 0, buffer, pos, lineSeparator.length);
                        pos += lineSeparator.length;
                        currentLinePos = 0;
                    }
                }
            }
        }
    }


    public boolean isInAlphabet(byte octet) {
        return octet >= 0 && octet < decodeTable.length && decodeTable[octet] != -1;
    }
}
