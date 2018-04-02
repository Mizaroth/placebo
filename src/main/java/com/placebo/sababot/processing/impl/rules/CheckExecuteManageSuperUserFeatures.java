package com.placebo.sababot.processing.impl.rules;

import org.telegram.telegrambots.api.objects.Message;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.CheckRule;

public class CheckExecuteManageSuperUserFeatures implements CheckRule {

  @Override
  public <T extends UpdateReceivedContext> boolean check(T updateContext) {
    Message message = updateContext.getUpdate().getMessage();
    String from = message.getFrom().getUserName();
    String title = message.getChat().getTitle();

    if(title != null)
      return false;

    if(!("Mizaroth".equals(from) || "cbarbato".equals(from)))
      return false;

    return (message.hasPhoto() || message.getVoice() != null);
  }

}
