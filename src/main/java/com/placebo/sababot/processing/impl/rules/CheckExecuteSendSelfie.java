package com.placebo.sababot.processing.impl.rules;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.CheckRule;
import com.placebo.sababot.utils.RNGHandler;

public class CheckExecuteSendSelfie implements CheckRule {

  @Override
  public <T extends UpdateReceivedContext> boolean check(T updateContext) {
    return RNGHandler.procByPercentage(10);
  }

}
