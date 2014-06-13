package org.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.util.DownloadFund;

public class LineChart {
	static ChartFrame createLineChart(String[] fundNum) {
		XYDataset xydataset = createDataset(fundNum);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "", "",
				xydataset, true, true, true);
		jfreechart.setTitle(new TextTitle("", new Font("隶书", Font.ITALIC, 8)));
		jfreechart.setAntiAlias(true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		xyplot.setOutlineVisible(false);
		xyplot.setBackgroundPaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.lightGray);
		xyplot.setRangeGridlinesVisible(true);

		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xylineandshaperenderer.setShapesVisible(true);
		xylineandshaperenderer.setShapesFilled(true);
		xylineandshaperenderer.setShape(new Rectangle(4, 4));

		StandardXYToolTipGenerator tipGenerator = new StandardXYToolTipGenerator(
				"{1}:{2}", new SimpleDateFormat("yy年MM月dd"),
				NumberFormat.getNumberInstance());
		xylineandshaperenderer.setToolTipGenerator(tipGenerator);

		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("yy-MM-dd"));

		return new ChartFrame("基金历史净值", jfreechart);
	}

	private static XYDataset createDataset(String fundNum[]) {
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		for (int i = 0; i < fundNum.length; i++) {
			TimeSeries timeseries = new TimeSeries(fundNum[i], Day.class);
			File file = new File("基金历史净值/" + fundNum[i] + ".txt");
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException ex) {
				try {
					DownloadFund.downloadfundjz(fundNum[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "请正确输入！");
			}
			String s = null;
			int year = 0;
			int month = 0;
			int day = 0;
			double value = 0;
			try {
				while (!(s = br.readLine()).isEmpty()) {
					year = Integer.parseInt(s.substring(0, 4));// 年
					month = Integer.parseInt(s.substring(5, 7));// 月
					day = Integer.parseInt(s.substring(8, 10));// 日
					value = Double.parseDouble(s.substring(11, 17));// 净值
					timeseries.add(new Day(day, month, year), value);
				}
			} catch (IOException ex) {
				Logger.getLogger(LineChart.class.getName()).log(Level.SEVERE,
						null, ex);
			}

			timeseriescollection.addSeries(timeseries);
		}

		return timeseriescollection;
	}
}