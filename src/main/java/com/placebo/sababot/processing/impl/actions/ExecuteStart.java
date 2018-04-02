package com.placebo.sababot.processing.impl.actions;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.ExecuteAction;

public class ExecuteStart implements ExecuteAction {

  @Override
  public <T extends UpdateReceivedContext> T execute(T updateContext) {
    Message message = updateContext.getUpdate().getMessage();
    User userFrom = message.getFrom();
    
    String from = userFrom.getFirstName() + " '" + userFrom.getUserName() + "'" 
    + ((userFrom.getLastName() != null) ? (" " + userFrom.getLastName()) : "");
    
    String chatTitle = (message.getChat() != null && message.getChat().getTitle() != null) ? 
        message.getChat().getTitle() : "Private Chat";
    
    updateContext.setActionPerformed(false);
    updateContext.setFrom(from);
    updateContext.setChatTitle(chatTitle);

    return updateContext;
  }

}
