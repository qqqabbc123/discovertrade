package org.discover.trading.analysis;

import org.discover.trading.core.Security;

/**
 * 
 * @author kushals
 *
 */
public interface IStrategy {
	/**
	 * Emits buy signal
	 */ 
	void buy();
	
	/**
	 * Emits sell signal
	 */
	void sell();
	
	/**
	 * Calculate the trend and emit corresponding signal
	 */
	void calculate(Security security);
}
