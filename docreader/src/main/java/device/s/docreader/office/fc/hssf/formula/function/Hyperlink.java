package device.s.docreader.office.fc.hssf.formula.function;

import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;


public final class Hyperlink extends Var1or2ArgFunction {

	public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
		return arg0;
	}
	public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
		// note - if last arg is MissingArgEval, result will be NumberEval.ZERO,
		// but WorkbookEvaluator does that translation
		return arg1;
	}
}
