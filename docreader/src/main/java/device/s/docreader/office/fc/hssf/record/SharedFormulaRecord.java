package device.s.docreader.office.fc.hssf.record;

import device.s.docreader.office.fc.hssf.formula.Formula;
import device.s.docreader.office.fc.hssf.formula.SharedFormula;
import device.s.docreader.office.fc.hssf.formula.ptg.Ptg;
import device.s.docreader.office.fc.hssf.util.CellRangeAddress8Bit;
import device.s.docreader.office.fc.ss.SpreadsheetVersion;
import device.s.docreader.office.fc.util.HexDump;
import device.s.docreader.office.fc.util.LittleEndianOutput;


/**
 * Title:        SHAREDFMLA (0x04BC) SharedFormulaRecord
 * Description:  Primarily used as an excel optimization so that multiple similar formulas
 *               are not written out too many times.  We should recognize this record and
 *               serialize as is since this is used when reading templates.
 * <p>
 * Note: the documentation says that the SID is BC where biffviewer reports 4BC.  The hex dump shows
 * that the two byte sid representation to be 'BC 04' that is consistent with the other high byte
 * record types.
 * @author Danny Mui at apache dot org
 */
public final class SharedFormulaRecord extends SharedValueRecordBase {
    public final static short   sid = 0x04BC;

    private int field_5_reserved;
    private Formula field_7_parsed_expr;

    // for testing only
    public SharedFormulaRecord() {
        this(new CellRangeAddress8Bit(0,0,0,0));
    }
    private SharedFormulaRecord(CellRangeAddress8Bit range) {
        super(range);
        field_7_parsed_expr = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    /**
     * @param in the RecordInputstream to read the record from
     */
    public SharedFormulaRecord(RecordInputStream in) {
        super(in);
        field_5_reserved        = in.readShort();
        int field_6_expression_len = in.readShort();
        int nAvailableBytes = in.available();
        field_7_parsed_expr = Formula.read(field_6_expression_len, in, nAvailableBytes);
    }

    protected void serializeExtraData(LittleEndianOutput out) {
        out.writeShort(field_5_reserved);
        field_7_parsed_expr.serialize(out);
    }

    protected int getExtraDataSize() {
        return 2 + field_7_parsed_expr.getEncodedSize();
    }

    /**
     * print a sort of string representation ([SHARED FORMULA RECORD] id = x [/SHARED FORMULA RECORD])
     */

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("[SHARED FORMULA (").append(HexDump.intToHex(sid)).append("]\n");
        buffer.append("    .range      = ").append(getRange().toString()).append("\n");
        buffer.append("    .reserved    = ").append(HexDump.shortToHex(field_5_reserved)).append("\n");

        Ptg[] ptgs = field_7_parsed_expr.getTokens();
        for (int k = 0; k < ptgs.length; k++ ) {
           buffer.append("Formula[").append(k).append("]");
           Ptg ptg = ptgs[k];
           buffer.append(ptg.toString()).append(ptg.getRVAType()).append("\n");
        }

        buffer.append("[/SHARED FORMULA]\n");
        return buffer.toString();
    }

    public short getSid() {
        return sid;
    }

    /**
     * @return the equivalent {@link Ptg} array that the formula would have, were it not shared.
     */
    public Ptg[] getFormulaTokens(FormulaRecord formula) {
        int formulaRow = formula.getRow();
        int formulaColumn = formula.getColumn();
        //Sanity checks
        if (!isInRange(formulaRow, formulaColumn)) {
            throw new RuntimeException("Shared Formula Conversion: Coding Error");
        }

        SharedFormula sf = new SharedFormula(SpreadsheetVersion.EXCEL97);
        return sf.convertSharedFormulas(field_7_parsed_expr.getTokens(), formulaRow, formulaColumn);
    }

    public Object clone() {
        SharedFormulaRecord result = new SharedFormulaRecord(getRange());
        result.field_5_reserved = field_5_reserved;
        result.field_7_parsed_expr = field_7_parsed_expr.copy();
        return result;
    }
	public boolean isFormulaSame(SharedFormulaRecord other) {
		return field_7_parsed_expr.isSame(other.field_7_parsed_expr);
	}
}
