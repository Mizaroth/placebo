package com.placebo.sababot.bots;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.utils.RNGHandler;
import com.placebo.sababot.utils.ReplyDispatcher;
import com.placebo.sababot.utils.TelegramApiWrapper;

public class SabaBot extends TelegramLongPollingBot {

	private static final Properties PROPS = new Properties();

	static {
		try {
			PROPS.load(ClassLoader.class.getResourceAsStream("/META-INF/user.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdateReceived(Update update) {
		if(update != null && update.hasMessage()) {
			boolean actionPerformed = false;

			Long chatId = update.getMessage().getChatId();
			User userFrom = update.getMessage().getFrom();

			String from = null;

			if(userFrom != null)
				from = userFrom.getFirstName() + " '" + userFrom.getUserName() + "'" + ((userFrom.getLastName() != null) ? (" " + userFrom.getLastName()) : "") ;

			if(userFrom != null && ("Mizaroth".equals(userFrom.getUserName()) || "cbarbato".equals(userFrom.getUserName()) )&& update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() == null) {
				if(update.getMessage().hasPhoto()) {
					String fileId = update.getMessage().getPhoto().get(0).getFileId();
					String caption = "File ID: " + update.getMessage().getPhoto().get(0).getFileId();
					actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.PHOTO, chatId, fileId, caption);
				}

				if(update.getMessage().getVoice() != null) {
					String fileId = update.getMessage().getVoice().getFileId();
					String caption = "File ID: " + update.getMessage().getVoice().getFileId();
					actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.VOICE, chatId, fileId, caption);
				}
			}

			//10% of sending hilarious audio
			if(RNGHandler.procByPercentage(10)) {
				String fileId = (String)ReplyDispatcher.reply(ReactionConstants.VOICE_RECORDINGS);
				actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.VOICE, chatId, fileId);
			}

			//5% of sending SabaSelfie
			if(RNGHandler.procByPercentage(5)) {
				String fileId = ReactionConstants.SABA_SELFIE;
				String caption = ReactionConstants.SABA_SELFIE_CAPTION;
				actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.PHOTO, chatId, fileId, caption);
			}

			String message = update.getMessage().getText();

			if(message != null) {
				String messageCapitalized = message.toUpperCase();

				//1% of mOcKiNg YoUr RePlY
				if(RNGHandler.procByPercentage(1)) {
					String text = ReplyDispatcher.mockReply(message);
					actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.MESSAGE, chatId, text);
				}

				if(containsOneOf(messageCapitalized, ReactionConstants.SABATO)) {
					String text = ReplyDispatcher.reply(ReactionConstants.SABATO_REPLY);
					actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.MESSAGE, chatId, text);
				}
			}

			if(actionPerformed) {
				System.out.println("Triggered by: " + from + " | Chat: " + ((update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() != null) ? update.getMessage().getChat().getTitle() : "Private Chat" ) + " @ " + Calendar.getInstance().getTime().toString());
			}
		}
	}

	private boolean containsOneOf(String message, String[] triggers) {
		for(String trigger : triggers) {
			if(message.matches(".*\\b" + trigger + "\\b.*"))
				return true;
		}

		return false;
	}

	@Override
	public String getBotUsername() {
		return PROPS.getProperty("user");
	}

	@Override
	public String getBotToken() {
		return PROPS.getProperty("token");
	}

}
