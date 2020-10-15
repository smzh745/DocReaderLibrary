package device.s.docreader.office.fc.ss.usermodel.charts;

import device.s.docreader.office.fc.ss.util.DataMarker;

import java.util.List;


/**
 * @author Roman Kashitsyn
 */
public interface ScatterChartData extends ChartData {
	/**
	 * @param xMarker data marker to be used for X value range
	 * @param yMarker data marker to be used for Y value range
	 * @return a new scatter chart serie
	 */
	ScatterChartSerie addSerie(DataMarker xMarker, DataMarker yMarker);

	/**
	 * @return list of all series
	 */
	List<? extends ScatterChartSerie> getSeries();
}
