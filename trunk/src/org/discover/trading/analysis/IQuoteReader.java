/**
 * 
 */
package org.discover.trading.analysis;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.discover.trading.core.IQuote;

/**
 * @author KUSHAL
 *
 */
public interface IQuoteReader {
	public List<IQuote> parse() throws Exception;
}
