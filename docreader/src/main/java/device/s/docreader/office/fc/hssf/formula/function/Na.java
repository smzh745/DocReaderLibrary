package device.s.docreader.office.fc.hssf.formula.function;

import device.s.docreader.office.fc.hssf.formula.eval.ErrorEval;
import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;

/**
 * Implementation of Excel function NA()
 *
 * @author Josh Micich
 */
public final class Na extends Fixed0ArgFunction {

	public ValueEval evaluate(int srcCellRow, int srcCellCol) {
		return ErrorEval.NA;
	}
}
