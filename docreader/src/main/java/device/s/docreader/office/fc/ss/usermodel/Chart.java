package device.s.docreader.office.fc.ss.usermodel;

import device.s.docreader.office.fc.ss.usermodel.charts.ChartAxis;
import device.s.docreader.office.fc.ss.usermodel.charts.ChartAxisFactory;
import device.s.docreader.office.fc.ss.usermodel.charts.ChartData;
import device.s.docreader.office.fc.ss.usermodel.charts.ChartDataFactory;
import device.s.docreader.office.fc.ss.usermodel.charts.ChartLegend;
import device.s.docreader.office.fc.ss.usermodel.charts.ManuallyPositionable;

import java.util.List;


/**
 * High level representation of a chart.
 *
 * @author Roman Kashitsyn
 */
public interface Chart extends ManuallyPositionable {
	
	/**
	 * @return an appropriate ChartDataFactory implementation
	 */
	ChartDataFactory getChartDataFactory();

	/**
	 * @return an appropriate ChartAxisFactory implementation
	 */
	ChartAxisFactory getChartAxisFactory();

	/**
	 * @return chart legend instance
	 */
	ChartLegend getOrCreateLegend();

	/**
	 * Delete current chart legend.
	 */
	void deleteLegend();

	/**
	 * @return list of all chart axis
	 */
	List<? extends ChartAxis> getAxis();

	/**
	 * Plots specified data on the chart.
	 *
	 * @param data a data to plot
	 */
	void plot(ChartData data, ChartAxis... axis);
}
