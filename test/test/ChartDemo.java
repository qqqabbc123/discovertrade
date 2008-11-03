package test;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.discover.charts.CandleStickChart;
import org.discover.trading.analysis.QuoteReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.ui.RefineryUtilities;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class ChartDemo {

	static final CellProcessor[] processors = new CellProcessor[] {
			new ParseDate("dd-MM-yyyy"), new ParseDouble(), new ParseDouble(),
			new ParseDouble(), new ParseDouble(), new ParseDouble(), };

	public static class Bean {
		Date date;
		private Double open, high, low, close, volume;
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Double getOpen() {
			return open;
		}

		public void setOpen(Double open) {
			this.open = open;
		}

		public Double getHigh() {
			return high;
		}

		public void setHigh(Double high) {
			this.high = high;
		}

		public Double getLow() {
			return low;
		}

		public void setLow(Double low) {
			this.low = low;
		}

		public Double getClose() {
			return close;
		}

		public void setClose(Double close) {
			this.close = close;
		}

		public Double getVolume() {
			return volume;
		}

		public void setVolume(Double volume) {
			this.volume = volume;
		}
		
		public String toString() {
			// TODO Auto-generated method stub
			return getDate() + ", " + getOpen() + ", " + getHigh() + ", " + getLow() + ", " + getClose() + ", " + getVolume();
		}
		

	}

	public static void main(String[] args) {
		try {
			List<ChartDemo.Bean> quotes = new QuoteReader<Bean>(
					"NIFTYConverted.csv", Bean.class, processors).parse();
			Date[]  dates    =  new Date[quotes.size()]; 
			double[] highs   =  new double[quotes.size()];
			double[] lows    =  new double[quotes.size()];
			double[] opens   =  new double[quotes.size()];
			double[] closes  =  new double[quotes.size()];
			double[] volumes =  new double[quotes.size()];
			
			Iterator<ChartDemo.Bean> iterator = quotes.iterator();
			OHLCDataItem[] dataItems = new OHLCDataItem[quotes.size()];
			for (int i = 0; iterator.hasNext() ; i++) {
				Bean quote = iterator.next();
				dataItems[i] = new OHLCDataItem(quote.getDate(), quote.getOpen(), quote.getHigh(), quote.getLow(), quote.getClose(), quote.getVolume());
				dates[i]   = quote.getDate();
				opens[i]   = quote.getOpen();
				highs[i]   = quote.getHigh();
				lows[i]	   = quote.getLow();	
				closes[i]  = quote.getClose();
				volumes[i] = quote.getVolume();
			}
			
			DefaultOHLCDataset dataset = new DefaultOHLCDataset("", dataItems); 
			JFreeChart 	chart = ChartFactory.createCandlestickChart("NIFTY", "Date", "Price", dataset, true);  //new CandleStickChart("NIFTY", dataset);
			XYPlot plot = chart.getXYPlot();
			ChartFrame frame = new ChartFrame("Test", chart);
			ChartPanel panel = (ChartPanel)frame.getContentPane(); 
			panel.setMouseZoomable(true, true);
			CandleStickChart charta;
			frame.getContentPane().setPreferredSize(new java.awt.Dimension(500*3, 270*3));
			TickUnitSource units = NumberAxis.createIntegerTickUnits();
			DateAxis axis = (DateAxis) plot.getDomainAxis();
			
			panel.setAutoscrolls(true);
			//axis.setTickUnit(new DateTickUnit(1,7));
			NumberAxis axis1 = (NumberAxis)plot.getRangeAxis();
			CandlestickRenderer render = (CandlestickRenderer)plot.getRenderer();
			
			render.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_SMALLEST);
			render.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
			//XYItemRenderer renderer = new HighLowRenderer();
			//plot.setRenderer(renderer);
			render.setCandleWidth(5);
			axis1.setRange(4300, 4400);
			axis.setRange(quotes.get(0).getDate(), quotes.get(dataItems.length - 1).getDate());
			chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
