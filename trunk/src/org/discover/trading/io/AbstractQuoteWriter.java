package org.discover.trading.io;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;

public abstract class AbstractQuoteWriter implements IQuoteWriter {

	private Security security;
	public AbstractQuoteWriter(Security security) {
		this.security = security;
	}
	

}
