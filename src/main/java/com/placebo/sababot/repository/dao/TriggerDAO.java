package com.placebo.sababot.repository.dao;

import org.springframework.stereotype.Repository;

import com.placebo.sababot.models.Trigger;
import com.placebo.sababot.repository.api.AbstractGenericDAO;

@Repository
public class TriggerDAO extends AbstractGenericDAO<Trigger> {
  public TriggerDAO() {
    super();
    setClazz(Trigger.class);
  }
}
