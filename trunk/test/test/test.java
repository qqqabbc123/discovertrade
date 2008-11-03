package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream("/root/Download/nifty.txt"));
		String l;
		String source = null;
		try {
			int c;
			while ((c = reader.read()) != -1) {
				source += (char)(c);
				System.out.print((char)(c));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> fieldPatternMap = new TreeMap<String, String>();
		String tagStart = "<\\w+[^>]*>";
		String tagEnd   = "</\\w+[^>]*>";
		String value    = "(\\w+[^<]*)";
		String[] fields = new String[]{	"date", "open", "high", "low", "volume", "lastTradedPrice", 
										"lastTradedTime", "ask", "askSize", "bid", "bidSize"};
		String[] tagValues   = new String[]{"DATE", "DAY OPEN", "DAY HIGH", "DAY LOW", "DAY VOLUME", "LAST TRADE PRICE", 
											"LAST TRADED TIME", "BEST OFFER PRICE", "BEST OFFER QTY", "BEST BID PRICE", "BEST BID QTY" 
											};
		for (int i = 0; i < fields.length; i++) {
			String pattern = tagStart + tagValues[i] + tagEnd + "\\s*+" + tagStart + value  + tagEnd;
			Matcher matcher = Pattern.compile(pattern, Pattern.MULTILINE).matcher(
					source);
			
			int index = 0;
			while (matcher.find()) {
				//System.out.println(matcher.group());
				System.out.println(matcher.group(1));
		// arr.add(matcher.group(matcher.groupCount()-1));
			}
		}
		
				
	}

}
