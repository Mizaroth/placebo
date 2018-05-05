package com.placebo.sababot.scheduled;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import com.placebo.sababot.repository.dao.TriggerDAO;

public class ScheduledCleaningAgent {

  private static final Logger LOGGER = Logger.getLogger(ScheduledCleaningAgent.class);
  @Autowired
  private TriggerDAO triggerDAO;
  
  @Async("scheduledTaskExecutor")
  @Scheduled(cron="0 */5 * ? * *")
  public void performCleaning() {
    int cleanedRows = 0;
    if(triggerDAO.needsToBeCleaned()) {
      cleanedRows = triggerDAO.performCleaning();
    }
    LOGGER.info("Cleaned " + cleanedRows + " rows.");
  }
  
}
