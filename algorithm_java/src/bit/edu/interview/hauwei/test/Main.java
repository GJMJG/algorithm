package bit.edu.interview.hauwei.test;

import java.util.Scanner;

/**
 * 2020.8.12 华为笔试
 * 商品5元，有5，10，20面值，顾客面值序列，问能否找零，如果可以打印rrue，并输出最后一位顾客序号，
 * 否则输出false，以及不能找零的顾客序号
 * 设置三个变量：分别表示几种面值有几张
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] money = {0, 0, 0}; //5 10 20
        int index = 0;
        boolean isTrue = true;
        String input = sc.nextLine();
        String[] inputArray = input.split(",");

        for (int i = 0; i < inputArray.length; i++) {
            index = 1 + i;
            int in = Integer.parseInt(inputArray[i]);
            if (!canGoing(in, money)) {
                isTrue = false;
                break;
            }
        }

        if (isTrue) {
            System.out.print("true");
        } else {
            System.out.print("false");
        }
        System.out.println("," + index);
    }

    private static boolean canGoing(int in, int[] money) {
        if (in < 5) return false;
        if (in == 5) {
            money[0] += 1;
            return true;
        } else if (in == 10) {
            if (money[0] > 0) {
                money[1] += 1;
                money[0] -= 1;
                return true;
            }
            return false;
        } else if (in == 20) {
            if (money[1] > 0 && money[0] > 0) {
                money[1] -= 1;
                money[0] -= 1;
                money[2] += 1;
                return true;
            } else if (money[0] > 2) {
                money[2] += 1;
                money[0] -= 3;
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
