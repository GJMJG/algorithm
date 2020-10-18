package bit.edu.algorithm.basic.scanner;

import java.util.Arrays;
import java.util.Scanner;

public class ScanArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input array and interval with space");
        int[] array = new int[8];
        for (int i = 0; i < array.length; i++) {
            if (scanner.hasNextInt()) {
                array[i] = scanner.nextInt();
            }
            //           array[i] = scanner.nextInt();
        }
        System.out.println("array after parse: " + Arrays.toString(array));
    }
}
