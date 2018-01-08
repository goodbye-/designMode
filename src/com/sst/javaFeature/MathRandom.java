package com.sst.javaFeature;

import java.util.Random;

/**
 * @author shui java random的用法
 */
public class MathRandom {
    public static void main(String[] args) {
        System.out.println(Math.random());
        //相同的种子产生的随机数是一样的
        Random r1 = new Random(20);
        Random r2 = new Random(20);
        for (int i = 0; i < 10; i++) {
            System.out.println(r1.nextLong());
            System.out.println(r2.nextLong());
            System.out.println("int=============");
            System.out.println(r1.nextInt(1));
            System.out.println(r2.nextInt(1));
            System.out.println(r1.nextBoolean());
            System.out.println(r2.nextBoolean());
            System.out.println(r1.nextDouble());
            System.out.println(r2.nextDouble());
            System.out.println(r1.nextFloat());
            System.out.println(r2.nextFloat());
        }
    }
}
