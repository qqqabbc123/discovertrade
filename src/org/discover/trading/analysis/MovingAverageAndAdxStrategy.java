package org.discover.trading.analysis;

import org.discover.trading.core.Security;

/**
 * 
 */

/**
 * @author kushals
 * 
 */
public class MovingAverageAndAdxStrategy implements IStrategy {

	private boolean MA5AboveMA20 = false;

	public void buy() {
		// TODO Auto-generated method stub
	}

	public void calculate(Security security) {
		// TODO Auto-generated method stub
		Indicator MA5 = IndicatorRegistry.getIndicator(security, Indicator.SMA,
				5);
		Indicator MA20 = IndicatorRegistry.getIndicator(security,
				Indicator.SMA, 20);
		Indicator Adx14 = IndicatorRegistry.getIndicator(security,
				Indicator.ADX, 14);

		if (MA5.getLastPrice() < MA20.getLastPrice()) {
			if (MA5AboveMA20 == true)
				sell();
			MA5AboveMA20 = false;
		}

		else {
			if (MA5AboveMA20 == false)
				buy();
			MA5AboveMA20 = true;
		}
	}

	public void sell() {
		// TODO Auto-generated method stub

	}
}
