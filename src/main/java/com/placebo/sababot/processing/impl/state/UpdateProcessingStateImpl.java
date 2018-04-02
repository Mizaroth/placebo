package com.placebo.sababot.processing.impl.state;

import java.util.ArrayList;
import java.util.Map;

import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.CheckRule;
import com.placebo.sababot.processing.api.ExecuteAction;
import com.placebo.sababot.processing.api.UpdateProcessingState;

public class UpdateProcessingStateImpl implements UpdateProcessingState {
  private Map<String, Object> guardMap;
  private Map<String, Object> actionMap;
  
  @Override
  public Boolean checkExecute(UpdateReceivedContext updateReceivedContext) {
    return ((CheckRule)getGuardMap().get(updateReceivedContext.getStrategyType())).check(updateReceivedContext);
  }
  
  @Override
  public UpdateReceivedContext doExecute(UpdateReceivedContext updateReceivedContext) {
    ExecuteAction executeAction = (ExecuteAction)getActionMap().get(updateReceivedContext.getStrategyType());
    trackExecutedStep(updateReceivedContext, executeAction);
    return executeAction.execute(updateReceivedContext);
  }
  
  private void trackExecutedStep(UpdateReceivedContext updateReceivedContext, ExecuteAction executeAction) {
    if(updateReceivedContext != null) {
      if(updateReceivedContext.getExecutedSteps() == null)
        updateReceivedContext.setExecutedSteps(new ArrayList<>());
      updateReceivedContext.getExecutedSteps().add(executeAction.getClass().getSimpleName());
    }
  }
  
  public Map<String, Object> getActionMap() {
    return actionMap;
  }
  public void setActionMap(Map<String, Object> actionMap) {
    this.actionMap = actionMap;
  }
  public Map<String, Object> getGuardMap() {
    return guardMap;
  }
  public void setGuardMap(Map<String, Object> guardMap) {
    this.guardMap = guardMap;
  }
}
