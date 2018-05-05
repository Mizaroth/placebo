package com.placebo.sababot.utils;

import org.telegram.telegrambots.api.objects.Contact;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class MessageExtractor {

  private MessageExtractor() {
    //Do not instantiate.
  }

  public static String extract(Update update) {
    StringBuilder extractedMessage = null;
    
    if(update != null && update.hasMessage()) {
      Message message = update.getMessage();
      extractedMessage = decodeMessage(message);
    }
    
    return extractedMessage != null ? extractedMessage.toString() : null;
  }

  private static StringBuilder decodeMessage(Message message) {
    StringBuilder extractedMessage = new StringBuilder();

    if(message.hasText()) {
      extractedMessage.append("Text: " + message.getText() + "\n");
    }
    if(message.hasPhoto()) {
      extractedMessage.append("PhotoID: " + message.getPhoto().get(0).getFileId() + "\n");
    }
    if(message.hasDocument()) {
      extractedMessage.append("Document: NAME=" + message.getDocument().getFileName() + "; ID=" + message.getDocument().getFileId() + "\n");
    }
    if(message.hasInvoice()) {
      extractedMessage.append("Invoice: " + message.getInvoice().getTitle() + "\n");
    }
    if(message.hasLocation()) {
      extractedMessage.append("Location: LAT=" + message.getLocation().getLatitude() + ", LONG=" + message.getLocation().getLongitude() + "\n");
    }
    if(message.getAudio() != null) {
      extractedMessage.append("AudioID: " + message.getAudio().getFileId() + "\n");
    }
    if(message.getContact() != null) {
      String contactString = buildContactString(message.getContact());
      extractedMessage.append("Contact: " + contactString + "\n");
    }
    if(message.getVoice() != null) {
      extractedMessage.append("VoiceID: " + message.getVoice().getFileId() + "\n");
    }
    if(message.getVideo() != null) {
      extractedMessage.append("VideoID: " + message.getVideo().getFileId() + "\n");
    }
    if(message.getVideoNote() != null) {
      extractedMessage.append("VideoNoteID: " + message.getVideoNote().getFileId() + "\n");
    }
    if(message.getCaption() != null) {
      extractedMessage.append("Caption: " + message.getCaption() + "\n");
    }
    
    int lastIndex = -1;
    if((lastIndex = extractedMessage.lastIndexOf("\n")) > -1 && lastIndex < extractedMessage.length())
      extractedMessage.deleteCharAt(lastIndex);
    
    return extractedMessage;
  }

  private static String buildContactString(Contact contact) {
    String firstName = contact.getFirstName() != null ? contact.getFirstName() : "";
    String lastName = contact.getLastName() != null ? contact.getLastName() : "";
    String phoneNumber = contact.getPhoneNumber() != null ? contact.getPhoneNumber() : "";
    return firstName + " " + lastName + " Number: " + phoneNumber;
  }

}
