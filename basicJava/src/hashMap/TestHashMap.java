package hashMap;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(map.put(1, 11));
        System.out.println(map.put(2, 22));
    }
}