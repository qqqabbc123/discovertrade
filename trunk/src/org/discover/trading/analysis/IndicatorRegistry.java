package org.discover.trading.analysis;
import java.util.TreeMap;

import org.discover.trading.core.Security;

public class IndicatorRegistry {

    private TreeMap<Security, Indicator> mSecurityMap = new TreeMap<Security, Indicator>();
	public void add(Security security, Indicator indicator) {
		mSecurityMap.put(security, indicator);
	}
    public static Indicator getIndicator(Security security, int indicatorType, int period) {
		// TODO Auto-generated method stub
		return null;
	}
	public void remove(Security security, Indicator indicator) {
		// TODO Auto-generated method stub
		
		
	}
	public void remove(Security security) {
		// TODO Auto-generated method stub		
	}

}
