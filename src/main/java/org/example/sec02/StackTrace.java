package org.example.sec02;

import org.example.util.CommonUtils;

import java.time.Duration;

public class StackTrace {
  public static void main(String[] args) {
    showCase(Thread.ofPlatform());
    showCase(Thread.ofVirtual().name("VT-", 1));
    CommonUtils.sleep(Duration.ofSeconds(3));
  }

  public static void showCase(Thread.Builder builder) {
    for (int i = 0; i < 20; i++) {
      int temp = i;
      builder.start(() -> Task.execute(temp));
    }
  }
}
