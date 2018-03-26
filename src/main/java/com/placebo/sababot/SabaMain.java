package com.placebo.sababot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.placebo.sababot.bots.SabaBot;

/**
 * Hello world!
 *
 */
public class SabaMain {
	private SabaMain() {
		//do NOT.
	}
	public static void main( String[] args ){
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new SabaBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
