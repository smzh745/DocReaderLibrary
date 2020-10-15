package device.s.docreader.office.fc.hslf.record;

/**
 * A container record that specifies information about a movie stored externally.
 *
 * @author Yegor Kozlov
 */
public final class ExAviMovie extends ExMCIMovie
{

    /**
     * Set things up, and find our more interesting children
     */
    protected ExAviMovie(byte[] source, int start, int len)
    {
        super(source, start, len);
    }

    /**
     * Create a new ExAviMovie, with blank fields
     */
    public ExAviMovie()
    {
        super();

    }

    /**
     * We are of type 4102
     */
    public long getRecordType()
    {
        return RecordTypes.ExAviMovie.typeID;
    }
}
