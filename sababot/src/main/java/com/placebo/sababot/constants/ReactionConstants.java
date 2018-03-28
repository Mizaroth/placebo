package com.placebo.sababot.constants;

import com.placebo.sababot.utils.PropsLoader;
import com.vdurmont.emoji.EmojiParser;

public interface ReactionConstants {

  String[] SABATO = {
      "SABA",
      "SABATINO",
      "SABATO",
      "SAB",
      "SABB",
      "SABBA",
      "SABBATINO",
      "FRA"
  };
  
  String[] SABATO_REPLY = {
      "Cre frà?",
      "C sfaccimm vuò fra?",
      "Cre fratellò",
      "Fraaatm",
      "Fraaavl",
      EmojiParser.parseToUnicode("Fragolaaaaa :strawberry: :strawberry:"),
      "Jooooooo "
  };

  String SABA_SELFIE = "AgADBAADyqwxG8_Z0VEg3m4PiBPnJlkJmhoABBdEusYhWVvnkBcAAgI";
  String SABA_SELFIE_CAPTION = "Fraatm sto tutt fresh!!";
  
  Object[] VOICE_RECORDINGS = PropsLoader.listProps();

}
