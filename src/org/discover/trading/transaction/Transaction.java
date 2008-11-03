package org.discover.trading.transaction;

import org.discover.trading.core.IQuote;
import org.discover.trading.core.Security;

public class Transaction {
	
	private Security mSecurity = null;
	private int mQuantity;
	private Deductables mDeduct = null;
	private IQuote mStartPosition = null;
	
	private IQuote mEndPosition = null;
	private boolean mSquared = false;
	
	public static final int NOT_INITIATED = 1;
	public static final int IN_PROGRESS  = 2;
	public static final int SQUARED = 3;
	public static final int SHORT = 1;
	public static final int LONG = 2;
	
	private int mStatus = NOT_INITIATED;

	private int mTransactionType;
	
	public Transaction(Security security, Deductables deduct,int transactionType) {
		mDeduct = deduct;
		mSecurity = security;
		mTransactionType = transactionType;
	}
	
	public IQuote getStartPosition() {
		return mStartPosition;
	}

	public void setStartPosition(IQuote startPosition) {
		mStartPosition = startPosition;
	}

	public IQuote getEndPosition() {
		return mEndPosition;
	}

	public void setEndPosition(IQuote endPosition) {
		mEndPosition = endPosition;
	}
	
	public Security getSecurity() {
		return mSecurity;
	}

	public int getQuantity() {
		return mQuantity;
	}

	public Deductables getDeductables() {
		return mDeduct;
	}

	public boolean isSquared() {
		return mSquared;
	}
	public int getStatus() {
		return mStatus;
	}

	public int getTransactionType() {
		return mTransactionType;
	}

	public void start(IQuote quote,  int quantity) {
		mStartPosition = quote;
		mQuantity = quantity;
		mStatus = IN_PROGRESS;		
	}
	
	public void end(IQuote quote) {
		mEndPosition = quote;
		mStatus = SQUARED;
	}
	
	public double getTransactionAmount() {
		if (getTransactionType() == SHORT ) {
			double tradeAmount = getQuantity() * (getStartPosition().getOpen() - getEndPosition().getOpen());
			double deduction = tradeAmount * getDeductables().getPercentage(); 
			return  tradeAmount - deduction ;
		}
		
		else {
			double tradeAmount = getQuantity() * (getEndPosition().getOpen() - getStartPosition().getOpen());
			double deduction = tradeAmount * getDeductables().getPercentage(); 
			return  tradeAmount - deduction ;
		}
		
	}	
}
