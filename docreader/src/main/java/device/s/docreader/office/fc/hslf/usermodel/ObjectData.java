package device.s.docreader.office.fc.hslf.usermodel;

import device.s.docreader.office.fc.hslf.record.ExOleObjStg;

import java.io.IOException;
import java.io.InputStream;


/**
 * A class that represents object data embedded in a slide show.
 *
 * @author Daniel Noll
 */
public class ObjectData
{
    /**
     * The record that contains the object data.
     */
    private ExOleObjStg storage;

    /**
     * Creates the object data wrapping the record that contains the object data.
     *
     * @param storage the record that contains the object data.
     */
    public ObjectData(ExOleObjStg storage)
    {
        this.storage = storage;
    }

    /**
     * Gets an input stream which returns the binary of the embedded data.
     *
     * @return the input stream which will contain the binary of the embedded data.
     */
    public InputStream getData()
    {
        return storage.getData();
    }

    /**
     * Sets the embedded data.
     *
     * @param data the embedded data.
     */
    public void setData(byte[] data) throws IOException
    {
        storage.setData(data);
    }

    /**
     * Return the record that contains the object data.
     *
     * @return the record that contains the object data.
     */
    public ExOleObjStg getExOleObjStg()
    {
        return storage;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        if (storage != null)
        {
            storage.dispose();
            storage = null;
        }
    }
}
