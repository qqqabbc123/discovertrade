/**
 * 
 */
package org.discover.trading.core;

import java.util.Date;



/**
 * @author KUSHAL
 *	Interface representing an abstract quote.
 */
public interface IQuote {
	/**
	 *  Get date of the this quote.
	 */
	public Date 	getDate();
	/**
	 * Get opening price.
	 */
	public Double	getOpen();
	/**
	 *  Get high of the day.
	 */
	public Double	getHigh();
	/**
	 *  Get high of the day.
	 */
	public Double	getLow();
	/**
	 * Get closing price
	 */
	public Double getClose();
	/**
	 *  Get high of the day.
	 */
	public Integer	getVolume();
	
	/**
	 *  Set date of the this quote.
	 */
	public void 	setDate(Date date);
	/**
	 * Set opening price.
	 */
	public void	setOpen(Double open);
	/**
	 *  Set high of the day.
	 */
	public void	setHigh(Double high);
	/**
	 *  Set high of the day.
	 */
	public void	setLow(Double low);
	/**
	 * Set closing price
	 */
	public void setClose(Double close);
	/**
	 *  Set high of the day.
	 */
	public void	setVolume(Integer volume);
	
}
