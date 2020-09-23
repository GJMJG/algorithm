package bit.edu.algorithm.basic.sort;

import java.util.Arrays;

/**
 * Select sort O(N^2)
 */
public class SelectSort {

    public static void selectSort(int[] input) {
        int length = input.length;
        for (int i = 0; i < length; i++) {
            int min = input[i];
            int min_index = i;
            for (int j = i; j < length; j++) {
                if (input[j] < min) {
                    min = input[j];
                    min_index = j;
                }
            }
            int temp = input[i];
            input[i] = input[min_index];
            input[min_index] = temp;
        }
    }

}
