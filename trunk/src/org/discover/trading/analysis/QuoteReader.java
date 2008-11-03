package org.discover.trading.analysis;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;


public class QuoteReader <T> {
	// Complete file path with extension
	// Ex: "C:/Kushal/NiftyQuote.csv"
	private String filePath;
	private Class beanClass;
	private CellProcessor[] processors;
	
	public QuoteReader(String filePath, Class<T> beanClass, CellProcessor[] processors) {
		// TODO Auto-generated constructor stub
		this.filePath = filePath;
		this.beanClass = beanClass;
		this.processors = processors;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public ArrayList<T> parse() throws Exception {
		// Fetched Quotes
		ArrayList<T> quotes = new ArrayList<T>();
		
		// TODO Auto-generated method stub
		ICsvBeanReader inFile = new CsvBeanReader(new BufferedReader( new FileReader(filePath)), CsvPreference.EXCEL_PREFERENCE);
	    try {
	      final String[] header = inFile.getCSVHeader(true);
	      T obj;
	      //System.out.println(header[5]);
	      //for (int i = 0; i < header.length; i++) 
	    	  //System.out.print(header[i] + ", ");
	      int i = 0;
	      while( (obj = (T)inFile.read(beanClass, header, processors)) != null) {
	        quotes.add(obj);
	        System.out.println(obj);
	        }
	    } finally {
	      inFile.close();
	    }
	    return quotes;
	  }
}
