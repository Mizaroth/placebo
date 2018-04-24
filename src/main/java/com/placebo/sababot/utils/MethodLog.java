package com.placebo.sababot.utils;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface MethodLog {
  String[] fields();
  String logLevel() default "DEBUG";
  boolean chrono() default false;
}
