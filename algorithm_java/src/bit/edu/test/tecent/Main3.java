package bit.edu.test.tecent;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 输入一个数组，输出删除第 i 个元素之后，由 n - 1 个元素组成数组的中位数
 * <p>注意的是拷贝一份数组而不是直接操作操作</p>
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = in.nextInt();
        }

        int[] copy = Arrays.copyOf(number, n);
        Arrays.sort(copy);
        int midA = copy[(n - 1) >> 1];
        int midB = copy[n >> 1];

        for (int i = 0; i < n; i++) {
            if (number[i] <= midA) {
                System.out.println(midB);
            } else {
                System.out.println(midA);
            }
        }
    }
}
