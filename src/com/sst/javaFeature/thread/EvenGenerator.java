//: concurrency/EvenGenerator.java
// When threads collide.
package com.sst.javaFeature.thread;

public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public int next() {
        ++currentEvenValue; // Danger point here!
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++currentEvenValue;//即使递增操作也不是原子操作，但是如果不加sleep方法，两个对成员变量i操作的++操作，
                           //居然运行了好几次都没有看到奇数的产生
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
} /*
   * Output: (Sample) Press Control-C to exit 89476993 not even! 89476993 not
   * even!
   */// :~
