package com.placebo.sababot.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQueries({
  @NamedNativeQuery(
    name="needsToBeCleanedQuery",
    query="select sum(n_live_tup)/10000 >= 0.5 from pg_stat_user_tables;"
  ),
  @NamedNativeQuery(
      name="performCleaningStatement",
      query="delete from trigger where id_trigger in "
          + "(select trigger.id_trigger from trigger order by id_trigger limit "
          + "(select count(id_trigger)*0.75 from trigger));"
    )
})
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
  
  @Column(name="MESSAGE")
  private String message;
  
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
  public Trigger(String source, String chatTitle, Date triggerTime, String message) {
    this.source = source;
    this.chatTitle = chatTitle;
    this.triggerTime = triggerTime;
    this.message = message;
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
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
}
