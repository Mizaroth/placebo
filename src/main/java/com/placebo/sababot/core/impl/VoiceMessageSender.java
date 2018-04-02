package com.placebo.sababot.core.impl;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.core.api.MessageSender;
import com.placebo.sababot.models.Message;

public class VoiceMessageSender extends MessageSender {
  private PartialBotApiMethod<org.telegram.telegrambots.api.objects.Message> sendVoice;
  private static final Logger LOGGER = Logger.getLogger(VoiceMessageSender.class);

  @Override
  public boolean send(Message m) {
    SendVoice sendMessage = (SendVoice)sendVoice;
    sendMessage.setVoice(m.getFileId());
    sendMessage.setCaption(m.getCaption());
    sendMessage.setChatId(m.getChatId());
    try {
      getSabaBot().sendVoice(sendMessage);
      return true;
    } catch (TelegramApiException e) {
      LOGGER.error("Error while trying to send a voice recording:", e);
    }
    return false;
  }

  public void setSendVoice(PartialBotApiMethod<org.telegram.telegrambots.api.objects.Message> sendVoice) {
    this.sendVoice = sendVoice;
  }
}
