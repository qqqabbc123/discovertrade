package org.discover.trading.io;

import java.io.IOException;
import java.util.List;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;

public class DatabaseQuoteWriter extends AbstractQuoteWriter {

	public DatabaseQuoteWriter(Security security) {
		super(security);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(IQuote quote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeAll(List<IQuote> quotes) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub		
	}

}
