package com.sst.sington;
public class Singleton {
    private Singleton() {}  //私有构造函数
    //如果单例初始值是 null，还未构建，则构建单例对象并返回。这个写法属于单例模式当中的懒汉模式。
    //如果单例对象一开始就被 new Singleton() 主动构建，则不再需要判空操作，这种写法属于饿汉模式。
    private static Singleton instance = null;  //单例对象
    //静态工厂方法
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
