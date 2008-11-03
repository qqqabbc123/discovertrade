package org.discover.trading.analysis;

import java.util.List;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;

public class URLQuoteReader implements IQuoteReader {
	// Security list to be watched...
	List<Security> securityList = null;
	
	public List<IQuote> parse() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void add(Security security) {
		securityList.add(security);
	}

	public void remove(Security security) {
		securityList.remove(security);
	}
}
