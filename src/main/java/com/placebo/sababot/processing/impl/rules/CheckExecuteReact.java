package com.placebo.sababot.processing.impl.rules;

import org.telegram.telegrambots.api.objects.Message;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.CheckRule;

public class CheckExecuteReact implements CheckRule {

  @Override
  public <T extends UpdateReceivedContext> boolean check(T updateContext) {
    Message message = updateContext.getUpdate().getMessage();
    String messageCapitalized = message.getText() != null ? message.getText().toUpperCase() : null;

    return messageCapitalized != null 
        && containsOneOf(messageCapitalized, ReactionConstants.getSabato());
  }
  
  private boolean containsOneOf(String message, String[] triggers) {
    for(String trigger : triggers) {
      if(message.matches(".*\\b" + trigger + "\\b.*"))
        return true;
    }
    return false;
  }

}
