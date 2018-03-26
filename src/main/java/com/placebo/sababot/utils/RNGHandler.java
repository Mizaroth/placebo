package com.placebo.sababot.utils;

public class RNGHandler {

	public static boolean procByPercentage(int chancePercentage) {
		return Math.round((Math.random()*100)) >= (100-chancePercentage);
	}
	
}
