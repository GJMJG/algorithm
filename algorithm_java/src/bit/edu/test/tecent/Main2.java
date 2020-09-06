package bit.edu.test.tecent;

import java.util.*;

/**
 * 输入多个字符串，依次输出第 k 多和第 k 少的字符以及对应出现的次数
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        Map<String, Integer> map = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        int i = 0;
        Integer[] array = new Integer[map.size()];
        for (String s : map.keySet()) {
            array[i++] = map.get(s);
        }

        Arrays.sort(array, (a, b) -> (a - b));
        for (int j = 0; j < k; j++) {
            for (String s : map.keySet()) {
                if (map.get(s) == array[j]) {
                    System.out.println(s + " " + array[j]);
                    if (j + 1 < k && array[j + 1] == array[j]) {
                        j++;
                    } else break;
                }
            }
        }

        Arrays.sort(array);
        int len = array.length;
        // 输出前 K 小
        for (int j = 0; j < k; j++) {
            for (String s : map.keySet()) {
                if (map.get(s) == array[j]) {
                    System.out.println(s + " " + array[j]);
                    if (j + 1 < k && array[j + 1] == array[j]) {
                        j++;
                    } else break;
                }
            }
        }

    }
}
