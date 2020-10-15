
package device.s.docreader.office.fc.codec;


public interface BinaryDecoder extends Decoder {


    byte[] decode(byte[] source) throws DecoderException;
}  

