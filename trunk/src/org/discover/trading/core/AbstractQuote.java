package org.discover.trading.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractQuote implements IQuote {
	private Date date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Integer volume;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		return date.format(getDate()) + ", " + getOpen() + ", " + getHigh() + ", " +
			   getLow() + ", " + getClose() + ", " + getVolume();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractQuote) {
			return ((AbstractQuote)(obj)).getDate().equals(getDate());
		}		
		return false;
	}
}
