package com.sst.javaFeature.thread;

import java.util.ArrayList;
//: concurrency/CallableDemo.java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author shui
 *  get方法会阻塞
 *  exec.shutdown();不能网鞋，否则main方法都不会停止
 *
 */
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2*1000);
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> fs : results)
            try {
                // get() blocks until completion:
                System.out.println(fs.isDone());
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }
} /*
   * Output: result of TaskWithResult 0 result of TaskWithResult 1 result of
   * TaskWithResult 2 result of TaskWithResult 3 result of TaskWithResult 4
   * result of TaskWithResult 5 result of TaskWithResult 6 result of
   * TaskWithResult 7 result of TaskWithResult 8 result of TaskWithResult 9
   */// :~
