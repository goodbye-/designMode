package com.sst.sington;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton {
    private Singleton() {
    } // 私有构造函数
    // 如果单例初始值是 null，还未构建，则构建单例对象并返回。这个写法属于单例模式当中的懒汉模式。
    // 如果单例对象一开始就被 new Singleton() 主动构建，则不再需要判空操作，这种写法属于饿汉模式。

    private static Singleton instance = null; // 单例对象
    // 静态工厂方法

    public static Singleton getInstance() {
        if (instance == null) {// 线程不安全
            instance = new Singleton();
        }
        return instance;
    }
    
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        ReflectMethod.getObjectByReflect(Singleton2.class);
    }
}

class Singleton1 {
    private Singleton1() {
    } // 私有构造函数

    private static Singleton1 instance = null; // 单例对象
    // 静态工厂方法

    //仍然不是绝对的安全，具体就是Instance= new Singletion1()这句话，
   /* 这种情况表面看似没什么问题，要么 Instance 还没被线程 A 构建，线程 B 执行 if（instance == null）的时候得到 false；要么 Instance 已经被线程 A 构建完成，线程 B 执行 if（instance == null）的时候得到 true。
            真的如此吗？答案是否定的。这里涉及到了 JVM 编译器的指令重排。
            指令重排是什么意思呢？比如 java 中简单的一句 instance = new Singleton，会被编译器编译成如下 JVM 指令：
            memory =allocate();    //1：分配对象的内存空间 
            ctorInstance(memory);  //2：初始化对象 
            instance =memory;     //3：设置 instance 指向刚分配的内存地址 
            但是这些指令顺序并非一成不变，有可能会经过 JVM 和 CPU 的优化，指令重排成下面的顺序：
            memory =allocate();    //1：分配对象的内存空间 
            instance =memory;     //3：设置 instance 指向刚分配的内存地址 
            ctorInstance(memory);  //2：初始化对象 
            当线程 A 执行完 1,3, 时，instance 对象还未完成初始化，但已经不再指向 null。此时如果线程 B 抢占到 CPU 资源，执行  if（instance == null）的结果会是 false，从而返回一个没有初始化完成的 instance 对象。
          如何避免这一情况呢？我们需要在 instance 对象前面增加一个修饰符 volatile。      
    The volatile keyword indicates that a value may change between different accesses,
    it prevents an optimizing compiler from optimizing away subsequent reads or writes and thus incorrectly reusing a stale value or omitting writes.

    即便如此，通过反射还是可以获取到构造函数，构造不同的实力对象


*/
    public static Singleton1 getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (Singleton.class) { // 同步锁
                if (instance == null) { // 双重检测机制
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}

/**
 * @author shui
 * 通过静态内部类来实现，利用类加载机制
 * 这里有几个需要注意的点：
1、从外部无法访问静态内部类 LazyHolder，只有当调用 Singleton.getInstance 方法的时候，才能得到单例对象 INSTANCE。
2、INSTANCE 对象初始化的时机并不是在单例类 Singleton 被加载的时候，而是在调用 getInstance 方法，使得静态内部类 LazyHolder 被加载的时候。因此这种实现方式是利用 classloader 的加载机制来实现懒加载，并保证构建单例的线程安全。
3.不能阻止通过反射来构建对象
 */
class Singleton2 {
    private static class LazyHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }
    private Singleton2 (){}
    public static Singleton2 getInstance() {
        return LazyHolder.INSTANCE;
    }
}

class ReflectMethod{
    public static void getObjectByReflect(Class<?> clazz) throws Exception, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
      //获得构造器
        Constructor<?> con = clazz.getDeclaredConstructor();
        //设置为可访问
        con.setAccessible(true);
        //构造两个不同的对象
        Object singleton1 = con.newInstance();
        Object singleton2 = con.newInstance();
        //验证是否是不同对象
        System.out.println(singleton1.equals(singleton2));
    }
}
enum SingletonEnum {
    INSTANCE;
}
