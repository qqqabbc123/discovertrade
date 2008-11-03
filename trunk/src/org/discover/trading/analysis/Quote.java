package org.discover.trading.analysis;
import java.util.Date;


public class Quote {
	private String Instrument;
	private String Symbol;
	private Date date;
	private Date Expiry;
	private double Open;
	private double High;
	private double Low;
	private double Close;
	private double LTP;
	private double Settle_Price;	
	private int No_of_contracts;
	private double Turnover_in_lacs;
	private int Open_Int;
	private int Change_in_OI;
	
	public String toString() {
		return 	getInstrument()  + ", " + getSymbol()  + ", " + getDate()  + ", " + getExpiry()  + ", " +
				getOpen()  + ", " + getHigh()  + ", " + getLow()  + ", " + getClose()  + ", " + getLTP() + ", " +
				getSettle_Price() + ", " + getNo_of_contracts() + ", " + getTurnover_in_lacs() + ", " + getOpen_Int()  + ", " +
				getChange_in_OI();
	}
	
	public int getChange_in_OI() {
		return Change_in_OI;
	}
	public void setChange_in_OI(int change_in_OI) {
		Change_in_OI = change_in_OI;
	}
	public double getClose() {
		return Close;
	}
	public void setClose(double close) {
		Close = close;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getExpiry() {
		return Expiry;
	}
	public void setExpiry(Date expiry) {
		Expiry = expiry;
	}
	public double getHigh() {
		return High;
	}
	public void setHigh(double high) {
		High = high;
	}
	public String getInstrument() {
		return Instrument;
	}
	public void setInstrument(String instrument) {
		Instrument = instrument;
	}
	public double getLow() {
		return Low;
	}
	public void setLow(double low) {
		Low = low;
	}
	public double getLTP() {
		return LTP;
	}
	public void setLTP(double ltp) {
		LTP = ltp;
	}
	public int getNo_of_contracts() {
		return No_of_contracts;
	}
	public void setNo_of_contracts(int no_of_contracts) {
		No_of_contracts = no_of_contracts;
	}
	public double getOpen() {
		return Open;
	}
	public void setOpen(double open) {
		Open = open;
	}
	public int getOpen_Int() {
		return Open_Int;
	}
	public void setOpen_Int(int open_Int) {
		Open_Int = open_Int;
	}
	public double getSettle_Price() {
		return Settle_Price;
	}
	public void setSettle_Price(double settle_Price) {
		Settle_Price = settle_Price;
	}
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public double getTurnover_in_lacs() {
		return Turnover_in_lacs;
	}
	public void setTurnover_in_lacs(double turnover_in_lacs) {
		Turnover_in_lacs = turnover_in_lacs;
	}
}
