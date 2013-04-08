package com.softtech.kismiss.utils;

import org.apache.commons.lang.StringUtils;

public class ArrayUtilz {
	public static int sumArray(int[] values)
	{
		int sum =0;
		for (int i=0; i < values.length; i++)
			sum+=values[i]; 
		return sum;
	}
	
	public static int sumArray(int[] values, int untill)
	{
		int sum =0;
		for (int i=0; i < values.length; i++)
		{
			sum+=values[i]; 
			if(i == untill)
				break;
		}
		return sum;
	}
	
	public static double sum(double[] arg0){
		double result=0;
		for (double d : arg0) {
			result += d;
		}
		return result;
	}
	
	public static int sum(int[] arg0){
		int result=0;
		for (int d : arg0) {
			result += d;
		}
		return result;
	}
	
	public static double[] convertToDouble(String[] arg0){
		double[] result = new double[arg0.length];
		for(int i=0; i < arg0.length; i++){
			result[i] = Double.valueOf(arg0[i]);
		}
		return result;
	}
	
	public static boolean indexOfString(String[] array, String valueToFind){
		for (String content : array) {
			if(StringUtils.indexOf(content, valueToFind) > -1){
				return true;
			}
		}
		return false;
	}
}
