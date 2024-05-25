package org.example.sec02;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
  public static final Logger log = LoggerFactory.getLogger(Task.class);
  public static void executeOpOne(int i){
    CommonUtils.sleep(Duration.ofMillis(200));
    try {
      executeOpTwo(i);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public static void executeOpTwo(int i){
    CommonUtils.sleep(Duration.ofMillis(100));
    executeOpThree(i);
  }
  public static void executeOpThree(int i){
    CommonUtils.sleep(Duration.ofMillis(500));
    if (i == 4 ){
      throw new IllegalStateException("I can't be 4");
    }
  }
  public static void execute(int i){
    log.info("[ START ] Task {}",i);
    try{
      executeOpOne(i);
    }catch (Exception e){
      log.error("Error for {} : {} caused by {}",i, e.getMessage(), e.getCause());
    }
    log.info("[ END ] Task {}",i);
  }

}
