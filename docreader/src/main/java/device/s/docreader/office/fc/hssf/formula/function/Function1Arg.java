package device.s.docreader.office.fc.hssf.formula.function;

import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;

/**
 * Implemented by all functions that can be called with one argument
 *
 * @author Josh Micich
 */
public interface Function1Arg extends Function {
	/**
	 * see {@link Function#evaluate(ValueEval[], int, int)}
	 */
	ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0);
}
