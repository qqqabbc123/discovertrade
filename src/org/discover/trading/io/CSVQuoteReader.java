package org.discover.trading.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.LiveQuote;
import org.discover.trading.core.Security;
import org.discover.trading.core.SimpleQuote;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.exception.SuperCSVReflectionException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

/**
 * This class reads a csv file row by row and converts it to the bean class in
 * this case its just the quote. This class wont read duplicate quotes.
 */
public class CSVQuoteReader extends AbstractQuoteReader {

	// Path of the csv file
	private String filePath;
	// Cell processor to verify the integrity of the file
	private CellProcessor[] processors;
	// Bean class
	private Class beanClass;
	// CSV file reader
	private ICsvBeanReader reader;
	// Headers
	private String[] headers;
	// Previous quote read. This is kept to not to read duplicate quotes.
	private IQuote prevQuote;

	public CSVQuoteReader(Security security, String filePath,
			CellProcessor[] processors, Class<? extends IQuote> beanClass)
			throws IOException {
		super(security);
		this.filePath = filePath;
		this.processors = processors;
		this.beanClass = beanClass;
		// TODO Auto-generated constructor stub
		reader = new CsvBeanReader(
				new BufferedReader(new FileReader(filePath)),
				CsvPreference.EXCEL_PREFERENCE);
		headers = reader.getCSVHeader(true);
	}

	public CSVQuoteReader(Security security, String filePath,
			CellProcessor[] processors, Class<? extends IQuote> beanClass,
			String[] headers) throws IOException {
		this(security, filePath, processors, beanClass);
		this.headers = headers;
	}

	@Override
	public IQuote read() throws IOException {
		IQuote quote = null;
		do {
			quote = (IQuote) reader.read(beanClass, headers, processors);
			if (quote == null)
				break;
		} while (prevQuote != null && quote.equals(prevQuote));
		prevQuote = quote;
		return quote;
	}

	public List<IQuote> readAll() throws IOException {

		IQuote quote = null;
		List<IQuote> quotes = new ArrayList<IQuote>();
		while ((quote = read()) != null) {
			quotes.add(quote);
			//System.out.println(quote);
		}
		return quotes;
	}

	public static void main(String[] args) {
		CellProcessor[] processors = new CellProcessor[] {
				new ParseDate("HH:mm:ss"), new ParseDouble(),
				new ParseDouble(), new ParseDouble(), new ParseDouble(),
				new ParseInt() };

		try {
			IQuoteReader reader = new CSVQuoteReader(new Security(),
					"/media/disk/Users/KUSHAL/Documents/nifty3.csv",
					processors, SimpleQuote.class, new String[] { "Date",
							"Open", "High", "Low", "Close", "Volume" });
			IQuote quote = null;
			while ((quote = reader.read()) != null) {
				System.out.println(quote);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
