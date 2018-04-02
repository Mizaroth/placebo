package com.placebo.sababot.processing.api;

import com.placebo.sababot.models.UpdateReceivedContext;

public interface ExecuteAction {
  public <T extends UpdateReceivedContext> T execute(T updateContext);
}
