package org.discover.trading.io.util.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;
import org.discover.trading.core.SimpleQuote;
import org.discover.trading.io.CSVQuoteReader;
import org.discover.trading.io.IQuoteReader;
import org.discover.trading.io.util.QuoteConvertor;
import org.junit.Before;
import org.junit.Test;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class QuoteConvertorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConvert() {
		CellProcessor[] processors = new CellProcessor[] {
				new ParseDate("HH:mm:ss"), new ParseDouble(),
				new ParseDouble(), new ParseDouble(), new ParseDouble(),
				new ParseInt() };
		
		try {
			IQuoteReader reader = new CSVQuoteReader(new Security(),"/media/disk/Users/KUSHAL/Documents/nifty3.csv", processors, SimpleQuote.class,
													 new String[] {"Date", "Open", "High", "Low", "Close", "Volume"} );
			new QuoteConvertor(reader, SimpleQuote.class, 5).convert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
