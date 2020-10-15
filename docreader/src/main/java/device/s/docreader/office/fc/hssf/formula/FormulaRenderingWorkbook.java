package device.s.docreader.office.fc.hssf.formula;


import device.s.docreader.office.fc.hssf.formula.EvaluationWorkbook.ExternalSheet;
import device.s.docreader.office.fc.hssf.formula.ptg.NamePtg;
import device.s.docreader.office.fc.hssf.formula.ptg.NameXPtg;


public interface FormulaRenderingWorkbook {


	ExternalSheet getExternalSheet(int externSheetIndex);
	String getSheetNameByExternSheet(int externSheetIndex);
	String resolveNameXText(NameXPtg nameXPtg);
	String getNameText(NamePtg namePtg);
}
