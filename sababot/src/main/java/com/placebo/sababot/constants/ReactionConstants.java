package com.placebo.sababot.constants;

import com.placebo.sababot.utils.PropsLoader;
import com.vdurmont.emoji.EmojiParser;

public class ReactionConstants {

  private ReactionConstants() {
    //do NOT instantiate
  }

  public static final String SABA_SELFIE = "AgADBAADyqwxG8_Z0VEg3m4PiBPnJlkJmhoABBdEusYhWVvnkBcAAgI";
  public static final String SABA_SELFIE_CAPTION = "Fraatm sto tutt fresh!!";
  public static final String LAVORO_DIFFICILE = "LAVORO DIFFICILE";
  public static final String LAVORO_DIFFICILE_REPLY = "AwADBAADaAQAAi5a4VEBSDmG95c-gwI";
  private static final Object[] VOICE_RECORDINGS = PropsLoader.listProps();
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

  public static Object[] getVoiceRecordings() {
    return VOICE_RECORDINGS.clone();
  }
  
  public static String[] getSabato() {
    return SABATO.clone();
  }
  
  public static String[] getSabatoReply() {
    return SABATO_REPLY.clone();
  }
}
