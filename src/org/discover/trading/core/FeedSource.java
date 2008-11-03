package org.discover.trading.core;

import java.sql.Connection;

public class FeedSource {
	private int feedType;
	private String csvFilePath;
	private String feedURL;
	private Connection databaseConnection;
	
	public FeedSource(int feedType, String csvFilePath, String feedURL, Connection databaseConnection) {
		this.feedType 			= feedType;
		this.feedURL 	 		= feedURL;
		this.csvFilePath 		= csvFilePath;
		this.databaseConnection = databaseConnection;
	}

	public int getFeedType() {
		return feedType;
	}

	public void setFeedType(int feedType) {
		this.feedType = feedType;
	}

	public String getCsvFilePath() {
		return csvFilePath;
	}

	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	public String getFeedURL() {
		return feedURL;
	}

	public void setFeedURL(String feedURL) {
		this.feedURL = feedURL;
	}

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	public void setDatabaseConnection(Connection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}
}
