package bit.edu.test.tecent;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 并查集，我是使用多个map来模拟的
 * <p>一共n个人分成m个团队，一个人可以属于多个团队，也可以不属于任何一个团队，求出从 0 开始的并查集，返回最终连通的元素总数</p>
 */
public class Main1 {
    static Set<Integer> count = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // n 个人数
        int m = in.nextInt(); // m个团队
        Set<Integer>[] array = new Set[m];
        boolean[] visited = new boolean[m]; // 表示是否访问过该团队
        for (int i = 0; i < m; i++) {
            int num = in.nextInt();
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < num; j++) {
                set.add(in.nextInt());
            }
            array[i] = set;
            visited[i] = false;
        }

        int i = 0;
        for (Set<Integer> set : array) {
            if (set.contains(0)) {
                visited[i] = true;
                getPeople(array, set, visited);
                break;
            }
            ++i;
        }

        System.out.println(i == m ? 0 : count.size());
    }

    /**
     * @param array   团队数组
     * @param set     当前的团队
     * @param visited 团队们的访问情况
     */
    static void getPeople(Set[] array, Set<Integer> set, boolean[] visited) {
        count.addAll(set);
        for (int a : set) {
            // 当前成员是否包含在其他组中
            for (int i = 0; i < array.length; i++) {
                if (visited[i]) continue;
                if (array[i].contains(a)) {
                    visited[i] = true;
                    getPeople(array, array[i], visited);
                }
            }
        }
    }
}
