package com.sst.javaFeature.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



/*最后的输出是 10，对于无法理解的读者认为，打印的 staticVar 是从读取的对象里获得的，
应该是保存时的状态才对。之所以打印 10 的原因在于序列化时，并不保存静态变量，这其实比较容易理解，
序列化保存的是对象的状态，静态变量属于类的状态，因此 序列化并不保存静态变量*/


/*父类的序列化与 Transient 关键字
情境：一个子类实现了 Serializable 接口，它的父类都没有实现 Serializable 接口，序列化该子类对象，然后反序列化后输出父类定义的某变量的数值，该变量数值与序列化时的数值不同。

解决：要想将父类对象也序列化，就需要让父类也实现 Serializable 接口。如果父类不实现的话的，就 需要有默认的无参的构造函数。在父类没有实现 Serializable 接口时，虚拟机是不会序列化父对象的，而一个 Java 对象的构造必须先有父对象，才有子对象，反序列化也不例外。所以反序列化时，为了构造父对象，只能调用父类的无参构造函数作为默认的父对象。因此当我们取父对象的变量值时，它的值是调用父类无参构造函数后的值。如果你考虑到这种序列化的情况，在父类无参构造函数中对变量进行初始化，否则的话，父类变量值都是默认声明的值，如 int 型的默认是 0，string 型的默认是 null。
----他说的对。。。。。很扎心，之前自所以觉得对是因为我给了他初始值
Transient 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。*/


class Parent {
    public String address = "aaaaaa";
    private String name = "p_name";
    private Integer age = 30;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Parent() {
        System.out.println("parent=====================");
    }
    
    public Parent(int age) {
        this.age = age;
       System.out.println("parent++++++++++++++++++");
    }
}
public class Test extends Parent implements Serializable {
   public Test() {
        super(100000);
    }

    /* public Test() {
        System.out.println("test+++++++++++++++++++++++");
    }*/
    public Integer age = 199;

    private static final long serialVersionUID = 1L;

    public static int staticVar = 7;

    public static void main(String[] args) {
        try {
            // 初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
            out.writeObject(new Test());
            out.close();

            // 序列化后修改为10
            //Test.staticVar = 10;//不管在什么位置都是10

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
            Test t = (Test) oin.readObject();
            oin.close();

            // 再读取，通过t.staticVar打印新的值
            System.out.println(t.staticVar);
            System.out.println(t.getName() + t.getAge() + t.address);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}