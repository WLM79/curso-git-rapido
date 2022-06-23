package com.example.demo.util;

public class TimeUtil {
	
	public static double getDoubleHour(String hour){
		
		String[] hourArray = hour.split(":");
		double hourDouble = Double.parseDouble(hourArray[0]) + (Double.parseDouble(hourArray[1]) / 60 );
		
		return hourDouble;
	}
}
