package com.placebo.sababot.bots;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.utils.RNGHandler;
import com.placebo.sababot.utils.ReplyDispatcher;
import com.placebo.sababot.utils.TelegramApiWrapper;
import com.placebo.sababot.utils.UploadFileStrategy;

public class SabaBot extends TelegramLongPollingBot {
	private static final Properties PROPS = new Properties();
	private static final Logger LOGGER = Logger.getLogger(SabaBot.class);
	@Autowired
	private Map<String, UploadFileStrategy> strategyMap;

	static {
		try {
			PROPS.load(ClassLoader.class.getResourceAsStream("/META-INF/user.properties"));
		} catch (IOException e) {
			LOGGER.error("Error while trying to load user.properties:", e);
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
				actionPerformed = manageUserFeatures(update, actionPerformed, chatId);
			}

			//10% of sending hilarious audio
			if(RNGHandler.procByPercentage(10)) {
				String fileId = (String)ReplyDispatcher.reply(ReactionConstants.getVoiceRecordings());
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

				if(messageCapitalized.contains(ReactionConstants.LAVORO_DIFFICILE)) {
					String fileId = ReactionConstants.LAVORO_DIFFICILE_REPLY;
					actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.VOICE, chatId, fileId);
				}

				if(containsOneOf(messageCapitalized, ReactionConstants.getSabato())) {
					String text = ReplyDispatcher.reply(ReactionConstants.getSabatoReply());
					actionPerformed = TelegramApiWrapper.send(this, TelegramMessageType.MESSAGE, chatId, text);
				}
			}

			if(actionPerformed) {
				LOGGER.info("Triggered by: " + from + " | Chat: " + ((update.getMessage().getChat() != null && update.getMessage().getChat().getTitle() != null) ? update.getMessage().getChat().getTitle() : "Private Chat" ));
			}
		}
	}

	private boolean manageUserFeatures(Update update, boolean actionPerformed, Long chatId) {
		if(update.getMessage().hasPhoto()) {
			UploadFileStrategy strategy = strategyMap.get("PHOTO");
			actionPerformed=strategy.uploadFile(this, update, chatId);
		}

		if(update.getMessage().getVoice() != null) {
			UploadFileStrategy strategy = strategyMap.get("AUDIO");
			actionPerformed=strategy.uploadFile(this, update, chatId);
		}
		return actionPerformed;
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

	public void setStrategyMap(Map<String, UploadFileStrategy> strategyMap) {
		this.strategyMap = strategyMap;
	}
}
