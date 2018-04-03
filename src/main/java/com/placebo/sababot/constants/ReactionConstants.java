package com.placebo.sababot.constants;

import com.placebo.sababot.utils.VoiceRecordingsLoader;
import com.vdurmont.emoji.EmojiParser;

public class ReactionConstants {

  private ReactionConstants() {
    //do NOT instantiate
  }

  public static final String SABA_SELFIE = "AgADBAADyqwxG8_Z0VEg3m4PiBPnJlkJmhoABBdEusYhWVvnkBcAAgI";
  public static final String SABA_SELFIE_CAPTION = "Fraatm sto tutt fresh!!";
  public static final String LAVORO_DIFFICILE = "LAVORO DIFFICILE";
  public static final String LAVORO_DIFFICILE_REPLY = "AwADBAADaAQAAi5a4VEBSDmG95c-gwI";
  private static final Object[] VOICE_RECORDINGS = VoiceRecordingsLoader.listVoiceRecordings();
  private static final String[] SABATO = {
      "SABA",
      "SABATINO",
      "SABATO",
      "SAB",
      "SABB",
      "SABBA",
      "SABBATINO",
      "FRA"
  };
  private static final String[] SABATO_REPLY = {
      "Cre frà?",
      "C sfaccimm vuò fra?",
      "Cre fratellò",
      "Fraaatm",
      "Fraaavl",
      EmojiParser.parseToUnicode("Fragolaaaaa :strawberry: :strawberry:"),
      "Jooooooo "
  };
  private static final String[] SABA_FORECAST = {
      "CALDO",
      "FREDDO",
      "CALD",
      "CAVR",
      "FRIDD",
      "SOLE",
      "PIOGGIA",
      "PIOVE",
      "NEVE",
      "NEVICA",
      "PIOGG",
      "SCHIZZICHEA",
      "NUVOLE",
      "TEMPORALE",
      "TEMPESTA",
      "CHIOV",
      "OMBRELLO",
      "CALDISSIMO",
      "FREDDISSIMO",
      "CALDISSIM",
      "FREDDISSIM"
  };
  public static final String SABA_FORECAST_REPLY = "Qua fanno %d° gradi, c sfaccimm";
  public static final String SABA_FORECAST_PIOVE_ADD = "E chiov pur..";
  public static final String SABA_FORECAST_SOLE_ADD = "Ce sta nu sol esagerat..";

  public static Object[] getVoiceRecordings() {
    return VOICE_RECORDINGS.clone();
  }
  
  public static String[] getSabato() {
    return SABATO.clone();
  }
  
  public static String[] getSabatoReply() {
    return SABATO_REPLY.clone();
  }
  
  public static String[] getSabaForecast() {
    return SABA_FORECAST.clone();
  }
}
