package com.sst.javaFeature.thread;

//: concurrency/SimpleDaemons.java
//Daemon threads don't prevent the program from ending.
import java.util.concurrent.TimeUnit;

/**
 * @author shui
 * 只要main线程不结束，daemon线程就会一直执行,一旦main方法停止，jvm会关掉所有线程，即使daemon中有finally也不能保证会执行
 * ThreadFactory Executors创建线程池时的可选参数，对其中的线程做定制处理
 */
public class SimpleDaemons implements Runnable {
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
} /*
   * Output: (Sample) All daemons started Thread[Thread-0,5,main]
   * SimpleDaemons@530daa Thread[Thread-1,5,main] SimpleDaemons@a62fc3
   * Thread[Thread-2,5,main] SimpleDaemons@89ae9e Thread[Thread-3,5,main]
   * SimpleDaemons@1270b73 Thread[Thread-4,5,main] SimpleDaemons@60aeb0
   * Thread[Thread-5,5,main] SimpleDaemons@16caf43 Thread[Thread-6,5,main]
   * SimpleDaemons@66848c Thread[Thread-7,5,main] SimpleDaemons@8813f2
   * Thread[Thread-8,5,main] SimpleDaemons@1d58aae Thread[Thread-9,5,main]
   * SimpleDaemons@83cc67 ...
   */// :~
