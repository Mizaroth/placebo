package com.placebo.sababot.core.impl;

import java.util.Map;

import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.core.api.MessageSender;
import com.placebo.sababot.models.Message;

public class TelegramApiWrapper {

  private Map<TelegramMessageType, MessageSender> messageTypeMap;

  public boolean send(Message message) {
    if(message != null)
      return messageTypeMap.get(message.getMessageType()).send(message);
    else
      return false;
  }

  public void setMessageTypeMap(Map<TelegramMessageType, MessageSender> messageTypeMap) {
    this.messageTypeMap = messageTypeMap;
  }

}