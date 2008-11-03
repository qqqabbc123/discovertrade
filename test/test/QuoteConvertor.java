package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


class QuoteReader {
	// Complete file path with extension
	// Ex: "C:/Kushal/NiftyQuote.csv"
	private String filePath;

	static final CellProcessor[] inProcessors = new CellProcessor[] {
			new ParseDate("HH:mm:ss"), new ParseDouble(), new ParseDouble(),
			new ParseDouble(), new ParseDouble() };
	static final CellProcessor[] outProcessors = new CellProcessor[] {
			new ParseDate("dd-MMM-yyyy"), new ParseDouble(), new ParseDouble(),
			new ParseDouble(), new ParseDouble(), new ParseDouble(),
			new ParseDouble() };

	public static class ReadBean {
		Date time;
		Double price, open, high, low;

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
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

		public String toString() {
			// TODO Auto-generated method stub
			return getTime().toString() + ", " + getPrice() + ", " + getOpen()
					+ ", " + getHigh() + ", " + getLow();
		}

		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			ReadBean that = null;
			if (obj instanceof ReadBean) {
				that = (ReadBean) obj;
			} else
				return false;

			return this.getTime().equals(that.getTime())
					&& this.getPrice().equals(that.getPrice())
					&& this.getOpen().equals(that.getOpen())
					&& this.getHigh().equals(that.getHigh())
					&& this.getLow().equals(that.getLow());
		}
	}

	public static class WriteBean {
		Date date;
		Double open, high, low, close, volume;

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

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getDate().toString() + ", " + getOpen() + ", " + getHigh()
					+ ", " + getLow() + ", " + getClose() + ", " + getVolume();
		}
	}

	public QuoteReader(String filePath) {
		// TODO Auto-generated constructor stub
		this.filePath = filePath;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public ArrayList<ReadBean> parse() throws Exception {
		// Fetched Quotes
		ArrayList<ReadBean> quotes = new ArrayList<ReadBean>();

		// TODO Auto-generated method stub
		ICsvBeanReader inFile = new CsvBeanReader(new BufferedReader(
				new FileReader(filePath)), CsvPreference.EXCEL_PREFERENCE);
		try {
			final String[] header = inFile.getCSVHeader(true);
			ReadBean quote;
			// System.out.println(header[5]);
			//for (int i = 0; i < header.length; i++)
				//System.out.println(header[i] + ", ");
			ReadBean preQuote = null;

			while ((quote = (ReadBean) inFile.read(ReadBean.class, header,
					inProcessors)) != null) {
				if (preQuote != null && !quote.equals(preQuote)) {
					quotes.add(quote);
					//System.out.println(quote);
				}
				preQuote = quote;
			}
		} finally {
			inFile.close();
		}
		return quotes;
	}

}

public class QuoteConvertor {
	static final CellProcessor[] outProcessors = new CellProcessor[] {
			new ParseDate("dd-MMM-yyyy"), new ParseDouble(), new ParseDouble(),
			new ParseDouble(), new ParseDouble(), new ParseDouble() };

	static final int INTERVAL = 60;

	public static void main(String[] args) {

		QuoteReader convertor = new QuoteReader("NIFTY.csv");
		List<QuoteReader.ReadBean> quotes = null;
		List<QuoteReader.WriteBean> outQuotes = new ArrayList<QuoteReader.WriteBean>();
		try {
			quotes = convertor.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			ICsvBeanWriter writer = new CsvBeanWriter(new BufferedWriter(
					new FileWriter("NIFTYConverted2.csv")),
					CsvPreference.EXCEL_PREFERENCE);
			String[] headers = { "Date", "Open", "High", "Low", "Close", "Volume" };
			writer.writeHeader(headers);
			ListIterator<QuoteReader.ReadBean> iterator = quotes.listIterator();
			QuoteReader.ReadBean prevBean = null;
			long startTime = 0;
			Double open = 0.0;
			Double high = 0.0;
			Double low = 0.0;
			Double close = 0.0;
			Date date = Calendar.getInstance().getTime();
			Double volume = new Double(0);
			boolean newInterval = false;
			while (iterator.hasNext()) {
				 
				QuoteReader.ReadBean readBean = iterator.next();
				if (iterator.previousIndex() == 0 || newInterval) {
					startTime = readBean.getTime().getTime();
					open = readBean.getPrice(); 
					high = readBean.getPrice();
					low  = readBean.getPrice();
					newInterval = false;
				}
				
				else {
					high = high < readBean.getPrice() ? readBean.getPrice() : high;
					low  = low  > readBean.getPrice() ? readBean.getPrice() : low;
				}
				long timeDiff = (readBean.getTime().getTime() - startTime) / 1000;
				if (timeDiff > INTERVAL) {
					close = prevBean.getPrice();
					
					// write the bean here 
					QuoteReader.WriteBean writeBean = new QuoteReader.WriteBean();
					
					writeBean.setOpen(open + 4000);
					writeBean.setClose(close + 4000);
					writeBean.setHigh(high + 4000);
					writeBean.setLow(low + 4000);
					writeBean.setVolume(volume);
					writeBean.setDate(date);
					writer.write(writeBean, headers);
					System.out.println(writeBean);
					
					newInterval = true;
				}
				
				prevBean = readBean;	
				

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
