package com.placebo.sababot;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.bots.SabaBot;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class SabaMain implements CommandLineRunner {

  private static final Logger LOGGER = Logger.getLogger(SabaMain.class);
  private SabaBot sabaBot;
  
  static {
    ApiContextInitializer.init();
  }

  public static void main( String[] args ){
    SpringApplication.run(SabaMain.class, args);
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    TelegramBotsApi botsApi = new TelegramBotsApi();
    try {
      botsApi.registerBot(sabaBot);
    } catch (TelegramApiException e) {
      LOGGER.error("Error while trying to register the bot:", e);
    }
  }

  public void setSabaBot(SabaBot sabaBot) {
    this.sabaBot = sabaBot;
  }
}
