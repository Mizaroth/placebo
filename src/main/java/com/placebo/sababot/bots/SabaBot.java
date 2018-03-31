package com.placebo.sababot.bots;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.core.impl.TelegramApiWrapper;
import com.placebo.sababot.models.Message;
import com.placebo.sababot.utils.RNGHandler;
import com.placebo.sababot.utils.ReplyDispatcher;

public class SabaBot extends TelegramLongPollingBot {
  @Value("#{botProps.user}")
  private String botUsername;
  @Value("#{botProps.token}")
  private String botToken;
  @Autowired
  private TelegramApiWrapper telegramApiWrapper;
  private static final Logger LOGGER = Logger.getLogger(SabaBot.class);

  @Override
  public void onUpdateReceived(Update update) {
    if(update != null && update.hasMessage()) {
      boolean actionPerformed = false;

      Long chatId = update.getMessage().getChatId();
      User userFrom = update.getMessage().getFrom();

      String from = userFrom.getFirstName() + " '" + userFrom.getUserName() + "'" + ((userFrom.getLastName() != null) ? (" " + userFrom.getLastName()) : "") ;

      if(("Mizaroth".equals(userFrom.getUserName()) || "cbarbato".equals(userFrom.getUserName())) 
          && update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() == null) {
        actionPerformed = manageSuperUserFeatures(update, chatId, userFrom);
      }

      //10% of sending hilarious audio
      if(RNGHandler.procByPercentage(10)) {
        String fileId = (String)ReplyDispatcher.reply(ReactionConstants.getVoiceRecordings());
        Message message = new Message(TelegramMessageType.VOICE, chatId, fileId, null, userFrom);
        actionPerformed = telegramApiWrapper.send(message);
      }

      //5% of sending SabaSelfie
      if(RNGHandler.procByPercentage(5)) {
        String fileId = ReactionConstants.SABA_SELFIE;
        String caption = ReactionConstants.SABA_SELFIE_CAPTION;
        Message message = new Message(TelegramMessageType.PHOTO, chatId, fileId, caption, userFrom);
        actionPerformed = telegramApiWrapper.send(message);
      }

      String messageText = update.getMessage().getText();

      if(messageText != null) {
        String messageCapitalized = messageText.toUpperCase();

        //1% of mOcKiNg YoUr RePlY
        if(RNGHandler.procByPercentage(1)) {
          String text = ReplyDispatcher.mockReply(messageText);
          Message message = new Message(TelegramMessageType.TEXT, chatId, text, userFrom);
          actionPerformed = telegramApiWrapper.send(message);
        }

        if(messageCapitalized.contains(ReactionConstants.LAVORO_DIFFICILE)) {
          String fileId = ReactionConstants.LAVORO_DIFFICILE_REPLY;
          Message message = new Message(TelegramMessageType.VOICE, chatId, fileId, null, userFrom);
          actionPerformed = telegramApiWrapper.send(message);
        }

        if(containsOneOf(messageCapitalized, ReactionConstants.getSabato())) {
          String text = ReplyDispatcher.reply(ReactionConstants.getSabatoReply());
          Message message = new Message(TelegramMessageType.TEXT, chatId, text, userFrom);
          actionPerformed = telegramApiWrapper.send(message);
        }
      }

      if(actionPerformed) {
        LOGGER.info("Triggered by: " + from + " | Chat: " + ((update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() != null) ? update.getMessage().getChat().getTitle() : "Private Chat" ));
      }
    }
  }

  private boolean manageSuperUserFeatures(Update update, Long chatId, User userFrom) {
    Message message = null;
    
    if(update.getMessage().hasPhoto()) {
      String fileId = update.getMessage().getPhoto().get(0).getFileId();
      String caption = "File ID: " + update.getMessage().getPhoto().get(0).getFileId();
      message = new Message(TelegramMessageType.PHOTO, chatId, fileId, caption, userFrom);
    }

    if(update.getMessage().getVoice() != null) {
      String fileId = update.getMessage().getVoice().getFileId();
      String caption = "File ID: " + update.getMessage().getVoice().getFileId();
      message = new Message(TelegramMessageType.VOICE, chatId, fileId, caption, userFrom);
    }
    
    return telegramApiWrapper.send(message);
  }
  
  private boolean containsOneOf(String message, String[] triggers) {
    for(String trigger : triggers) {
      if(message.matches(".*\\b" + trigger + "\\b.*"))
        return true;
    }

    return false;
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  public void setTelegramApiWrapper(TelegramApiWrapper telegramApiWrapper) {
    this.telegramApiWrapper = telegramApiWrapper;
  }
}
