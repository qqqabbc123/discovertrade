package org.discover.trading.io;

import java.io.IOException;
import java.util.List;

import org.discover.trading.core.IQuote;

public interface IQuoteWriter {
	void write(IQuote quote) throws Exception;
	void writeAll(List<IQuote> quotes) throws Exception;
	void flush() throws IOException;
	void close() throws Exception;
}
