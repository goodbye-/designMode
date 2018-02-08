package com.sst.javaFeature.thread;

//: concurrency/ResponsiveUI.java
//User interface responsiveness.
//{RunByHand}

class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws Exception {
        while (d > 0)
            d = d + (Math.PI + Math.E) / d;
        System.in.read(); // Never gets here
    }
}

public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        //setDaemon(true);
        start();
    }

    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
            d = 1/0;
        }
    }

    public static void main(String[] args) throws Exception {
        // ! new UnresponsiveUI(); // Must kill this process
        try {
            new ResponsiveUI();
        } catch (Exception e) {
            System.out.println("异常已经捕获");//无法捕获异常
        }
        System.in.read();
        System.out.println(d + "====="); // Shows progress
    }
} /// :~
