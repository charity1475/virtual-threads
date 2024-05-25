package org.example.sec03;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CPUTask {
  private static  final Logger log = LoggerFactory.getLogger(CPUTask.class);
  private static final int TASK_COUNT = 7 * Runtime.getRuntime().availableProcessors();

  public static void main(String[] args) {
    log.info("Task Count: {}", TASK_COUNT);
    for (int i = 0; i < 5; i++) {
      log.info("###########################################################");
      var totalTimeVT = CommonUtils.timer(()->{
        simulate(Thread.ofVirtual());
      });
      log.info("Total Time VT: {} MS", totalTimeVT);
      var totalTimePT = CommonUtils.timer(()->{
        simulate(Thread.ofPlatform());
      });
      log.info("Total Time PT: {} MS", totalTimePT);
    }
  }

  private static void  simulate(Thread.Builder builder){
    var latch = new CountDownLatch(TASK_COUNT);

    for (int i = 0; i < TASK_COUNT; i++) {
      int finalI = i;
      builder.start(()->{
        Task.cpuIntensive(finalI);
        latch.countDown();
      });
    }

      try {
          latch.await();
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }
}
