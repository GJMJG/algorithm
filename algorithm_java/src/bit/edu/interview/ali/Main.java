package bit.edu.interview.ali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 阿里的笔试第一题，基本的思路是对的，就是回溯法。
 * <p>输入一个数字 n，找出所有 1 - n 之间数字的排列，使得任意两个相邻的数字差都不等于1。</p>
 * 使用递归实现，当时没调试成功，遗落了两个重要的知识点：
 * <p>
 * <b>1. 数组链表中，切记要新建数组，不要直接使用引用数组</b>
 * <b>2. 回溯法中的访问数组切记要清空</b>
 * </p>
 * 如果上述两点细节注意到了，这道题就没什么难点了。本质上还是属于八皇后问题的变形，只不过设定的规则不同
 */
public class Main {
    int n;
    List<int[]> result = new ArrayList<int[]>();

    public Main(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        Main afterResult = new Main(length);
        List<int[]> result = afterResult.calculate();

        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }

    public List<int[]> calculate() {
        int[] input = new int[n];
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            input[0] = i;
            visited[i] = true;
            findResult(input, 0, visited);
            visited[i] = false;
        }

        return this.result;
    }

    /**
     * @param input    已经匹配好位置的数组
     * @param position 已经匹配好的数组中最后位置
     * @param visited  记录是否已经访问过数组下标对应的数字
     */
    public void findResult(int[] input, int position, boolean[] visited) {
        int length = input.length;
        if (position == length - 1) {
            addResult(input);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            if (input[position] - i != 1 && i - input[position] != 1) {
                if (position + 1 < length) {
                    input[position + 1] = i;
                }

                visited[i] = true;
                findResult(input, position + 1, visited);
                visited[i] = false;
            }
        }
    }

    public void addResult(int[] array) {
        int[] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i];
        }

        this.result.add(arr);
    }
}
