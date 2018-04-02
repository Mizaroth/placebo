package com.placebo.sababot.models;

import java.util.List;

import org.telegram.telegrambots.api.objects.Update;

public class UpdateReceivedContext {
  
  private Update update;
  private String from;
  private String chatTitle;
  private boolean actionPerformed;
  private List<String> executedSteps;
  private String strategyType;

  public UpdateReceivedContext() {
    super();
  }
  
  public UpdateReceivedContext(Update update) {
    this.setUpdate(update);
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Update getUpdate() {
    return update;
  }

  public void setUpdate(Update update) {
    this.update = update;
  }

  public boolean isActionPerformed() {
    return actionPerformed;
  }

  public void setActionPerformed(boolean actionPerformed) {
    this.actionPerformed = actionPerformed;
  }

  public String getStrategyType() {
    return strategyType;
  }

  public void setStrategyType(String strategyType) {
    this.strategyType = strategyType;
  }

  public List<String> getExecutedSteps() {
    return executedSteps;
  }

  public void setExecutedSteps(List<String> executedSteps) {
    this.executedSteps = executedSteps;
  }

  public String getChatTitle() {
    return chatTitle;
  }

  public void setChatTitle(String chatTitle) {
    this.chatTitle = chatTitle;
  }
}
