package device.s.docreader.office.fc.hssf.formula;


import device.s.docreader.office.fc.hssf.formula.eval.AreaEval;
import device.s.docreader.office.fc.hssf.formula.eval.RefEvalBase;
import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;
import device.s.docreader.office.fc.hssf.formula.ptg.AreaI;
import device.s.docreader.office.fc.hssf.formula.ptg.AreaI.OffsetArea;
import device.s.docreader.office.fc.ss.util.CellReference;


/**
*
* @author Josh Micich
*/
final class LazyRefEval extends RefEvalBase {

	private final SheetRefEvaluator _evaluator;

	public LazyRefEval(int rowIndex, int columnIndex, SheetRefEvaluator sre) {
		super(rowIndex, columnIndex);
		if (sre == null) {
			throw new IllegalArgumentException("sre must not be null");
		}
		_evaluator = sre;
	}

	public ValueEval getInnerValueEval() {
		return _evaluator.getEvalForCell(getRow(), getColumn());
	}

	public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {

		AreaI area = new OffsetArea(getRow(), getColumn(),
				relFirstRowIx, relLastRowIx, relFirstColIx, relLastColIx);

		return new LazyAreaEval(area, _evaluator);
	}

	public String toString() {
		CellReference cr = new CellReference(getRow(), getColumn());
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getName()).append("[");
		sb.append(_evaluator.getSheetName());
		sb.append('!');
		sb.append(cr.formatAsString());
		sb.append("]");
		return sb.toString();
	}
}
