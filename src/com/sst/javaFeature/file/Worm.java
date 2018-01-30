package com.sst.javaFeature.file;

//: io/Worm.java
//Demonstrates object serialization.
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import static com.sst.javaFeature.file.Print.*;


//在还原的时候，是不调用worm的构造方法的，包括默认构造方法，所以默认的构造函数是可以不提供的
//这些成员变量都是private的
/*为一个实现 Serializable 接口的父类，编写一个能够序列化的子类 子类将自动的实现序列化 

为一个没有实现 Serializable 接口的父类，编写一个能够序列化的子类 1， 父类要有一个无参的 constructor；
2， 子类要先序列化自身，然后子类要负责序列化父类的域*/ 

//Serializable可以和transient结合使用，而Externalizable就没这个必要了，这在保护某些隐私字段中是很有用的

class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private static Random rand = new Random(47);
    private Data[] d = { new Data(rand.nextInt(10)), new Data(rand.nextInt(10)), new Data(rand.nextInt(10)) };
    private Worm next;
    private transient char c;

    // Value of i == number of segments
    public Worm(int i, char x) {
        print("Worm constructor: " + i);
        c = x;
        if (--i > 0)
            next = new Worm(i, (char) (x + 1));
    }

    public Worm() {
        print("Default constructor");
    }

    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data dat : d)
            result.append(dat);
        result.append(")");
        if (next != null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Worm w = new Worm(6, 'a');
        print("w = " + w);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close(); // Also flushes output
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) in.readObject();
        Worm w2 = (Worm) in.readObject();
        //Worm w22 = (Worm) in.readObject(); //EOFExcption
        print(s + "w2 = " + w2);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
//        bout.flush();
//        bout.close();  都他妈的是空方法
        out2.flush();
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        s = (String) in2.readObject();
        Worm w3 = (Worm) in2.readObject();
        print(s + "w3 = " + w3);
    }
} /*
   * Output: Worm constructor: 6 Worm constructor: 5 Worm constructor: 4 Worm
   * constructor: 3 Worm constructor: 2 Worm constructor: 1 w =
   * :a(853):b(119):c(802):d(788):e(199):f(881) Worm storage w2 =
   * :a(853):b(119):c(802):d(788):e(199):f(881) Worm storage w3 =
   * :a(853):b(119):c(802):d(788):e(199):f(881)
   */// :~
