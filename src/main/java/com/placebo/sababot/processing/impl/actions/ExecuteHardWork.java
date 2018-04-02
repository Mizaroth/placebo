package com.placebo.sababot.processing.impl.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.User;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.core.impl.TelegramApiWrapper;
import com.placebo.sababot.models.Message;
import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.ExecuteAction;

public class ExecuteHardWork implements ExecuteAction {
  @Autowired
  private TelegramApiWrapper telegramApiWrapper;

  @Override
  public <T extends UpdateReceivedContext> T execute(T updateContext) {
    org.telegram.telegrambots.api.objects.Message messageFrom = updateContext.getUpdate().getMessage();
    Long chatId = messageFrom.getChatId();
    User userFrom = messageFrom.getFrom();
    String fileId = ReactionConstants.LAVORO_DIFFICILE_REPLY;
    
    Message messageTo = new Message(TelegramMessageType.VOICE, chatId, fileId, null, userFrom);
    
    if(!updateContext.isActionPerformed())
      updateContext.setActionPerformed(telegramApiWrapper.send(messageTo));

    return updateContext;
  }

}
