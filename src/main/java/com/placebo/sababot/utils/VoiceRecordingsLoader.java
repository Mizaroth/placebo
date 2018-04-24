package com.placebo.sababot.utils;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VoiceRecordingsLoader {
  private static Properties voiceRecordings;

  private VoiceRecordingsLoader() {
    //do NOT instantiate
  }

  public static Object[] listVoiceRecordings() {
    return voiceRecordings.entrySet().stream()
        .map(Entry::getValue)
        .toArray();
  }

  public static String get(String key) {
    return voiceRecordings.getProperty(key);
  }

  @Autowired
  @Qualifier("voiceRecordings")
  public void setVoiceRecordings(Properties voiceRecordings) {
    VoiceRecordingsLoader.voiceRecordings = voiceRecordings;
  }

}
