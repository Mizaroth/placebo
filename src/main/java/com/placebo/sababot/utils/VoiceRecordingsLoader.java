package com.placebo.sababot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    List<String> props = null;
    
    for(Map.Entry<Object, Object> entry : voiceRecordings.entrySet()) {
      if(props == null)
        props = new ArrayList<>();
      props.add((String)entry.getValue());
    }
    
    return props != null ? props.toArray() : null;
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
