/**
 * 
 */
package org.discover.trading.analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.discover.trading.core.IQuote;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

/**
 * @author KUSHAL
 *
 */
public class CSVQuoteReader implements IQuoteReader {

	// File path where quotes are.
	String fileLocation = null;
	// File reader.
	Reader reader = null;
	// Bean class.
	Class beanClass = null;
	
	private CellProcessor[] processors = new CellProcessor[] {
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
	
	public CSVQuoteReader(Reader reader, Class beanClass) {
		this.reader = reader;
		this.beanClass = beanClass;
	}
	
	public CSVQuoteReader(String fileLocation) {
		this.fileLocation = fileLocation;
		
		// Open file
		try {
			reader = new FileReader(fileLocation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<IQuote> parse() throws Exception {
		// Fetched Quotes
		List<IQuote> quotes = new ArrayList<IQuote>();
		
		// TODO Auto-generated method stub
		ICsvBeanReader inFile = new CsvBeanReader(new BufferedReader(reader), CsvPreference.EXCEL_PREFERENCE);
	    try {
	      final String[] header = inFile.getCSVHeader(true);
	      IQuote quote;
	      int i = 0;
	      while( (quote = (IQuote) inFile.read(beanClass, header, processors)) != null) {
	      	quotes.add(quote);
	      }
	    } finally {
	      inFile.close();
	    }
	    return quotes;
	}
}
