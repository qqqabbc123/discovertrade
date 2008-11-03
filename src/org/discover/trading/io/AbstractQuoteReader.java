/**
 * 
 */
package org.discover.trading.io;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;

/**
 * @author KUSHAL
 *
 */
public abstract class AbstractQuoteReader implements IQuoteReader {
	private Security security = null;
	public AbstractQuoteReader(Security security) {
		this.security = security;
	}
}
