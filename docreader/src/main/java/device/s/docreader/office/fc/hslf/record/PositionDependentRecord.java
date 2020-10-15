package device.s.docreader.office.fc.hslf.record;
import java.util.Hashtable;

/**
 * Records which either care about where they are on disk, or have other
 *  records who care about where they are, will implement this interface.
 * Normally, they'll subclass PositionDependentRecordAtom or
 *  PositionDependentRecordContainer, which will do the work of providing
 *  the setting and updating interfaces for them.
 * This is a special (and dangerous) kind of Record. When created, they
 *  need to be pinged with their current location. When written out, they
 *  need to be given their new location, and offered the list of records
 *  which have changed their location.
 *
 * @author Nick Burch
 */

public interface PositionDependentRecord
{
	/** Fetch our location on the disk, as of the last write out */
	public int getLastOnDiskOffset();

	/**
	 * Update the Record's idea of where on disk it lives, after a write out.
	 * Use with care...
	 */
	public void setLastOnDiskOffset(int offset);

	/**
	 * Offer the record the list of records that have changed their
	 *  location as part of the writeout.
	 */
	public void updateOtherRecordReferences(Hashtable<Integer, Integer> oldToNewReferencesLookup);
	
	/**
     * 
     */
    public void dispose();
}
