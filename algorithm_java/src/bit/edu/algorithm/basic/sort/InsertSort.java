package bit.edu.algorithm.basic.sort;

/**
 * Insert sort O(N^2)
 */
public class InsertSort {
    public static void insertSort(int[] input) {
        int length = input.length;
        // 插入的次数
        for (int i = 1; i < length; i++) {
            int ordered_tail = i - 1; //已排好序子数组的最后一位索引
            int insertNum = input[i];
            for (int j = ordered_tail; j >= 0; j--) {
                if (input[j] > insertNum) {
                    input[j + 1] = input[j];
                    input[j] = insertNum;
                }
            }
        }
    }
}
