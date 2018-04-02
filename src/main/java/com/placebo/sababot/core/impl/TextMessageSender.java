package com.placebo.sababot.core.impl;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.core.api.MessageSender;
import com.placebo.sababot.models.Message;

public class TextMessageSender extends MessageSender {
  private PartialBotApiMethod<org.telegram.telegrambots.api.objects.Message> sendText;
  private static final Logger LOGGER = Logger.getLogger(TextMessageSender.class);

  @Override
  public boolean send(Message m) {
    SendMessage sendMessage = (SendMessage)sendText;
    sendMessage.setChatId(m.getChatId());
    sendMessage.setText(m.getText());
    try {
      getSabaBot().execute(sendMessage);
      return true;
    } catch(TelegramApiException e) {
      LOGGER.error("Error while trying to send a message:", e);
    }
    return false;
  }

  public void setSendText(PartialBotApiMethod<org.telegram.telegrambots.api.objects.Message> sendText) {
    this.sendText = sendText;
  }
}
