package com.placebo.sababot.processing.api;

import com.placebo.sababot.models.UpdateReceivedContext;

public interface CheckRule {
  public <T extends UpdateReceivedContext> boolean check(T update);
}
