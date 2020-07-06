package singleton.implement;

import java.io.IOException;
import java.util.Properties;

/**
 * 饿汉模式：使用静态代码块
 */
public class Singleton3 {
    public static final Singleton3 INSTANCE;
    private String info;

    static {
        Properties pro = new Properties();
        try {
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton/implement/pro.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String content = pro.getProperty("name");
        INSTANCE = new Singleton3(content);
    }

    private Singleton3(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" + "info='" + info + '\'' + '}';
    }
}
