package com.placebo.sababot.bots;


import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.placebo.sababot.models.Trigger;
import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.UpdateProcessingChain;
import com.placebo.sababot.repository.dao.TriggerDAO;
import com.placebo.sababot.utils.MessageExtractor;

public class SabaBot extends TelegramLongPollingBot  {
  @Value("#{botProps.user}")
  private String botUsername;
  @Value("#{botProps.token}")
  private String botToken;
  @Autowired
  private UpdateProcessingChain startUpdateProcessingChain;
  @Autowired
  private TriggerDAO triggerDAO;
  private static final Logger LOGGER = Logger.getLogger(SabaBot.class);

  @Override
  public void onUpdateReceived(Update update) {
    if(update != null && update.hasMessage()) {
      /** Process the received Update **/
      UpdateReceivedContext updateReceivedContext = 
          startUpdateProcessingChain.process(new UpdateReceivedContext(update));

      /** Log **/
      String from = updateReceivedContext.getFrom();
      String chatTitle = updateReceivedContext.getChatTitle();
      if(updateReceivedContext.isActionPerformed()) {
        LOGGER.info("Triggered by: " + from + " | Chat: " + chatTitle);
        triggerDAO.create(new Trigger(from, chatTitle, new Date(), MessageExtractor.extract(update)));
      }
    }
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}