package org.discover.trading.io;

import java.util.concurrent.ScheduledExecutorService;

import org.discover.trading.core.Security;

public class QuoteIOManager {
	
	// connection types
	public static final int DATABASE = 1;
	public static final int CSVFILE  = 2;
	public static final int INTERNET = 4;
	
	// feed types
	public static final int LIVE = 0;
	public static final int HISTORY = 1;
	
	// feed type of this manager
	private int feedType;

	// default connection type
	private int connectionType;
	
	// Executor service for internet. 
	ScheduledExecutorService internetThreadExecutor;
	
	// size of the executor service thread pool
	private static final int internetThreadPoolSize = 10; 
	
	// local executor for local database
	ScheduledExecutorService localThreadExecutor;

	// size of the executor service thread pool
	private static final int localThreadPoolSize = 20;
	
	public QuoteIOManager(int feedType, int connectionType) {
		this.feedType 		= feedType;
		this.connectionType = connectionType;
	}
	
	public void add(Security security) {
		
	}
	
	public void remove(Security security) {
		
	}
}
