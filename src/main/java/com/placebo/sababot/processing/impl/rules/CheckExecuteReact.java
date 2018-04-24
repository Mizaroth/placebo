package com.placebo.sababot.processing.impl.rules;

import java.util.Arrays;

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
    return Arrays.stream(triggers)
        .filter(trig -> message.matches(".*\\b" + trig + "\\b.*"))
        .findFirst()
        .orElse(null) != null;
  }

}
