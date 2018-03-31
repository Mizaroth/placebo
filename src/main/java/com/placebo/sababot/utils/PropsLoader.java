package com.placebo.sababot.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropsLoader {

  private static final Properties PROPS = new Properties();

  static {
    try {
      PROPS.load(ClassLoader.class.getResourceAsStream("/META-INF/replies.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PropsLoader(String path) {
    //OK
  }

  public static Object[] listProps() {
    List<String> props = null;
    
    for(Map.Entry<Object, Object> entry : PROPS.entrySet()) {
      if(props == null)
        props = new ArrayList<>();
      props.add((String)entry.getValue());
    }
    
    return props != null ? props.toArray() : null;
  }
  
  public static String get(String key) {
    return PROPS.getProperty(key);
  }

}
