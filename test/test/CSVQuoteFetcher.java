package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;
import org.discover.trading.core.SimpleQuote;
import org.discover.trading.io.CSVQuoteReader;
import org.discover.trading.io.CSVQuoteWriter;
import org.discover.trading.io.IQuoteReader;
import org.discover.trading.io.IQuoteWriter;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CSVQuoteFetcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CellProcessor[] processors = new CellProcessor[] {
				new ParseDate("HH:mm:ss"), new ParseDouble(),
				new ParseDouble(), new ParseDouble(), new ParseDouble(),
				new ParseInt() };
		String[] headers = new String[] {"Date", "Open", "High", "Low", "Close", "Volume"};
		PrintWriter  writer = null;
		
		IQuoteReader reader = null;
		
		try {
			reader = new CSVQuoteReader(new Security(), "/root/Desktop/Nifty.csv", processors, 
					SimpleQuote.class, headers);
			writer = new PrintWriter(new FileWriter("/root/Desktop/NiftyConverted.csv"));
			IQuote quote = null;
			writer.println(Arrays.toString(headers));
			while ((quote = reader.read()) != null) {
				System.out.println(quote);
				Thread.sleep(5000);
				writer.println(quote);
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
