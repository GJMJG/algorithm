package bit.edu.interview.wangyi;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 网易2020.8.7 笔试题
 * 给定一个整数数组，数组每个元素可以拆分成由若干个素数组成的子数组，求输入数组最多可以拆分为多少个子数组。
 * 最后 ac 80%，想的太复杂了，直接用long类型存储结果就好了，用不着 BigDecimal
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        BigDecimal number = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            number = number.add(new BigDecimal(getMaxNumber(a[i])));
        }
        System.out.println(number);
    }

    /**
     * 求最多可以拆分成几个素数
     */
    static public int getMaxNumber(int a) {
        if (a == 1) {
            return 1;
        }
        return a >> 1;
    }
}
