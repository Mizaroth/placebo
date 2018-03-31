package com.placebo.sababot.utils;

import org.telegram.telegrambots.api.objects.Update;

import com.placebo.sababot.bots.SabaBot;

public interface UploadFileStrategy {
	
	public boolean uploadFile(SabaBot sabaBot, Update update, Long chatId);

}
