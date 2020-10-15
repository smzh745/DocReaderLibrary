package device.s.docreader.office.fc.hssf.eventmodel;

import device.s.docreader.office.fc.hssf.record.ContinueRecord;
import device.s.docreader.office.fc.hssf.record.Record;
import device.s.docreader.office.fc.hssf.record.RecordFactory;
import device.s.docreader.office.fc.hssf.record.RecordFormatException;
import device.s.docreader.office.fc.hssf.record.RecordInputStream;

import java.io.InputStream;
import java.util.Arrays;


/**
 * Event-based record factory.  As opposed to RecordFactory
 * this version sends {@link ERFListener#processRecord(Record) } messages to
 * the supplied listener.  Record notifications are sent one record behind
 * to ensure that {@link ContinueRecord}s are processed first.
 *
 * @author Andrew C. Oliver (acoliver@apache.org) - probably to blame for the bugs (so yank his chain on the list)
 * @author Marc Johnson (mjohnson at apache dot org) - methods taken from RecordFactory
 * @author Glen Stampoultzis (glens at apache.org) - methods taken from RecordFactory
 * @author Csaba Nagy (ncsaba at yahoo dot com)
 */
public final class EventRecordFactory {

	private final ERFListener _listener;
	private final short[] _sids;

	/**
	 *
	 * @param sids an array of Record.sid values identifying the records
	 * the listener will work with.  Alternatively if this is "null" then
	 * all records are passed. For all 'known' record types use {@link RecordFactory#getAllKnownRecordSIDs()}
	 */
	public EventRecordFactory(ERFListener listener, short[] sids) {
		_listener = listener;
		if (sids == null) {
			_sids = null;
		} else {
			_sids = sids.clone();
			Arrays.sort(_sids); // for faster binary search
		}
	}
	private boolean isSidIncluded(short sid) {
		if (_sids == null) {
			return true;
		}
		return Arrays.binarySearch(_sids, sid) >= 0;
	}


	/**
	 * sends the record event to all registered listeners.
	 * @param record the record to be thrown.
	 * @return <code>false</code> to abort.  This aborts
	 * out of the event loop should the listener return false
	 */
	private boolean processRecord(Record record) {
		if (!isSidIncluded(record.getSid())) {
			return true;
		}
		return _listener.processRecord(record);
	}

	/**
	 * Create an array of records from an input stream
	 *
	 * @param in the InputStream from which the records will be
	 *		   obtained
	 *
	 * @exception RecordFormatException on error processing the
	 *			InputStream
	 */
	public void processRecords(InputStream in) throws RecordFormatException {
		Record last_record = null;

		RecordInputStream recStream = new RecordInputStream(in);

		while (recStream.hasNextRecord()) {
			recStream.nextRecord();
			Record[] recs = RecordFactory.createRecord(recStream);   // handle MulRK records
			if (recs.length > 1) {
				for (int k = 0; k < recs.length; k++) {
					if ( last_record != null ) {
						if (!processRecord(last_record)) {
							return;
						}
					}
					last_record = recs[ k ]; // do to keep the algorithm homogeneous...you can't
				}							// actually continue a number record anyhow.
			} else {
				Record record = recs[ 0 ];

				if (record != null) {
					if (last_record != null) {
						if (!processRecord(last_record)) {
							return;
						}
					}
					 last_record = record;
				}
			}
		}

		if (last_record != null) {
			processRecord(last_record);
		}
	}
}
