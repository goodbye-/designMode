package com.sst.javaFeature.thread;

class DualSynch {
	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {//改成this会阻塞
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
		
		
		String aString = (new String("111") + new String("222")).intern();
		String bString = new String("111222").intern();
		System.out.println(aString == bString);
	}
} /*
	 * Output: (Sample) g() f() g() f() g() f() g() f() g() f()
	 */// :~
