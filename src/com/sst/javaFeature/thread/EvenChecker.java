package com.sst.javaFeature.thread;

//: concurrency/EvenChecker.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "====" + generator.isCanceled() + " === " + generator.next());
        while (!generator.isCanceled()) {
            int val = generator.next();
            System.out.println(Thread.currentThread().getName() + "====" +val);
            if (val % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "====" + val + " not even!");
                generator.cancel(); // Cancels all EvenCheckers
            }
        }
    }

    // Test any type of IntGenerator:
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++)
            exec.execute(new EvenChecker(gp, i));
        exec.shutdown();
    }

    // Default value for count:
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
} /// :~
