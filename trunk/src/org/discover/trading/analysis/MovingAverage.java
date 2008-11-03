package org.discover.trading.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;

public class MovingAverage implements Indicator {

	static Core taLib = new Core();
	public static List<Double> calculate(List<Double> quotes, int period) {
		
		List<Double> result = new ArrayList<Double>();
		double resultArray[] = new double[quotes.size()];
		int startIdx = 0, endIdx = quotes.size() - 1; 
		MInteger outBegIdx = new MInteger(), outNBElement = new MInteger();
		double closePrices[]  = new double[quotes.size()];
		
		// convert close prices from Double to double
		ListIterator<Double> iterator = quotes.listIterator();
		int index = 0;
		while(iterator.hasNext()) {
			closePrices[index++] = iterator.next();
		}
		
		taLib.movingAverage(startIdx, endIdx, closePrices, period, MAType.Sma, outBegIdx, outNBElement, resultArray);
		System.out.println();
		//System.out.println(resultArray.length + ", " + quotes.size());
		//System.out.println(outBegIdx.value + ", " + outNBElement.value);
		//System.out.println(Arrays.toString(resultArray));
		
		for (int i = 0; i < outBegIdx.value; i++) {
			result.add(0.0);
		}
		
		for (int i = 0; i < outNBElement.value; i++) {
			result.add(resultArray[i]);
		//	System.out.print(resultArray[i] +  ", ");
		}
		
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) +  ", ");
		}		
		return result;
	}
	
	public double getLastPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
