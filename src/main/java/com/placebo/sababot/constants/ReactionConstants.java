package com.placebo.sababot.constants;

import com.placebo.sababot.utils.PropsLoader;
import com.vdurmont.emoji.EmojiParser;

public interface ReactionConstants {
  String BARZOTTO = "BARZOTTO";
  String BARZOTTA = "BARZOTTA";
  String BARZOTT_REPLY = "hey mamma, sei fica!";

  String ANIME = "ANIME";
  String LOLI = "LOLI";
  String[] ANIME_REPLY = {
      "bho alla fine 2d e' meglio di 3d",
      "non ce niente di male a volere una waifu, e' naturale",
      "angel beats e' tipo interstellar.. non so se l'avete visto ma lo dovete guardare\nhttps://www.youtube.com/watch?v=zIFV8UUs1-c"
  };

  String[] SABATO = {
      "SABA",
      "SABATINO",
      "SABATO",
      "SAB",
      "SABB",
      "SABBA",
      "SABBATINO"
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

  Object[] VOICE_RECORDINGS = PropsLoader.listProps();

}
