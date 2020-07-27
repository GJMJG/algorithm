package sychronizedListDemo;

import java.util.*;

public class SynchronizedListTest {
    public static void main(String[] args) {
        List<Integer> vector = new Vector<>();
        vector.add(1);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        List synchroList = Collections.synchronizedList(list);
        Iterator i = list.iterator();
        // 为什么还要加锁，因为iterator() 操作没有加锁，需要手动加锁
        synchronized (list) {
            while (i.hasNext()) {
                System.out.println(i.next());
            }
        }
    }
}
