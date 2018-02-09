package com.sst.javaFeature.thread;

//: concurrency/AttemptLocking.java
//Locks in the concurrent library allow you
//to give up on trying to acquire a lock.
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        final AttemptLocking al = new AttemptLocking();
        al.untimed(); // True -- lock is available
        al.timed(); // True -- lock is available
        // Now create a separate task to grab the lock:
        new Thread() {
            {
                setDaemon(true);
            }

            public void run() {
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.sleep(1*1000); // Give the 2nd task a chance
        al.untimed(); // False -- lock grabbed by task
        al.timed(); // False -- lock grabbed by task
        Thread.sleep(10*1000);
    }
} /*
   * Output: tryLock(): true tryLock(2, TimeUnit.SECONDS): true acquired
   * tryLock(): false tryLock(2, TimeUnit.SECONDS): false
   */// :~
