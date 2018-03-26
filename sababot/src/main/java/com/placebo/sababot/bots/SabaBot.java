package com.placebo.sababot.bots;

import java.io.IOException;
import java.util.Properties;

import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SabaBot extends TelegramLongPollingBot {

  private static final Properties PROPS = new Properties();

  static {
    try {
      PROPS.load(ClassLoader.class.getResourceAsStream("/META-INF/user.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getBotUsername() {
    return PROPS.getProperty("user");
  }

  public void onUpdateReceived(Update update) {
    if(update != null) {
      if(update.hasMessage()) {
        boolean actionPerformed = false;

        Long chatId = update.getMessage().getChatId();
        User userFrom = update.getMessage().getFrom();

        String from = null;

        if(userFrom != null)
          from = userFrom.getFirstName() + " '" + userFrom.getUserName() + "'" + ((userFrom.getLastName() != null) ? (" " + userFrom.getLastName()) : "") ;

        if(userFrom != null && ("Mizaroth".equals(userFrom.getUserName())|| "cbarbato".equals(userFrom.getUserName()) )&& update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() == null) {
          if(update.getMessage().hasPhoto()) {
            SendPhoto sp = new SendPhoto();
            sp.setPhoto(update.getMessage().getPhoto().get(0).getFileId());
            sp.setCaption("File ID: " + update.getMessage().getPhoto().get(0).getFileId());
            sp.setChatId(chatId);
            try {
              sendPhoto(sp);
              actionPerformed = true;
            } catch (TelegramApiException e) {
              e.printStackTrace();
            }
          }

          if(update.getMessage().getVoice() != null) {
            SendVoice sv = new SendVoice();
            sv.setVoice(update.getMessage().getVoice().getFileId());
            sv.setCaption("File ID: " + update.getMessage().getVoice().getFileId());
            sv.setChatId(chatId);
            try {
              sendVoice(sv);
              actionPerformed = true;
            } catch (TelegramApiException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }

  }

  @Override
  public String getBotToken() {
    return PROPS.getProperty("token");
  }

}
