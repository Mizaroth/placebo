package com.placebo.sababot.core.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.core.api.MessageSender;
import com.placebo.sababot.models.Message;

public class PhotoMessageSender extends MessageSender {

  @Autowired
  private PartialBotApiMethod<org.telegram.telegrambots.api.objects.Message> sendPhoto;
  private static final Logger LOGGER = Logger.getLogger(PhotoMessageSender.class);

  @Override
  public boolean send(Message m) {
    SendPhoto sendMessage = (SendPhoto)sendPhoto;
    sendMessage.setPhoto(m.getFileId());
    sendMessage.setCaption(m.getCaption());
    sendMessage.setChatId(m.getChatId());
    try {
      getSabaBot().sendPhoto(sendMessage);
      return true;
    } catch (TelegramApiException e) {
      LOGGER.error("Error while trying to send a photo:", e);
    }
    return false;
  }

  public void setSendPhoto(PartialBotApiMethod<org.telegram.telegrambots.api.objects.Message> sendPhoto) {
    this.sendPhoto = sendPhoto;
  }
}
