package device.s.docreader.office.fc.openxml4j.opc;

import device.s.docreader.office.fc.openxml4j.exceptions.InvalidFormatException;
import device.s.docreader.office.fc.openxml4j.exceptions.InvalidOperationException;
import device.s.docreader.office.fc.openxml4j.exceptions.OpenXML4JException;
import device.s.docreader.office.fc.openxml4j.opc.internal.marshallers.ZipPartMarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;


/**
 * Zip implementation of a PackagePart.
 *
 * @author Julien Chable
 * @version 1.0
 * @see PackagePart
 */
public class ZipPackagePart extends PackagePart
{

    /**
     * The zip entry corresponding to this part.
     */
    private ZipEntry zipEntry;

    /**
     * Constructor.
     *
     * @param container
     *            The container package.
     * @param partName
     *            Part name.
     * @param contentType
     *            Content type.
     * @throws InvalidFormatException
     *             Throws if the content of this part invalid.
     */
    public ZipPackagePart(ZipPackage container, PackagePartName partName, String contentType)
        throws InvalidFormatException
    {
        super(container, partName, contentType);
    }

    /**
     * Constructor.
     *
     * @param container
     *            The container package.
     * @param zipEntry
     *            The zip entry corresponding to this part.
     * @param partName
     *            The part name.
     * @param contentType
     *            Content type.
     * @throws InvalidFormatException
     *             Throws if the content of this part is invalid.
     */
    public ZipPackagePart(ZipPackage container, ZipEntry zipEntry, PackagePartName partName,
                          String contentType) throws InvalidFormatException
    {
        super(container, partName, contentType);
        this.zipEntry = zipEntry;
    }

    /**
     * Get the zip entry of this part.
     *
     * @return The zip entry in the zip structure coresponding to this part.
     */
    public ZipEntry getZipArchive()
    {
        return zipEntry;
    }

    /**
     * Implementation of the getInputStream() which return the inputStream of
     * this part zip entry.
     *
     * @return Input stream of this part zip entry.
     */
    @Override
    protected InputStream getInputStreamImpl() throws IOException
    {
        // We use the getInputStream() method from java.util.zip.ZipFile
        // class which return an InputStream to this part zip entry.
        return ((ZipPackage)_container).getZipArchive().getInputStream(zipEntry);
    }

    /**
     * Implementation of the getOutputStream(). Return <b>null</b>. Normally
     * will never be called since the MemoryPackage is use instead.
     *
     * @return <b>null</b>
     */
    @Override
    protected OutputStream getOutputStreamImpl()
    {
        return null;
    }

    @Override
    public boolean save(OutputStream os) throws OpenXML4JException
    {
        return new ZipPartMarshaller().marshall(this, os);
    }

    @Override
    public boolean load(InputStream ios)
    {
        throw new InvalidOperationException("Method not implemented !");
    }

    @Override
    public void close()
    {
        throw new InvalidOperationException("Method not implemented !");
    }

    @Override
    public void flush()
    {
        throw new InvalidOperationException("Method not implemented !");
    }
}
