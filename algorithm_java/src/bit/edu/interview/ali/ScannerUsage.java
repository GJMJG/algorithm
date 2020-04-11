package bit.edu.interview.ali;

import java.util.Scanner;

public class ScannerUsage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("number: " + n);
        int[] position = new int[n];
        for (int i = 0; i < n; i++) {

            int a = sc.nextInt();
            int b = sc.nextInt();
            // position[i] = sc.nextInt();
            // sc.nextInt();
            System.out.println(a + "  " + b);

        }

        System.out.println(position);
    }
}