package com.placebo.sababot.core.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.placebo.sababot.core.api.MessageSender;
import com.placebo.sababot.models.Message;

public class TelegramApiWrapper {

  @Autowired
  private Map<String, MessageSender> strategyMap;

  public boolean send(Message message) {
    if(message != null && message.getMessageType() != null)
      return strategyMap.get(message.getMessageType().name()).send(message);
    else
      return false;
  }

  public void setStrategyMap(Map<String, MessageSender> strategyMap) {
    this.strategyMap = strategyMap;
  }

}