package device.s.docreader.office.fc.hssf.formula.udf;


import device.s.docreader.office.fc.hssf.formula.atp.AnalysisToolPak;
import device.s.docreader.office.fc.hssf.formula.function.FreeRefFunction;


/**
 * Common interface for "Add-in" libraries and user defined function libraries.
 *
 * @author PUdalau
 */
public interface UDFFinder {
	public static final UDFFinder DEFAULT = new AggregatingUDFFinder(AnalysisToolPak.instance);

	/**
	 * Returns executor by specified name. Returns <code>null</code> if the function name is unknown.
	 *
	 * @param name Name of function.
	 * @return Function executor.
	 */
	FreeRefFunction findFunction(String name);
}
