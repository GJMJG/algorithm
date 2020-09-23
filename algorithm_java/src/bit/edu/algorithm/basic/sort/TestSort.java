package bit.edu.algorithm.basic.sort;

import java.util.Arrays;

public class TestSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 5, 9, 34, 58, 0};
        SelectSort.selectSort(array);
        System.out.println(Arrays.toString(array));

        int[] array1 = new int[]{2, 1, 5, 9, 34, 58, 0};
        InsertSort.insertSort(array1);
        System.out.println(Arrays.toString(array1));

        int[] array2 = new int[]{2, 1, 5, 9, 34, 58, 0};
        QuickSort.quickSort(array2);
        System.out.println(Arrays.toString(array2));

        System.out.println("=======堆排序========");
        int[] array4 = new int[]{2, 1, 5, 9, 34, 58, 0, 3, 2};
        HeapSort.heapSort(array4);
        System.out.println("排序之后的数据：\t \t" + Arrays.toString(array4));
    }
}
