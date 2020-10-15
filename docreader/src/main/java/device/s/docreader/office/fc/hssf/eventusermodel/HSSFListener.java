
package device.s.docreader.office.fc.hssf.eventusermodel;

import device.s.docreader.office.fc.hssf.record.Record;

/**
 * Interface for use with the HSSFRequest and HSSFEventFactory.  Users should create
 * a listener supporting this interface and register it with the HSSFRequest (associating
 * it with Record SID's).
 *
 * @see HSSFEventFactory
 * @see HSSFRequest
 * @author  acoliver@apache.org
 */

public interface HSSFListener
{

    /**
     * process an HSSF Record. Called when a record occurs in an HSSF file.
     */

    public void processRecord(Record record);
}
