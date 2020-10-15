package device.s.docreader.office.fc.hssf.formula;

import device.s.docreader.office.fc.hssf.formula.eval.ValueEval;


final class PlainValueCellCacheEntry extends CellCacheEntry {
	public PlainValueCellCacheEntry(ValueEval value) {
		updateValue(value);
	}
}
