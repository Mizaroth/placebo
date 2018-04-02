package com.placebo.sababot.processing.impl.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.User;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.core.impl.TelegramApiWrapper;
import com.placebo.sababot.models.Message;
import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.ExecuteAction;
import com.placebo.sababot.utils.ReplyDispatcher;

public class ExecuteReact implements ExecuteAction {
  @Autowired
  private TelegramApiWrapper telegramApiWrapper;

  @Override
  public <T extends UpdateReceivedContext> T execute(T updateContext) {
    org.telegram.telegrambots.api.objects.Message messageFrom = updateContext.getUpdate().getMessage();
    Long chatId = messageFrom.getChatId();
    User userFrom = messageFrom.getFrom();
    String replyText = ReplyDispatcher.reply(ReactionConstants.getSabatoReply());
    
    Message messageTo = new Message(TelegramMessageType.TEXT, chatId, replyText, userFrom);

    if(!updateContext.isActionPerformed())
      updateContext.setActionPerformed(telegramApiWrapper.send(messageTo));

    return updateContext;
  }

}
