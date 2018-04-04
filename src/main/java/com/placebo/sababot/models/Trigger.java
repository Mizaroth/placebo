package com.placebo.sababot.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TRIGGER")
@SequenceGenerator(name="TriggerSeqGen", sequenceName="TRIGGER_SEQ", allocationSize=1)
public class Trigger {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TriggerSeqGen")
  @Column(name="ID_TRIGGER")
  private long id;
  
  @Column(name="SOURCE")
  private String source;
  
  @Column(name="CHAT_TITLE")
  private String chatTitle;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="TRIGGER_TIME")
  private Date triggerTime;

  public Trigger() {
    super();
  }
  public Trigger(String source, String chatTitle, Date triggerTime) {
    this.source = source;
    this.chatTitle = chatTitle;
    this.triggerTime = triggerTime;
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }
  public String getChatTitle() {
    return chatTitle;
  }
  public void setChatTitle(String chatTitle) {
    this.chatTitle = chatTitle;
  }
  public Date getTriggerTime() {
    return triggerTime;
  }
  public void setTriggerTime(Date triggerTime) {
    this.triggerTime = triggerTime;
  }
}
