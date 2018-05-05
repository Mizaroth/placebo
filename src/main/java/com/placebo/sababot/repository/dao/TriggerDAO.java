package com.placebo.sababot.repository.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.placebo.sababot.models.Trigger;
import com.placebo.sababot.repository.api.AbstractGenericDAO;

@Repository
@Transactional
public class TriggerDAO extends AbstractGenericDAO<Trigger> {
  private String needsToBeCleanedQuery;
  private String performCleaningStatement;
  public TriggerDAO() {
    super();
    setClazz(Trigger.class);
  }

  public Boolean needsToBeCleaned() {
    return (Boolean) getCurrentSession().getNamedNativeQuery(needsToBeCleanedQuery).uniqueResult();
  }
  
  public int performCleaning() {
    return getCurrentSession().getNamedNativeQuery(performCleaningStatement).executeUpdate();
  }
  
  public void setNeedsToBeCleanedQuery(String needsToBeCleanedQuery) {
    this.needsToBeCleanedQuery = needsToBeCleanedQuery;
  }
  public void setPerformCleaningStatement(String performCleaningStatement) {
    this.performCleaningStatement = performCleaningStatement;
  }
}
