package org.discover.trading.analysis;

public interface Indicator {

	static final int SMA = 1;
	static final int ADX = 2;
	double getLastPrice();

}
