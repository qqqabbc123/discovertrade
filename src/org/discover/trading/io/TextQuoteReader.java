package org.discover.trading.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.LiveQuote;
import org.discover.trading.core.RegexQuoteTemplate;
import org.discover.trading.core.Security;

public class TextQuoteReader extends AbstractQuoteReader {

	private String source;
	private RegexQuoteTemplate template;
	private IQuote prevQuote = null;
	public TextQuoteReader(Security security, String source, RegexQuoteTemplate template) {
		super(security);
		this.source = source;
		this.template = template;		
	}

	public IQuote read(String source) throws Exception {
		this.source = source;
		return read();
	}
	@Override
	public IQuote read() throws Exception {
		LiveQuote quote = new LiveQuote();
		// Iterate thru the set and create the bean.
		Iterator<String> iterator = template.getFields().iterator();
		while(iterator.hasNext()) {
			String field = iterator.next();
			String pattern = template.getPattern(field);
			Matcher matcher = Pattern.compile(pattern, Pattern.MULTILINE).matcher(source);
			int index = 0;
			if (matcher.find(index)) {
				String match = matcher.group(1);
				match = match.equals("NA") ? "0" : match;
				match = match.replace(",", "");
				match = match.replace(",", "");
				if (field.equals("date")) {
					try {
						quote.setDate(new SimpleDateFormat(template.getDateFormat()).parse(match));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (field.equals("open")) {
						quote.setOpen(Double.parseDouble(match));
						quote.setClose(0.0);
				} else if (field.equals("high")) {
					quote.setHigh(Double.parseDouble(match));	
				} else if (field.equals("low")) {
					quote.setLow(Double.parseDouble(match));	
				} else if (field.equals("volume")) {
					quote.setVolume(Integer.parseInt(match));
				} else if (field.equals("lastTradedPrice")) {
					quote.setLastTradedPrice(Double.parseDouble(match));
					quote.setOpen(Double.parseDouble(match));
				} else if (field.equals("lastTradedTime")) {
					try {
						quote.setLastTradedTime(new SimpleDateFormat("HH:mm:ss").parse(match));
						quote.setDate(new SimpleDateFormat("HH:mm:ss").parse(match));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (field.equals("ask")) {
					quote.setAsk(Double.parseDouble(match));	
				} else if (field.equals("askSize")) {
					quote.setAskSize(Integer.parseInt(match));
				} else if (field.equals("bid")) {
					quote.setBid(Double.parseDouble(match));
				} else if (field.equals("bidSize")) {
					quote.setBidSize(Integer.parseInt(match));
				} 				
			}
		}
			if (prevQuote == null || (prevQuote != null && !prevQuote.equals(quote)) ) {
				prevQuote = quote;
				return quote;
			}
			else
				return null;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IQuote> readAll() throws Exception {
		List<IQuote> quotes = null;
		IQuote quote = read();
		if (quote != null) {
			quotes = new ArrayList<IQuote>();
			quotes.add(quote);
		}
		return quotes;
	}

}
