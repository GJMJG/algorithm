package test;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * {@code instanceof} 关键字判断是否是某一个接口、父类的实例。
 */
public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = "hello";
        System.out.println(s instanceof Comparable); //true
        System.out.println(s instanceof String); //true
        // reflect to access array of String
        Class<String> stClass = String.class;
        Field stValue = stClass.getDeclaredField(("value"));
        stValue.setAccessible(true);

        System.out.println(Arrays.toString((char[]) stValue.get(s)));

        System.out.println(s.replace('l', 'm'));
    }
}
