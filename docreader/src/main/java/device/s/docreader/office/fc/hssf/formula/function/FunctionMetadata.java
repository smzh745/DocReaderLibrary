package device.s.docreader.office.fc.hssf.formula.function;

/**
 * Holds information about Excel built-in functions.
 *
 * @author Josh Micich
 */
public final class FunctionMetadata {
	/**
	 * maxParams=30 in functionMetadata.txt means the maximum number arguments supported
	 * by the given version of Excel. Validation routines should take the actual limit (Excel 97 or 2007)
	 * from the SpreadsheetVersion enum.
	 * Perhaps a value like 'M' should be used instead of '30' in functionMetadata.txt
	 * to make that file more version neutral.
	 * @see device.s.docreader.office.fc.hssf.formula.FormulaParser#validateNumArgs(int, FunctionMetadata)
	 */
	private static final short FUNCTION_MAX_PARAMS = 30;

	private final int _index;
	private final String _name;
	private final int _minParams;
	private final int _maxParams;
	private final byte _returnClassCode;
	private final byte[] _parameterClassCodes;

	/* package */ FunctionMetadata(int index, String name, int minParams, int maxParams,
                                   byte returnClassCode, byte[] parameterClassCodes) {
		_index = index;
		_name = name;
		_minParams = minParams;
		_maxParams = maxParams;
		_returnClassCode = returnClassCode;
		_parameterClassCodes = parameterClassCodes;
	}
	public int getIndex() {
		return _index;
	}
	public String getName() {
		return _name;
	}
	public int getMinParams() {
		return _minParams;
	}
	public int getMaxParams() {
		return _maxParams;
	}
	public boolean hasFixedArgsLength() {
		return _minParams == _maxParams;
	}
	public byte getReturnClassCode() {
		return _returnClassCode;
	}
	public byte[] getParameterClassCodes() {
		return _parameterClassCodes.clone();
	}
	/**
	 * Some varags functions (like VLOOKUP) have a specific limit to the number of arguments that 
	 * can be passed.  Other functions (like SUM) don't have such a limit.  For those functions,
	 * the spreadsheet version determines the maximum number of arguments that can be passed.
	 * @return <code>true</code> if this function can the maximum number of arguments allowable by
	 * the {@link device.s.docreader.office.fc.ss.SpreadsheetVersion}
	 */
	public boolean hasUnlimitedVarags() {
		return FUNCTION_MAX_PARAMS == _maxParams;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer(64);
		sb.append(getClass().getName()).append(" [");
		sb.append(_index).append(" ").append(_name);
		sb.append("]");
		return sb.toString();
	}
}
