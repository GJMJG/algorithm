package bit.edu.algorithm.basic.scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串 a （可以包含空格或符号）");
        String a = scanner.nextLine();
        System.out.println("输入字符串 b （不可以包含空格）");
        String b = scanner.next();
        System.out.println("输入一个整数 c");
        int c = scanner.nextInt();
        System.out.println("输入一个float数 d");
        float d = scanner.nextFloat();
        System.out.println("输入一个double e");
        double e = scanner.nextDouble();
        System.out.println("输入一个 boolean f");
        boolean f = scanner.nextBoolean();

        System.out.println("a：" + a);
        System.out.println("b：" + b);
        System.out.println("c：" + c);
        System.out.println("d：" + d);
        System.out.println("e：" + e);
        System.out.println("f：" + f);
    }
}
