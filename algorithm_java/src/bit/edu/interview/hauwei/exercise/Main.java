package bit.edu.interview.hauwei.exercise;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            if (a == 0) break;
            System.out.println(getResult(a));
        }
    }

    public static int getResult(int buttom) {
        int changed = 0;
        int reserved = 0;
        int result = 0;
        while (buttom > 2) {
            changed = buttom / 3;
            reserved = buttom % 3;
            result += changed;
            buttom = changed + reserved;
        }
        if (buttom == 2) ++result;
        return result;
    }
}
