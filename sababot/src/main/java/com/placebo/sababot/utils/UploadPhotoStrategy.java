package com.placebo.sababot.utils;

import org.telegram.telegrambots.api.objects.Update;

import com.placebo.sababot.bots.SabaBot;
import com.placebo.sababot.constants.TelegramMessageType;

public class UploadPhotoStrategy implements UploadFileStrategy {

	@Override
	public boolean uploadFile(SabaBot sabaBot,Update update, Long chatId) {
		String fileId = update.getMessage().getPhoto().get(0).getFileId();
		String caption = "File ID: " + update.getMessage().getPhoto().get(0).getFileId();
		boolean actionPerformed = TelegramApiWrapper.send(sabaBot, TelegramMessageType.PHOTO, chatId, fileId, caption);
		return actionPerformed;
	}

}
