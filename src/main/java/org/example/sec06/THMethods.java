package org.example.sec06;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class THMethods {
  private static final Logger log = LoggerFactory.getLogger(THMethods.class);

  public static void main(String[] args) throws InterruptedException {
    isVirtual();
  }

  private static void isVirtual() throws InterruptedException {
    var t1 =Thread.ofVirtual().start(()-> {
      CommonUtils.sleep(Duration.ofSeconds(2));
      log.info("called product service");
    });
    var t2 =Thread.ofPlatform().start(()-> {
      CommonUtils.sleep(Duration.ofSeconds(2));
      log.info("called pricing service");
    });
    // join will force the main thread to wait for the two
    t1.join();
    t2.join();
//    log.info("Is t1 virtual: {}", t1.isVirtual());
//    log.info("Is t2 virtual: {}", t2.isVirtual());
//    log.info("Is current thread virtual: {}", Thread.currentThread().isVirtual());
  }
}
