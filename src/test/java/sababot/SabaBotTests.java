package sababot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.placebo.sababot.bots.SabaBot;

public class SabaBotTests {

  private SabaBot sabaBot;
  
  @Before
  public void setUp() throws Exception {
    sabaBot = new SabaBot();
  }

//  @Test
//  public void testGetBotUsername() {
//    assertEquals("The username should be Sabato Sodano.", "Sabato Sodano", sabaBot.getBotUsername());
//  }
//
//  @Test
//  public void testGetBotToken() {
//    assertEquals("The token should be 560173849:AAFYS3PC6mgPsy6h-VXE3IUl06vUR6xMvnM", "560173849:AAFYS3PC6mgPsy6h-VXE3IUl06vUR6xMvnM", sabaBot.getBotToken());
//  }

}
