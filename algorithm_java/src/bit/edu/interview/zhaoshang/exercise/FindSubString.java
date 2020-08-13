package bit.edu.interview.zhaoshang.exercise;
/**
 * 题目描述
 * 从非负整数序列 0, 1, 2, ..., n中给出包含其中n个数的子序列，请找出未出现在该子序列中的那个数。
 * 输入描述:
 * 输入为n+1个非负整数，用空格分开。
 * 其中：首个数字为非负整数序列的最大值n，后面n个数字为子序列中包含的数字。
 * 输出描述:
 * 输出为1个数字，即未出现在子序列中的那个数。
 * 示例1
 * 输入
 * 复制
 * 3 3 0 1
 * 输出
 * 复制
 * 2
 *
 * 思路：等差数列求和做差，也可以参考剑指offer数组中重复数的解法
 */

import java.util.Scanner;

public class FindSubString {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        int n = sc.nextInt();
        for (int i = 0; i < n; ++i) {
            sum += sc.nextInt();
        }

        int result = n * (n + 1) >> 1;
        System.out.print(result - sum);
    }
}
