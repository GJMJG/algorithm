package bit.edu.algorithm.basic.sort;

import java.util.Arrays;

class HeapSort {

    public static void heapSort(int[] array) {
        // 创建初始化最大堆
        // 堆顶元素和最后一个元素对调
        // 减小堆大小，重新维护堆
        // 重复上述流程
        System.out.println("堆初始化之前的数组\t" + Arrays.toString(array));
        buildHeap(array);
        System.out.println("堆初始化之后的数组\t" + Arrays.toString(array));
        int n = array.length - 1;
        for (int i = n; i > 0; --i) {
            swap(array, 0, i);
            heapify(array, 0, i - 1);
            System.out.println("第" + (n - i + 1) + "轮排序之后的数组\t" + Arrays.toString(array));
        }
    }

    public static void swap(int[] input, int a, int b) {
        input[a] = input[a] + input[b] - (input[b] = input[a]);
    }

    /* 初始化堆的函数*/
    public static void buildHeap(int[] input) {
        int last = input.length - 1;
        int parent = ((last - 1) >> 1);
        while (parent >= 0) {
            heapify(input, parent, last);
            parent--;
        }
    }

    /**
     * 维护堆，维护以 parentIndex 为根节点的堆，length表示堆最后一个元素索引，因为堆的大小在变化
     */
    public static void heapify(int[] input, int parentIndex, int length) {
        if (parentIndex >= length) return;
        int c1 = (parentIndex << 1) + 1, c2 = (parentIndex << 1) + 2;
        int max = parentIndex;
        if (c1 <= length && input[max] < input[c1]) {
            max = c1;
        }
        if (c2 <= length && input[max] < input[c2]) {
            max = c2;
        }
        if (max != parentIndex) {
            swap(input, max, parentIndex);
            heapify(input, max, length);
        }
    }
}