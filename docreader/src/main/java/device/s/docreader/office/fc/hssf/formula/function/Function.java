package device.s.docreader.office.fc.hssf.formula.function;

import device.s.docreader.office.fc.hssf.formula.eval.BlankEval;
import device.s.docreader.office.fc.hssf.formula.eval.ErrorEval;
import device.s.docreader.office.fc.hssf.formula.eval.MissingArgEval;
import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;

/**
 * Common interface for all implementations of Excel built-in functions.
 *
 * @author Amol S. Deshmukh &lt; amolweb at ya hoo dot com &gt;
 */
public interface Function {

	/**
	 * @param args the evaluated function arguments.  Empty values are represented with
	 * {@link BlankEval} or {@link MissingArgEval}, never <code>null</code>.
	 * @param srcRowIndex row index of the cell containing the formula under evaluation
	 * @param srcColumnIndex column index of the cell containing the formula under evaluation
	 * @return The evaluated result, possibly an {@link ErrorEval}, never <code>null</code>.
	 * <b>Note</b> - Excel uses the error code <i>#NUM!</i> instead of IEEE <i>NaN</i>, so when
	 * numeric functions evaluate to {@link Double#NaN} be sure to translate the result to {@link
	 * ErrorEval#NUM_ERROR}.
	 */
	ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex);
}
