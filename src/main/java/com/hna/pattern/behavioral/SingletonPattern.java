package com.hna.pattern.behavioral;

/**
 * 推荐使用枚举法实现单例，好处是线程安全，防止反射强制调用构造器，提供自动序列化机制防止反序列化时创建新对象
 * 其他方式都有两个共同缺点：
 * 1.都需要额外的工作(Serializable、transient、readResolve())来实现序列化，否则每次反序列化一个序列化的对象实例时都会创建一个新的实例
 * 2.可能会有人使用反射强行调用我们的私有构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）
 * 最后 可以通过自定义类加载器来生成新的实例
 */
public class SingletonPattern {

    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.err.println(s1 == s2);

        Singleton6 s3 = Singleton6.INSTANCE;
        Singleton6 s4 = Singleton6.INSTANCE;
        System.err.println(s3 == s4);
    }

}

/**
 * 简单版本 线程不安全
 */
class Singleton1 {

    private static Singleton1 _singleton = null;

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        if (null == _singleton) {
            _singleton = new Singleton1();
        }
        return _singleton;
    }

}

/**
 * 线程安全
 */
class Singleton2 {

    private static volatile Singleton2 _singleton = null;
    private static Object LOCK = new Object();

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        synchronized (LOCK) {
            if (null == _singleton) {
                _singleton = new Singleton2();
            }
        }
        return _singleton;
    }
}

/**
 * 双重检查
 */
class Singleton3 {

    private static volatile Singleton3 _singleton = null;
    private static Object LOCK = new Object();

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (null == _singleton) {
            synchronized (LOCK) {
                if (null == _singleton) {
                    _singleton = new Singleton3();
                }
            }
        }
        return _singleton;
    }
}

/**
 * 静态初始化
 */
class Singleton4 {

    private static Singleton4 _singleton = new Singleton4();

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return _singleton;
    }
}

/**
 * 静态内部类
 */
class Singleton5 {

    private static class Holder {
        private static Singleton5 _instance = new Singleton5();
    }

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        return Holder._instance;
    }
}

/**
 * 枚举法
 */
enum Singleton6 {
    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String sayHello() {
        return String.valueOf(this.hashCode());
    }
}