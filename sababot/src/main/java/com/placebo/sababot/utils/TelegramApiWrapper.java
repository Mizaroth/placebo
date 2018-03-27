package com.placebo.sababot.utils;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.bots.SabaBot;
import com.placebo.sababot.constants.TelegramMessageType;

public class TelegramApiWrapper {

  private static final SendMessage SEND_MESSAGE = new SendMessage();
  private static final SendVoice SEND_VOICE = new SendVoice();
  private static final SendPhoto SEND_PHOTO = new SendPhoto();

  public static boolean send(SabaBot sabaBot, TelegramMessageType messageType, long chatId, String... args) {
    boolean actionPerformed = false;

    switch(messageType) {
    case MESSAGE:
      actionPerformed = sendMessage(sabaBot, chatId, args[0]);
      break;
    case VOICE:
      actionPerformed = sendVoice(sabaBot, chatId, args[0]);
      break;
    case PHOTO:
      actionPerformed = sendPhoto(sabaBot, chatId, args);
      break;
    }

    return actionPerformed;
  }

  private static boolean sendMessage(SabaBot sabaBot, long chatId, String text) {
    SEND_MESSAGE.setChatId(chatId);
    SEND_MESSAGE.setText(text);
    try {
      sabaBot.execute(SEND_MESSAGE);
      return true;
    } catch(TelegramApiException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean sendPhoto(SabaBot sabaBot, long chatId, String... args) {
    SEND_PHOTO.setPhoto(args[0]);
    SEND_PHOTO.setCaption(args.length > 1 ? args[1] : null);
    SEND_PHOTO.setChatId(chatId);
    try {
      sabaBot.sendPhoto(SEND_PHOTO);
      return true;
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean sendVoice(SabaBot sabaBot, long chatId, String... args) {
    SEND_VOICE.setVoice(args[0]);
    SEND_VOICE.setCaption(args.length > 1 ? args[1] : null);
    SEND_VOICE.setChatId(chatId);
    try {
      sabaBot.sendVoice(SEND_VOICE);
      return true;
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
    return false;
  }

}