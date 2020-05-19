package hashMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap初始化默认容量为16
 * <p> 使用反射的方式调用获取容量的方法以及表示size的字段
 */
public class DeepLearningHashMap {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("No1", "1");
        Class<?> mapType = map.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map));
        Field size = mapType.getDeclaredField("size");
        size.setAccessible(true);
        System.out.println("size : " + size.get(map));
    }
}
