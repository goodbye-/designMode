package com.sst.javaFeature.file;

//: io/SerialCtl.java
//Controlling serialization by adding your own
//writeObject() and readObject() methods.
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


//1.注意writeObject的方法签名  private
//2. /** class-defined readObject method, or null if none */
//private Method readObjectMethod;  lookup在生成类描述器的时候会去判断private类型的这个名字的方法，
//如果有在执行writeObject的时候会有不同的实现

public class SerialCtl implements Serializable {
    private String a;
    private transient String b;

    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    public String toString() {
        return a + "\n" + b;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(b);
        stream.defaultWriteObject();//顺序无关
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String) stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" + sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc);
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtl sc2 = (SerialCtl) in.readObject();
        System.out.println("After:\n" + sc2);
    }
} /*
   * Output: Before: Not Transient: Test1 Transient: Test2 After: Not Transient:
   * Test1 Transient: Test2
   */// :~
