package org.discover.trading.analysis;
import java.util.ArrayList;
import java.util.List;

import org.discover.trading.core.Security;


public class WatchList {
	String mName = null;
	List<Security> mSecurities = new ArrayList<Security>();
	IndicatorRegistry mIndicators = new IndicatorRegistry();
	void addSecurity(Security security) {
		mSecurities.add(security);
	}
	
	void removeSecurity(Security security) {
		mSecurities.remove(security);
		mIndicators.remove(security);
	}
	
	void addIndicator (Security security, Indicator indicator) {
		mIndicators.add(security, indicator);
	}
	
	void removeIndicator (Security security, Indicator indicator) {
		mIndicators.remove(security, indicator);
	}  
}
