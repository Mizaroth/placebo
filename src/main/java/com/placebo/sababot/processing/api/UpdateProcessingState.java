package com.placebo.sababot.processing.api;

import java.util.Map;

import com.placebo.sababot.models.UpdateReceivedContext;

public interface UpdateProcessingState {
  public abstract Boolean checkExecute(UpdateReceivedContext updateReceivedContext);
  public abstract UpdateReceivedContext doExecute(UpdateReceivedContext updateReceivedContext);
  public abstract Map<String, Object> getActionMap();
  public abstract void setActionMap(Map<String, Object> actionMap);
  public abstract Map<String, Object> getGuardMap();
  public abstract void setGuardMap(Map<String, Object> guardMap);
}
