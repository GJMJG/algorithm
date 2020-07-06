package singleton.implement;

/**
 * 饿汉模式：直接创建
 */
public class Singleton1 {
    public static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }
}
