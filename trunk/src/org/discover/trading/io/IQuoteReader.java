/**
 * 
 */
package org.discover.trading.io;

import java.io.IOException;
import java.util.List;

import org.discover.trading.core.IQuote;


/**
 * @author KUSHAL
 *
 */
public interface IQuoteReader {
	IQuote read() throws Exception;
	public List<IQuote> readAll() throws Exception;
	void close() throws Exception;
}
