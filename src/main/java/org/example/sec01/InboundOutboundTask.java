package org.example.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTask {
  public static final int MAX_PLATFORM = 20;

  public static void main(String[] args) {
//    platformThread();
//    modernThread();
    virtualThread();
  }
//  Traditional way to create a thread
  private static void platformThread(){
    for (int i = 0; i < MAX_PLATFORM; i++) {
      int temp = i;
      Thread thread = new Thread(()->Task.IOIntensiveTask(temp));
      thread.start();
    }
  }

//  Modern way to create a thread
  private static void modernThread(){
    var builder = Thread.ofPlatform().name("MTG = ", 1);
    for (int i = 0; i < MAX_PLATFORM; i++) {
      int temp = i;
      Thread thread = builder.unstarted(()->Task.IOIntensiveTask(temp));
      thread.start();
    }
  }
// Async Await pattern
  private static void asyncAwait(){
    var latch = new CountDownLatch(MAX_PLATFORM);
    var builder = Thread.ofPlatform().name("MTG = ", 1);

    for (int i = 0; i < MAX_PLATFORM; i++) {
      int temp = i;
      Thread thread = builder.unstarted(()->{
        Task.IOIntensiveTask(temp);
        latch.countDown();
      });
      thread.start();
    }
      try {
          latch.await();
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }

//  Virtual threads, daemon by default
  private static void virtualThread() {
    var builder = Thread.ofVirtual().name("VTH: ",1);
    var latch = new CountDownLatch(MAX_PLATFORM);
    for (int i = 0; i < MAX_PLATFORM; i++) {
      int temp = i+1;
      Thread thread = builder.unstarted(()->{
        Task.IOIntensiveTask(temp);
        latch.countDown();
      });
      thread.start();
    }
      try {
          latch.await();
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }
}
