package com.placebo.sababot.core.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.placebo.sababot.bots.SabaBot;
import com.placebo.sababot.models.Message;

public abstract class MessageSender {
  @Autowired
  private SabaBot sabaBot;
  
  public abstract boolean send(Message m);

  public SabaBot getSabaBot() {
    return sabaBot;
  }

  public void setSabaBot(SabaBot sabaBot) {
    this.sabaBot = sabaBot;
  }
}
