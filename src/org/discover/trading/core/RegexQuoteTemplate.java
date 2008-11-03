package org.discover.trading.core;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RegexQuoteTemplate {
	private String regexPattern;
	private Map<String, String> fieldPatternMap = null;
	private String dateFormat;
	public RegexQuoteTemplate(String dateFormat) {
		this.fieldPatternMap = new TreeMap<String, String>();
		this.dateFormat = dateFormat;
	}
	
	public RegexQuoteTemplate(Map<String, String> fieldPatternMap, String dateFormat) {
		this.fieldPatternMap = fieldPatternMap;
		this.dateFormat = dateFormat;
	}
	
	public void put(String field, String pattern) {
		fieldPatternMap.put(field, pattern);
	}
	
	public void remove(String field) {
		fieldPatternMap.remove(field);
	}	
	
	public String getPattern(String field) {
		return fieldPatternMap.get(field);
	}
	
	public Set<String> getFields() {
		return fieldPatternMap.keySet();
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
}
