package com.placebo.sababot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.bots.SabaBot;

public class SabaMain {

  private static final Logger LOGGER = Logger.getLogger(SabaMain.class);
  
  private SabaMain() {
    //do NOT.
  }

  public static void main( String[] args ){
    ApiContextInitializer.init();
    TelegramBotsApi botsApi = new TelegramBotsApi();
    try {
      botsApi.registerBot(new SabaBot());
    } catch (TelegramApiException e) {
      LOGGER.error("Error while trying to register the bot:", e);
    }
  }
}
