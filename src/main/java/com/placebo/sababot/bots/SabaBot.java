package com.placebo.sababot.bots;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.utils.RNGHandler;
import com.placebo.sababot.utils.ReplyDispatcher;import com.vdurmont.emoji.EmojiParser;

public class SabaBot extends TelegramLongPollingBot {

  private static final Properties PROPS = new Properties();

  static {
    try {
      PROPS.load(ClassLoader.class.getResourceAsStream("/META-INF/user.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getBotUsername() {
    return PROPS.getProperty("user");
  }

  public void onUpdateReceived(Update update) {
    if(update != null) {
      if(update.hasMessage()) {
        boolean actionPerformed = false;

        Long chatId = update.getMessage().getChatId();
        User userFrom = update.getMessage().getFrom();

        String from = null;

        if(userFrom != null)
          from = userFrom.getFirstName() + " '" + userFrom.getUserName() + "'" + ((userFrom.getLastName() != null) ? (" " + userFrom.getLastName()) : "") ;

        if(userFrom != null && ("Mizaroth".equals(userFrom.getUserName())|| "cbarbato".equals(userFrom.getUserName()) )&& update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() == null) {
          if(update.getMessage().hasPhoto()) {
            SendPhoto sp = new SendPhoto();
            sp.setPhoto(update.getMessage().getPhoto().get(0).getFileId());
            sp.setCaption("File ID: " + update.getMessage().getPhoto().get(0).getFileId());
            sp.setChatId(chatId);
            try {
              sendPhoto(sp);
              actionPerformed = true;
            } catch (TelegramApiException e) {
              e.printStackTrace();
            }
          }

          if(update.getMessage().getVoice() != null) {
            SendVoice sv = new SendVoice();
            sv.setVoice(update.getMessage().getVoice().getFileId());
            sv.setCaption("File ID: " + update.getMessage().getVoice().getFileId());
            sv.setChatId(chatId);
            try {
              sendVoice(sv);
              actionPerformed = true;
            } catch (TelegramApiException e) {
              e.printStackTrace();
            }
          }
        }

        //33% of sending hilarious audio
        if(RNGHandler.procByPercentage(10)) {
          SendVoice sv = new SendVoice();
          sv.setChatId(chatId);
          sv.setVoice((String)ReplyDispatcher.reply(ReactionConstants.VOICE_RECORDINGS));
          try {
            sendVoice(sv);
            actionPerformed = true;
          } catch (TelegramApiException e) {
            e.printStackTrace();
          }
        }

        String message = update.getMessage().getText();

        if(message != null) {
          String messageCapitalized = message.toUpperCase();

          //1% of mOcKiNg YoUr RePlY
          if(RNGHandler.procByPercentage(1)) {
            SendMessage sm = new SendMessage(chatId, ReplyDispatcher.mockReply(message));
            try {
              execute(sm);
              actionPerformed = true;
            } catch (TelegramApiException e) {
              e.printStackTrace();
            }
          }

          if(messageCapitalized.contains(ReactionConstants.BARZOTTA) || messageCapitalized.contains(ReactionConstants.BARZOTTO)) {
            SendMessage sm = new SendMessage(chatId, ReplyDispatcher.reply(ReactionConstants.BARZOTT_REPLY));
            try {
              execute(sm);
              actionPerformed = true;
            } catch (TelegramApiException e) {
              e.printStackTrace();
            }
          } else if(messageCapitalized.contains(ReactionConstants.ANIME) || messageCapitalized.contains(ReactionConstants.LOLI)) {
            SendMessage sm = new SendMessage(chatId, ReplyDispatcher.reply(ReactionConstants.ANIME_REPLY));
            try {
              execute(sm);
              actionPerformed = true;
            } catch(TelegramApiException e) {
              e.printStackTrace();
            }
          } else if(containsOneOf(messageCapitalized, ReactionConstants.SABATO)) {
            SendMessage sm = new SendMessage(chatId, ReplyDispatcher.reply(ReactionConstants.SABATO_REPLY));
            try {
              execute(sm);
              actionPerformed = true;
            } catch(TelegramApiException e) {
              e.printStackTrace();
            }
          }
        }

        if(actionPerformed) {
          System.out.println("Triggered by: " + from + " | Chat: " + ((update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() != null) ? update.getMessage().getChat().getTitle() : "Private Chat" ) + " @ " + Calendar.getInstance().getTime().toString());
        }
      }
    }

  }

  private boolean containsOneOf(String message, String[] triggers) {
    for(String trigger : triggers) {
      if(message.matches("(" + trigger + ")"))
        return true;
    }

    return false;
  }

  @Override
  public String getBotToken() {
    return PROPS.getProperty("token");
  }

}
