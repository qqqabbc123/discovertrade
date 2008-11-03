package org.discover.trading.io.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.discover.trading.core.IQuote;
import org.discover.trading.io.IQuoteReader;


public class QuoteConvertor {
	// Interval in seconds
	private int interval = 0;
	// Quotes to be converted
	private List<IQuote> quotes;
	// Quote class that needs to be created
	private Class<? extends IQuote> bean;

	public QuoteConvertor(List<IQuote> quotes, Class<? extends IQuote> bean,
			int interval) {
		this.interval = interval;
		this.bean = bean;
		this.quotes = quotes;
	}

	public QuoteConvertor(IQuoteReader reader, Class<? extends IQuote> bean,
			int interval) throws Exception {
		this.interval = interval;
		this.bean = bean;
		this.quotes = reader.readAll();
	}

	public List<IQuote> convert() throws InstantiationException, IllegalAccessException {
		List<IQuote> convertedQuotes = new ArrayList<IQuote>();

		// High, low will be continuously  
		Double high = 0.0;
		Double low = 0.0;
		
		// Quotes to be converted
		ListIterator<IQuote> iterator = quotes.listIterator();
		
		// Previous quote
		IQuote startQuote = null, endQuote = null; 
		while (iterator.hasNext()) {
			IQuote currQuote = iterator.next();
			if (iterator.previousIndex() == 0) {
				startQuote = currQuote;
				high = currQuote.getOpen();
				low = currQuote.getOpen();
			}

			high = high < currQuote.getOpen() ? currQuote.getOpen() : high;
			low = low > currQuote.getOpen() ? currQuote.getOpen() : low;
			
			
			if (((currQuote.getDate().getTime() - startQuote.getDate().getTime()) / 1000) > interval) {
				endQuote = currQuote;
				// write the bean here
				IQuote quote =  (IQuote)bean.newInstance();

				quote.setDate(endQuote.getDate());
				quote.setOpen(startQuote.getOpen());
				quote.setClose(endQuote.getOpen());
				quote.setHigh(high);
				quote.setLow(low);
				quote.setVolume(endQuote.getVolume() - startQuote.getVolume());
				convertedQuotes.add(quote);
				System.out.println(quote);
			
				startQuote = endQuote;				
			}
		}
		return quotes;
	}
}
