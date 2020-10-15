package device.s.docreader.office.fc.hssf.formula.function;


import device.s.docreader.office.fc.hssf.formula.TwoDEval;
import device.s.docreader.office.fc.hssf.formula.eval.ErrorEval;
import device.s.docreader.office.fc.hssf.formula.eval.NumberEval;
import device.s.docreader.office.fc.hssf.formula.eval.RefEval;
import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;


/**
 * Implementation for Excel COLUMNS function.
 *
 * @author Josh Micich
 */
public final class Columns extends Fixed1ArgFunction {

	public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {

		int result;
		if (arg0 instanceof TwoDEval) {
			result = ((TwoDEval) arg0).getWidth();
		} else if (arg0 instanceof RefEval) {
			result = 1;
		} else { // anything else is not valid argument
			return ErrorEval.VALUE_INVALID;
		}
		return new NumberEval(result);
	}
}
