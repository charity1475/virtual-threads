package org.example.sec03;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
  public static final Logger log = LoggerFactory.getLogger(Task.class);

  public static long findNthNo(long input){
    if (input<2)
      return input;
    return findNthNo(input-1) + findNthNo(input-2);
  }

  public static void cpuIntensive(int i) {
//    log.info("Starting task: {}", Thread.currentThread());
    var timeTaken = CommonUtils.timer(()-> findNthNo(i));
//    log.info("Ending task, Time: {}", timeTaken);
  }
}
