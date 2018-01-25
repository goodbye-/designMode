package com.sst.javaFeature.file;

//: io/IntBufferDemo.java
//Manipulating ints in a ByteBuffer with an IntBuffer
import java.nio.*;

public class IntBufferDemo {
    private static final int BSIZE = 28;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        System.out.println(bb.limit());
        IntBuffer ib = bb.asIntBuffer();
        System.out.println(ib.limit());
        // Store an array of int:
        System.out.println(ib.position() + "==p");
        ib.put(new int[] { 11, 42, 47, 99, 143, 811, 1016 });//put操作会执行position++
        System.out.println(ib.limit());
        System.out.println(ib.position() + "==p");
        // Absolute location read and write:
        System.out.println(ib.get(3));
        System.out.println(ib.limit());
        ib.put(3, 1811);
        System.out.println(ib.limit());
        // Setting a new limit before rewinding the buffer.
        //ib.flip();
        while (ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }
    }
} /*
   * Output: 99 11 42 47 1811 143 811 1016
   */// :~
