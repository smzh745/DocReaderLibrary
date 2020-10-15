package device.s.docreader.office.fc.hssf.formula;


public interface ExternSheetReferenceToken {
	int getExternSheetIndex();

	String format2DRefAsString();
}
