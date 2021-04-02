package com.freaky.fit.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public final class StackTraceUtil {
  private StackTraceUtil() {}

  public static String getStackTrace(Throwable ex) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    String sStackTrace = "( Exception stack trace :-  " + sw.toString() + ")";
    return sStackTrace.replaceAll("\n", " ");
  }
}
