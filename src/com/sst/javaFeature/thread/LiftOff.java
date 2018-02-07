package com.sst.javaFeature.thread;

import java.util.concurrent.TimeUnit;

//: concurrency/LiftOff.java

//Demonstration of the Runnable interface.

/**
 * @author shui
 * 
 * Thread.yield( ) 方法：

使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。
cpu 会从众多的可执行态里选择，也就是说，
当前也就是刚刚的那个线程还是有可能会被再次执行到的，并不是说一定会执行其他线程而该线程在下一次中不会执行到了。
用了 yield 方法后，该线程就会把 CPU 时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
 *
 */
public class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();//
            /*try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
    
    public static void main(String[] args) {
        new Thread(new LiftOff()).start();
        new Thread(new LiftOff()).start();
    }
} /// :~
