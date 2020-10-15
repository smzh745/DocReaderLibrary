/*
 * DateUtil.java
 *
 * Created on January 19, 2002, 9:30 AM
 */
package device.s.docreader.office.fc.hssf.usermodel;

import device.s.docreader.office.ss.util.DateUtil;

import java.util.Calendar;


/**
 * Contains methods for dealing with Excel dates.
 *
 * @author  Michael Harhen
 * @author  Glen Stampoultzis (glens at apache.org)
 * @author  Dan Sherman (dsherman at isisph.com)
 * @author  Hack Kampbjorn (hak at 2mba.dk)
 * @author  Alex Jacoby (ajacoby at gmail.com)
 * @author  Pavel Krupets (pkrupets at palmtreebusiness dot com)
 */

public class HSSFDateUtil extends DateUtil {
	protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
		return DateUtil.absoluteDay(cal, use1904windowing);
	}
}
