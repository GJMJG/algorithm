package bit.edu.niuke.wangyi.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 求出前 百分之多少的成绩排名，计算公式：(不超过 s的人数 -1) / 总人数，保留六位小数
 * <p>输入如下：
 * 第一行数字 n：班级总人数
 * 第二行 n 个数字：每个人的成绩
 * 第三行：查询次数 m
 * 第四行的 m 个数：查询第几个人的排名</p>
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 1) {
            return;
        }
        int[] score = new int[n];
        for (int i = 0; i < n; ++i) {
            score[i] = sc.nextInt();
        }

        int queryTimes = sc.nextInt();
        int[] queryScore = new int[queryTimes];
        for (int i = 0; i < queryTimes; ++i) {
            queryScore[i] = score[sc.nextInt() - 1];
        }
        Arrays.sort(score);
        for (int i = 0; i < queryScore.length; ++i) {
            double res = calculate(score, queryScore[i]);
            String st = String.format("%.6f", res);
            System.out.println(st);
        }
    }

    public static double calculate(int[] score, int queryScore) {
        int n = score.length;
        int index = n - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (queryScore == score[i]) {
                index = i;
                break;
            }
        }

        return index * 100.0 / n;
    }
}