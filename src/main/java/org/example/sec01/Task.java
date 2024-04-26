package org.example.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
  private static final Logger log = LoggerFactory.getLogger(Task.class);

  public static void IOIntensiveTask(int i) {
      try {
        log.info("### STARTING I/O TASK {} ###", i);
      }catch (Exception e){
        throw new RuntimeException(e);
      }
  }
}
