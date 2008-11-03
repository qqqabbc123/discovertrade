package org.discover.trading.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCSVReflectionException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVQuoteWriter extends AbstractQuoteWriter {

	// Path of the csv file
	private String filePath;
	// CSV file Writer
	private ICsvBeanWriter writer;
	// Headers
	private String[] headers;
	// Writer to write the quotes into the file.
		
	public CSVQuoteWriter(Security security, String filePath, String[] headers) throws IOException {
		super(security);
		this.filePath = filePath;
		this.headers  = headers;
		
		// Create the writer.
		writer = new CsvBeanWriter(new FileWriter(filePath), CsvPreference.EXCEL_PREFERENCE);
		writer.writeHeader(headers);
	}

	@Override
	public void write(IQuote quote) throws Exception {
		writer.write(quote, headers);		
		System.out.println(quote);
	}

	@Override
	public void writeAll(List<IQuote> quotes) throws Exception {
		Iterator<IQuote> iterator = quotes.iterator();
		while (iterator.hasNext())
			write(iterator.next());
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		writer.close();
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		writer.flush();
	}

}
