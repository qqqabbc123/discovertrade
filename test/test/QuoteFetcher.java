package test;

import java.io.BufferedInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.RegexQuoteTemplate;
import org.discover.trading.core.Security;
import org.discover.trading.io.CSVQuoteWriter;
import org.discover.trading.io.IQuoteWriter;
import org.discover.trading.io.TextQuoteReader;

public class QuoteFetcher {

	private static RegexQuoteTemplate iciciDirectTemplate = null;
	private static long waitTime;
	private static String stockSymbol = "NIFTY";
	private static String url = "http://getquote.icicidirect.com/trading/equity/trading_stock_quote.asp?Symbol=NIFTY";
	static {
		// Create the the regex template for the quote reader.
		iciciDirectTemplate = new RegexQuoteTemplate("dd-MMM-yyyy");
		Map<String, String> fieldPatternMap = new TreeMap<String, String>();
		String tagStart = "<\\w+[^>]*>";
		String tagEnd = "</\\w+[^>]*>";
		String value = "(\\w+[^<]*)";
		String[] fields = new String[] { "date", "open", "high", "low",
				"volume", "lastTradedPrice", "lastTradedTime", "ask",
				"askSize", "bid", "bidSize" };
		String[] tagValues = new String[] { "DATE", "DAY OPEN", "DAY HIGH",
				"DAY LOW", "DAY VOLUME", "LAST TRADE PRICE",
				"LAST TRADED TIME", "BEST OFFER PRICE", "BEST OFFER QTY",
				"BEST BID PRICE", "BEST BID QTY" };
		for (int i = 0; i < fields.length; i++) {
			iciciDirectTemplate.put(fields[i], tagStart + tagValues[i] + tagEnd
					+ "\\s*+" + tagStart + value + tagEnd);
		}
	}
	
	static void run() {

		// If market is not yet open just sleep over
		while (!isMartketOpen()) {
			try {
				System.out.println("Market not open will wait for " + waitTime / 1000 + " seconds");
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Create a new csv writer to write all the data.
		PrintWriter quoteWriter = null;
		try {
			String[] headers = new String[] {"Date", "Open", "High", "Low", "Close", "Volume"};
			quoteWriter = new PrintWriter(new FileWriter(stockSymbol + " " + Calendar.getInstance().getTime().toString()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		// previous quote.
		TextQuoteReader quoteReader = new TextQuoteReader(new Security(),
				null, iciciDirectTemplate);
		while (!isMarketClosed()) {
			BufferedInputStream reader = null;
			try {
				URLConnection connection = new URL(url).openConnection();
				reader = new BufferedInputStream(connection.getInputStream());
				connection.setReadTimeout(10000);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			int c = 0;
			String source = "";
			try {
				while ((c = reader.read()) != -1)
					source = source + (char) c;
				
				try {
					IQuote quote = quoteReader.read(source);
					quoteWriter.println("");
					if (quote != null) {
						quoteWriter.println(quote);
						quoteWriter.flush();
					}
					System.out.println(quote);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			run();
		}
	}

	private static boolean isMarketClosed() {
		int hour   = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		
		if ((hour == 15 && minute >= 35) || hour > 15)
			return true;
		else 
			return false;
	}

	private static boolean isMartketOpen() {
		// Start time.
		int hour   = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int day    = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);	
		
		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY ) {
			waitTime = 8 * 60 * 60 * 1000; 
			return false;
		}
		
		// If the hour is less than 9 o clock
		if (hour < 9 || hour > 16) {
			waitTime = 50 * 60 * 1000; 
			return false;
		}
		
		else if ((hour == 9 && minute >= 50) || hour > 9)
			return true;
		
		else {
			waitTime = 10 * 1000;
			return false;
		}
		
	}

}
