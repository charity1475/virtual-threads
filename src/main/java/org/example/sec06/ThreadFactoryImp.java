package org.example.sec06;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryImp {
  private static final Logger log = LoggerFactory.getLogger(ThreadFactoryImp.class);

  public static void main(String[] args) {
    /* each thread creates one child thread: use case data aggregation */
    demo(Thread.ofVirtual().name("THX", 1).factory());
    CommonUtils.sleep(Duration.ofSeconds(3));
  }

  public static void demo(ThreadFactory factory) {
    for (int i = 0; i < 50; i++) {
      Thread thread = factory.newThread((() -> {
        log.info("Task started:  {}", Thread.currentThread());
        Thread childThread = factory.newThread((() -> {
          log.info("Child Task started:  {}", Thread.currentThread());
          CommonUtils.sleep(Duration.ofSeconds(2));
          log.info("Child Task ended: {}", Thread.currentThread());
        }));
        childThread.start();
        log.info("Task ended: {}", Thread.currentThread());
      }));
//      builder.start(()->{
//        log.info("Task started:  {}", Thread.currentThread());
//        log.info("Task ended: {}", Thread.currentThread());
//      });
      thread.start();
    }
  }
}
