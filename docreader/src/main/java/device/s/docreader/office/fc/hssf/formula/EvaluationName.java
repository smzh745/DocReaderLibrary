package device.s.docreader.office.fc.hssf.formula;

import device.s.docreader.office.fc.hssf.formula.ptg.NamePtg;
import device.s.docreader.office.fc.hssf.formula.ptg.Ptg;

/**
 * Abstracts a name record for formula evaluation.<br/>
 *
 * For POI internal use only
 *
 * @author Josh Micich
 */
public interface EvaluationName {

	String getNameText();

	boolean isFunctionName();

	boolean hasFormula();

	Ptg[] getNameDefinition();

	boolean isRange();
	NamePtg createPtg();
}
