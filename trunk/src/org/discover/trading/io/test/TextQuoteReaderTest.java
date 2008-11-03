package org.discover.trading.io.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.discover.trading.core.RegexQuoteTemplate;
import org.discover.trading.core.Security;
import org.discover.trading.io.IQuoteReader;
import org.discover.trading.io.TextQuoteReader;
import org.junit.Before;
import org.junit.Test;

public class TextQuoteReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRead() {
		// Read the html source from the file.
		BufferedReader reader = null;
		String l;
		String source = null;
		try {
			reader = new BufferedReader(new FileReader("/root/Download/nifty.txt"));
			while ((l = reader.readLine()) != null)
				source += l;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Create the the regex template for the quote reader.
		RegexQuoteTemplate iciciDirectTemplate = new RegexQuoteTemplate("dd-MMM-yyyy");
		Map<String, String> fieldPatternMap = new TreeMap<String, String>();
		String tagStart = "<\\w+[^>]*>";
		String tagEnd   = "</\\w+[^>]*>";
		String value    = "(\\w+[^<]*)";
		String[] fields = new String[]{	"date", "open", "high", "low", "volume", "lastTradedPrice", 
										"lastTradedTime", "ask", "askSize", "bid", "bidSize"};
		String[] tagValues   = new String[]{"DATE", "DAY OPEN", "DAY HIGH", "DAY LOW", "DAY VOLUME", "LAST TRADE PRICE", 
											"LAST TRADED TIME", "BEST OFFER PRICE", "BEST OFFER QTY", "BEST BID PRICE", "BEST BID QTY" 
											};
		for (int i = 0; i < fields.length; i++) {
			iciciDirectTemplate.put(fields[i], tagStart + tagValues[i] + tagEnd + "\\s*+" + tagStart + value  + tagEnd);
		}
		
		IQuoteReader textReader = new TextQuoteReader(new Security(), source, iciciDirectTemplate);
		try {
			for (int i = 0; i < 10; i++)
				System.out.println(textReader.read());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
