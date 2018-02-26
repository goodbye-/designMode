package com.sst.javaFeature.thread;

//: concurrency/AtomicityTest.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 76740
 * 本例子只有一个线程操作i，所以不存在多个线程之间的可视性问题
 *
 */
public class AtomicityTest implements Runnable {
	private volatile int i = 0;  //volatile 只保证了原子性(也只是针对long和double的类型)和可见性，并没有同步

	public /*synchronized*/ int getValue() {//两个同步方法是可以保持原子性和可见性的
		return i;//在返回i的时候，可能会执行到两个i++之间
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	public void run() {
		while (true)
			evenIncrement();
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
} /*
	 * Output: (Sample) 191583767
	 */// :~
