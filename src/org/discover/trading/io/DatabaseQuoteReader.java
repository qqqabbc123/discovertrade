package org.discover.trading.io;

import java.sql.Connection;
import java.util.List;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;

public class DatabaseQuoteReader extends org.discover.trading.io.AbstractQuoteReader {
	private Connection connection;
	public DatabaseQuoteReader(Security security, Connection connection) {
		super(security);
		this.connection = connection;
		// TODO Auto-generated constructor stub
	}

	@Override
	public IQuote read() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IQuote> readAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
