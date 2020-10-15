package device.s.docreader.office.fc.hslf.record;

import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndian;
import device.s.docreader.office.fc.util.StringUtil;


/**
 * A TextBytesAtom (type 4008). Holds text in ascii form (unknown
 *  code page, for now assumed to be the default of
 *  arc.fc.util.StringUtil, which is the Excel default).
 * The trailing return character is always stripped from this
 *
 * @author Nick Burch
 */

public final class TextBytesAtom extends RecordAtom
{
	private byte[] _header;
	private static long _type = 4008l;

	/** The bytes that make up the text */
	private byte[] _text;

	/** Grabs the text. Uses the default codepage */
	public String getText() {
		return StringUtil.getFromCompressedUnicode(_text,0,_text.length);
	}

	/** Updates the text in the Atom. Must be 8 bit ascii */
	public void setText(byte[] b) {
		// Set the text
		_text = b;

		// Update the size (header bytes 5-8)
		LittleEndian.putInt(_header,4,_text.length);
	}

	/* *************** record code follows ********************** */

	/**
	 * For the TextBytes Atom
	 */
	protected TextBytesAtom(byte[] source, int start, int len) {
		// Sanity Checking
		if(len < 8) { len = 8; }

		// Get the header
		_header = new byte[8];
		System.arraycopy(source,start,_header,0,8);

		// Grab the text
		_text = new byte[len-8];
		System.arraycopy(source,start+8,_text,0,len-8);
	}

	/**
	 * Create an empty TextBytes Atom
	 */
	public TextBytesAtom() {
		_header = new byte[8];
		LittleEndian.putUShort(_header, 0, 0);
		LittleEndian.putUShort(_header, 2, (int)_type);
		LittleEndian.putInt(_header, 4, 0);

		_text = new byte[]{};
	}

	/**
	 * We are of type 4008
	 */
	public long getRecordType() { return _type; }


	/**
	 * dump debug info; use getText() to return a string
	 * representation of the atom
	 */
	public String toString() {
        StringBuffer out = new StringBuffer();
        out.append( "TextBytesAtom:\n");
		out.append( HexDump.dump(_text, 0, 0) );
		return out.toString();
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
	    _header = null;
	    _text = null;
	}
}
