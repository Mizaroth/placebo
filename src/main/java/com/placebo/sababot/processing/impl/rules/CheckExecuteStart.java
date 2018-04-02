package com.placebo.sababot.processing.impl.rules;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.CheckRule;

public class CheckExecuteStart implements CheckRule {

  @Override
  public <T extends UpdateReceivedContext> boolean check(T updateContext) {
    return updateContext != null && updateContext.getUpdate() != null && updateContext.getUpdate().hasMessage();
  }

}
