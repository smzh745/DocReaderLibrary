package device.s.docreader.office.fc.hslf.record;

/**
 * A record that can be referenced in PersistPtr storage.
 *
 * @author Yegor Kozlov
 */
public interface PersistRecord
{

    /**
     * Fetch the persist ID
     */
    public int getPersistId();

    /**
     * Set the persist ID
     */
    public void setPersistId(int id);
    
    /**
     * 
     */
    public void dispose();
}
