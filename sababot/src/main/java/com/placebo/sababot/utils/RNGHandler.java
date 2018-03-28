package com.placebo.sababot.utils;

public class RNGHandler {
  
  private RNGHandler() {
    //do NOT instantiate
  }

  public static boolean procByPercentage(int chancePercentage) {
    return Math.round((Math.random()*100)) >= (100-chancePercentage);
  }

}
