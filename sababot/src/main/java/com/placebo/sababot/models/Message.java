package com.placebo.sababot.models;

import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.placebo.sababot.constants.TelegramMessageType;

public class Message {
  private Long chatId;
  private String text;
  private String fileId;
  private String caption;
  private TelegramMessageType messageType;
  private TelegramLongPollingBot sender;
  private User receiver;

  //Default constructor
  public Message() {
    super();
  }

  /**
   * Full constructor.
   * @param messageType
   * @param chatId
   * @param text
   * @param fileId
   * @param caption
   * @param receiver
   */
  public Message(TelegramMessageType messageType, Long chatId, String text, String fileId, String caption, TelegramLongPollingBot sender, User receiver) {
    super();
    this.chatId = chatId;
    this.text = text;
    this.fileId = fileId;
    this.caption = caption;
    this.messageType = messageType;
    this.sender = sender;
    this.receiver = receiver;
  }

  /**
   * Text message constructor.
   * @param messageType
   * @param chatId
   * @param text
   * @param receiver
   */
  public Message(TelegramMessageType messageType, Long chatId, String text, User receiver) {
    super();
    this.chatId = chatId;
    this.text = text;
    this.messageType = messageType;
    this.receiver = receiver;
  }

  /**
   * File message constructor.
   * @param messageType
   * @param chatId
   * @param fileId
   * @param caption
   * @param receiver
   */
  public Message(TelegramMessageType messageType, Long chatId, String fileId, String caption, User receiver) {
    super();
    this.chatId = chatId;
    this.fileId = fileId;
    this.caption = caption;
    this.messageType = messageType;
    this.receiver = receiver;
  }

  public Long getChatId() {
    return chatId;
  }
  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public String getFileId() {
    return fileId;
  }
  public void setFileId(String fileId) {
    this.fileId = fileId;
  }
  public String getCaption() {
    return caption;
  }
  public void setCaption(String caption) {
    this.caption = caption;
  }
  public TelegramMessageType getMessageType() {
    return messageType;
  }
  public void setMessageType(TelegramMessageType messageType) {
    this.messageType = messageType;
  }
  public TelegramLongPollingBot getSender() {
    return sender;
  }
  public void setSender(TelegramLongPollingBot sender) {
    this.sender = sender;
  }
  public User getReceiver() {
    return receiver;
  }
  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }
}
