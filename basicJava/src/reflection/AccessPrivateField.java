package reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Reflect to access array of String
 */
public class AccessPrivateField {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = "hello";
        // Gets the class of String type using Reflection.
        Class<?> stClass = String.class;
        // Gets the specific field of returned class.
        Field stValue = stClass.getDeclaredField(("value"));
        // Make sure the field can be accessed.
        stValue.setAccessible(true);
        // Using get() to get the field of one String instance. Note Cast to char[] is needed.
        System.out.println(Arrays.toString((char[]) stValue.get(s)));
    }
}
