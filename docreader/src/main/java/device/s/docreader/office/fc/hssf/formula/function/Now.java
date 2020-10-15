package device.s.docreader.office.fc.hssf.formula.function;

import device.s.docreader.office.fc.hssf.formula.eval.NumberEval;
import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;
import device.s.docreader.office.ss.util.DateUtil;

import java.util.Date;


/**
 * Implementation of Excel NOW() Function
 *
 * @author Frank Taffelt
 */
public final class Now extends Fixed0ArgFunction {

	public ValueEval evaluate(int srcRowIndex, int srcColumnIndex) {
		Date now = new Date(System.currentTimeMillis());
		return new NumberEval(DateUtil.getExcelDate(now));
	}
}
