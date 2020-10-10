package bit.edu.interview.hauwei.test;

import java.util.Collections;
import java.util.Scanner;

/**
 *链接：https://www.nowcoder.com/questionTerminal/d0fafdf8892743818a3f990c8ba4ccc7
 * 来源：牛客网
 *
 * KiKi学习了循环，BoBo老师给他出了一系列打印图案的练习，该任务是打印用“*”组成的X形图案。
 *
 * 输入描述:
 * 多组输入，一个整数（2~20），表示输出的行数，也表示组成“X”的反斜线和正斜线的长度。
 *
 * 输出描述:
 * 针对每行输入，输出用“*”组成的X形图案。
 *
 * 示例1
 * 输入
 * 5
 * 输出
 * *   *
 *  * *
 *   *
 *  * *
 * *   *
 * 示例2
 * 输入
 * 6
 * 输出
 * *    *
 *  *  *
 *   **
 *   **
 *  *  *
 * *    *
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            printX(n);
        }
    }

    public static void printX(int n) {
        String s = String.join("", Collections.nCopies(n, " "));
        for (int j = 0; j < n; ++j) {
            char[] temp = s.toCharArray();
            temp[j] = '*';
            temp[n - j - 1] = '*';
            System.out.println(new String(temp));
        }
    }
}
