package org.discover.trading.io.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.discover.trading.core.Security;
import org.discover.trading.core.SimpleQuote;
import org.discover.trading.io.CSVQuoteReader;
import org.discover.trading.io.CSVQuoteWriter;
import org.discover.trading.io.IQuoteWriter;
import org.discover.trading.io.util.QuoteConvertor;
import org.junit.Before;
import org.junit.Test;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CSVQuoteWriterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCSVQuoteWriter() {
		fail("Not yet implemented");
	}

	@Test
	public void testWrite() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteAll() {
		CellProcessor[] processors = new CellProcessor[] {
				new ParseDate("HH:mm:ss"), new ParseDouble(),
				new ParseDouble(), new ParseDouble(), new ParseDouble(),
				new ParseInt() };
		String[] headers = new String[] {"Date", "Open", "High", "Low", "Close", "Volume"};  
		IQuoteWriter writer = null;
		try {
			(writer = new CSVQuoteWriter(null,  "/tmp/nifty.csv", headers)).writeAll(
					new QuoteConvertor(	new CSVQuoteReader(new Security(), "/media/disk/Users/KUSHAL/Documents/nifty3.csv", processors, 
							SimpleQuote.class, headers), SimpleQuote.class, 60).convert());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

}
