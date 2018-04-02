package com.placebo.sababot.processing.impl.chain;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.UpdateProcessingChain;
import com.placebo.sababot.processing.api.UpdateProcessingState;

public class UpdateProcessingChainImpl implements UpdateProcessingChain {
  private UpdateProcessingState state;
  private UpdateProcessingChain nextChain;
  
  @Override
  public UpdateReceivedContext process(UpdateReceivedContext updateReceivedContext) {
    if(getState() != null && getState().checkExecute(updateReceivedContext)) {
      updateReceivedContext = getState().doExecute(updateReceivedContext);
    }
    if(getNextChain() != null) {
      updateReceivedContext = getNextChain().process(updateReceivedContext);
    }
    return updateReceivedContext;
  }
  
  @Override
  public UpdateProcessingChain getNextChain() {
    return nextChain;
  }
  @Override
  public void setNextChain(UpdateProcessingChain nextChain) {
    this.nextChain = nextChain;
  }
  @Override
  public UpdateProcessingState getState() {
    return state;
  }
  @Override
  public void setState(UpdateProcessingState state) {
    this.state = state;
  }
}
