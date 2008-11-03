package test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.discover.trading.analysis.MovingAverage;
import org.discover.trading.analysis.Quote;
import org.discover.trading.analysis.QuoteReader;
import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;
import org.discover.trading.transaction.Deductables;
import org.discover.trading.transaction.Transaction;
import org.discover.trading.transaction.TransactionManager;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.tictactec.ta.lib.Core;

public class TestStrategy {
	static int low = 5, high = 8;
	public static class Bean implements IQuote {
		Date date;
		Double open, high, low, close;
		Integer volume;

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

		public Integer getVolume() {
			return volume;
		}

		public void setVolume(Integer volume) {
			this.volume = volume;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getDate().toString() + ", " + getOpen() + ", " + getHigh()
					+ ", " + getLow() + ", " + getClose() + ", " + getVolume();
		}

	}
	public static void main(String[] args) {
		CellProcessor[] processors = new CellProcessor[] {
	    new StrMinMax(1, 40),
	    new StrMinMax(1, 35),
	    new ParseDate("dd-MMM-yy"),
	    new ParseDate("dd-MMM-yy"),
	    new ParseDouble(),
	    new ParseDouble(),
	    new ParseDouble(),
	    new ParseDouble(),
	    new ParseDouble(),
	    new ParseDouble(),
	    new Optional(new ParseInt()),
	    new ParseDouble(),
	    new Optional(new ParseInt()),
	    new Optional(new ParseInt())
	};
		
		QuoteReader reader = new QuoteReader<Quote>("final4.csv", Quote.class, processors);
		ArrayList<Quote> quotes = null;
		try {
			quotes = reader.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		List<Double> maLow  = new MovingAverage().calculate(quotes, low);
		List<Double> maHigh = new MovingAverage().calculate(quotes, high);
		
		boolean lowAbove = true;
		
		ListIterator<Double> maLowIterator = maLow.listIterator();
		ListIterator<Double> maHighIterator = maHigh.listIterator();
		int index = 0;
		double points = 0;
		System.out.println();
		
		Security security = new Security(); 
		Deductables deduct = new Deductables();
		int quantity = 1;
		Transaction prevTransaction = null;
		while (maLowIterator.hasNext()) {
			//System.out.println("Current Date:" + quotes.get(index).getDate());
			double lowMa  = maLowIterator.next().doubleValue();
			double highMa = maHighIterator.next().doubleValue();
			boolean trade = false;
			int tradeType = 0;
			
			//System.out.println("Current Date: " + quotes.get(index).getDate() + " LowMa: " + lowMa + " HighMa: " + highMa);
			if (lowAbove == true && lowMa < highMa) {
				//System.out.println("Short:" + quotes.get(index).getDate());
				lowAbove = false;
				tradeType = Transaction.SHORT;
				trade = true;
				}
			
			else if (lowAbove == false && lowMa > highMa) {
				lowAbove = true;
				tradeType = Transaction.LONG;
				trade = true;
				}
			
			if (trade) {
				// close previous trade;
				if (prevTransaction != null) {
					prevTransaction.end(quotes.get(index));
					double point = prevTransaction.getTransactionAmount();
					double stopLoss = -(prevTransaction.getStartPosition().getOpen() * 0.02);
					
					if (point < 0.0 && point < stopLoss ) {
						point = stopLoss;
					}
					points = points + point;
					//System.out.println(" Points earned: " + point);
					System.out.println(point);
				}
				
				// Start a new trade  
				Transaction transaction = new Transaction(security, deduct, tradeType);  
				transaction.start(quotes.get(index), quantity);
				prevTransaction = transaction;
			}
			
			index++;
		}
		System.out.println();
		System.out.println("Points gained: " + points);
		
	}
	
}
