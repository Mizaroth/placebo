package com.placebo.sababot.processing.api;

import com.placebo.sababot.models.UpdateReceivedContext;

public interface UpdateProcessingChain {
  public abstract UpdateReceivedContext process(UpdateReceivedContext updateReceivedContext);
  public abstract UpdateProcessingChain getNextChain();
  public abstract void setNextChain(UpdateProcessingChain nextChain);
  public abstract UpdateProcessingState getState();
  public abstract void setState(UpdateProcessingState state);
}
