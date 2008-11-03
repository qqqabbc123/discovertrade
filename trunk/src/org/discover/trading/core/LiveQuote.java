package org.discover.trading.core;

import java.util.Date;

public class LiveQuote extends AbstractQuote {
	private Double 	lastTradedPrice;
	private Date 	lastTradedTime;
	private Double	ask;
	private Integer askSize;
	private Double  bid;
	private Integer bidSize;
	
	public Double getLastTradedPrice() {
		return lastTradedPrice;
	}
	public void setLastTradedPrice(Double lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}
	public Date getLastTradedTime() {
		return lastTradedTime;
	}
	public void setLastTradedTime(Date lastTradedTime) {
		this.lastTradedTime = lastTradedTime;
	}
	public Double getAsk() {
		return ask;
	}
	public void setAsk(Double ask) {
		this.ask = ask;
	}
	public Integer getAskSize() {
		return askSize;
	}
	public void setAskSize(Integer askSize) {
		this.askSize = askSize;
	}
	public Double getBid() {
		return bid;
	}
	public void setBid(Double bid) {
		this.bid = bid;
	}
	public Integer getBidSize() {
		return bidSize;
	}
	public void setBidSize(Integer bidSize) {
		this.bidSize = bidSize;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 	getDate().toString() + ", " + getOpen() + ", " + getHigh() + ", " + getLow() + ", " + getVolume() + ", " + 
				getLastTradedPrice() + ", " + getLastTradedTime() + ", " + getAsk() + ", " + getAskSize() + ", " + 
				getBid() + ", " + getBidSize();
	}
}
