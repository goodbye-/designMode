package com.sst.javaFeature.file;

//: io/Blips.java
//Simple use of Externalizable & a pitfall.
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import static com.sst.javaFeature.file.Print.*;

//Blip1和2的区别就在于2的构造器不是public的，no valid constructor
//实现这个接口在反序列化的时候是需要调用默认的构造函数的,不提供默认的也不行，即使不提供会有默认的，好像是必须显示提供
//readExternal方法在构造函数之后


class Blip1 implements Externalizable {
    public Blip1() {
        print("Blip1 Constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip1.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
  /*  Blip2() {
        print("Blip2 Constructor");
    }
    
    public Blip2(int x) {
        print(x);
        print("Blip2 Constructor");
    }*/

    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip2.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip2.readExternal");
    }
}

public class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        print("Recovering b1:");
        b1 = (Blip1) in.readObject();
        // OOPS! Throws an exception:
        print("Recovering b2:");
        b2 = (Blip2)in.readObject();
    }
} /*
   * Output: Constructing objects: Blip1 Constructor Blip2 Constructor Saving
   * objects: Blip1.writeExternal Blip2.writeExternal Recovering b1: Blip1
   * Constructor Blip1.readExternal
   */// :~
