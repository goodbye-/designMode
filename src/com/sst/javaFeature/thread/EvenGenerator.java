//: concurrency/EvenGenerator.java
// When threads collide.
package com.sst.javaFeature.thread;

/**
 * @author shui
 * 对于同一个对象来说，所有的synchronized方法共享一个对象锁，在执行f的时候，所有的其他线程都必须等f结束后才可以执行g--fg都在了synchronized关键字
 * 对于每一个类来说，也有一个锁(class对象的锁)，synchronized static可以防止类范围内对static数据的并发访问
 *
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public synchronized int next() {
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
