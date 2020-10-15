package device.s.docreader.office.fc.hssf.formula;

/**
 * This exception thrown when a supplied formula has incorrect syntax (or syntax currently not
 * supported by POI).  It  is primarily used by test code to confirm specific parsing exceptions.
 * Application code should also handle this exception if it potentially supplies formula text
 * that is not guaranteed to be well-formed.
 *
 * @author Josh Micich
 */
public final class FormulaParseException extends RuntimeException {

	FormulaParseException(String msg) {
		super(msg);
	}
}
