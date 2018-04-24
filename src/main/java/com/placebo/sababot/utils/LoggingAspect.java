package com.placebo.sababot.utils;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  @Async("logTaskExecutor")
  @Before("@annotation(methodLog)")
  public void logEntry(JoinPoint joinPoint, MethodLog methodLog) throws IllegalAccessException {
    Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
    String logString = buildLogString(joinPoint, methodLog, "ENTRY ");
    log(logger, logString, methodLog.logLevel());
  }
  
  @Async("logTaskExecutor")
  @After("@annotation(methodLog)")
  public void logExit(JoinPoint joinPoint, MethodLog methodLog) throws IllegalAccessException {
    Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
    String logString = buildLogString(joinPoint, methodLog, "EXIT ");
    log(logger, logString, methodLog.logLevel());
  }

  private String buildLogString(JoinPoint joinPoint, MethodLog methodLog, String param) throws IllegalAccessException {
    String signature = joinPoint.getSignature().toString();

    StringBuilder logString = new StringBuilder(param + signature.substring(signature.indexOf(' ')+1, signature.length()))
        .append(methodLog.fields().length > 0 ? buildArgsLogString(joinPoint.getArgs(), methodLog.fields()) : "")
        .insert(0, methodLog.chrono() ? determineStartOrEnd(param) + System.currentTimeMillis() + " " : "");
    return logString.toString();
  }

  private String determineStartOrEnd(String param) {
    if(param.trim().equals("ENTRY"))
      return "START: ";

    if(param.trim().equals("EXIT"))
      return "END: ";

    return "";
  }

  private String buildArgsLogString(Object[] args, String[] fieldsToLog) throws IllegalAccessException {
    Object firstArg = args[0];
    StringBuilder argLogString = new StringBuilder("");
    if(firstArg != null) {
      for(String fieldToLog : fieldsToLog) {
        for(Field field : firstArg.getClass().getDeclaredFields()) {
          field.setAccessible(true);
          if(fieldToLog.equals(field.getName())) {
            if(argLogString.length()==0)
              argLogString.append(" with fields:");
            argLogString.append(" | " + fieldToLog + "={" + field.get(firstArg).toString() + "}");
          }
        }
      }
    }
    return argLogString.toString();
  }

  private void log(Logger logger, String logString, String logLevel) {
    switch(logLevel) {
    case "DEBUG":
      logger.debug(logString);
      break;
    case "INFO":
      logger.info(logString);
      break;
    case "WARN":
      logger.warn(logString);
      break;
    case "ERROR":
      logger.error(logString);
      break;
    default:
      logger.debug(logString);
    }
  }

}
