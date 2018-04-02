package com.placebo.sababot.processing.impl.rules;

import org.telegram.telegrambots.api.objects.Message;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.CheckRule;
import com.placebo.sababot.utils.RNGHandler;

public class CheckExecuteMockReply implements CheckRule {

  @Override
  public <T extends UpdateReceivedContext> boolean check(T updateContext) {
    Message message = updateContext.getUpdate().getMessage();
    return message.getText() != null && RNGHandler.procByPercentage(1);
  }

}
