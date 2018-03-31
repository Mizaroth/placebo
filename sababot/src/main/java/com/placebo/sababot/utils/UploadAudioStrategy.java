package com.placebo.sababot.utils;

import org.telegram.telegrambots.api.objects.Update;

import com.placebo.sababot.bots.SabaBot;
import com.placebo.sababot.constants.TelegramMessageType;

public class UploadAudioStrategy implements UploadFileStrategy {

	@Override
	public boolean uploadFile(SabaBot sabaBot,Update update, Long chatId) {
	  String fileId = update.getMessage().getVoice().getFileId();
	  String caption = "File ID: " + update.getMessage().getVoice().getFileId();
	  boolean actionPerformed = TelegramApiWrapper.send(sabaBot, TelegramMessageType.VOICE, chatId, fileId, caption);
		return actionPerformed;
	}

}
