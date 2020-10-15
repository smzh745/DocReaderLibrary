package device.s.docreader.office.fc.hwpf.model;


/**
 * Normally PropertyNodes only ever work in characters, but
 *  a few cases actually store bytes, and this lets everything
 *  still work despite that.
 * It handles the conversion as required between bytes
 *  and characters.
 *  
 *  @deprecated byte positions shall not be saved in memory
 */
@Deprecated
public abstract class BytePropertyNode<T extends BytePropertyNode<T>> extends
        PropertyNode<T>
{
        private final int startBytes;
        private final int endBytes;

	/**
	 * @param fcStart The start of the text for this property, in _bytes_
	 * @param fcEnd The end of the text for this property, in _bytes_
	 * @deprecated
	 */
	public BytePropertyNode(int fcStart, int fcEnd, CharIndexTranslator translator, Object buf) {
		super(
				translator.getCharIndex(fcStart),
				translator.getCharIndex(fcEnd, translator.getCharIndex(fcStart)),
				buf
		);

        if ( fcStart > fcEnd )
            throw new IllegalArgumentException( "fcStart (" + fcStart
                    + ") > fcEnd (" + fcEnd + ")" );

                this.startBytes = fcStart;
                this.endBytes = fcEnd;
	}

    public BytePropertyNode( int charStart, int charEnd, Object buf )
    {
        super( charStart, charEnd, buf );

        if ( charStart > charEnd )
            throw new IllegalArgumentException( "charStart (" + charStart
                    + ") > charEnd (" + charEnd + ")" );

        this.startBytes = -1;
        this.endBytes = -1;
    }

    /**
     * @deprecated Though bytes are actually stored in file, it is advised to
     *             use char positions for all operations. Including save
     *             operations, because only char positions are preserved.
     */
	@Deprecated
    public int getStartBytes()
    {
        return startBytes;
    }

    /**
     * @deprecated Though bytes are actually stored in file, it is advised to
     *             use char positions for all operations. Including save
     *             operations, because only char positions are preserved.
     */
    @Deprecated
    public int getEndBytes()
    {
        return endBytes;
    }
}
