package bit.edu.algorithm.basic.sort;

public class QuickSort {

    static public void quickSort(int[] input) {
        if (input == null || input.length == 0) return;
        quickSort(input, 0, input.length - 1);
    }

    static private void quickSort(int[] input, int start, int end) {
        if (start == end) return;
        int index = partition(input, start, end);
        if (index > start) quickSort(input, start, index - 1);
        if (index < end) quickSort(input, index + 1, end);
    }

    /**
     * 将数组分为两部分，并返回两部分分割索引。前部分所有元素比某个数字小，后部分所有元素比某个数字大
     */
    static public int partition(int[] input, int start, int end) {
        if (input == null || input.length < 0 || start < 0 || end >= input.length) {
            throw new RuntimeException("无效的参数");
        }

        int small = start - 1;
        // 生成一个在[start, end]范围内的随机数
        int random = start + (int) (Math.random() * (end - start + 1));
        swap(input, random, end);
        for (int i = start; i < end; i++) {
            if (input[i] < input[end]) {
                ++small;
                if (i != small) {
                    swap(input, small, i);
                }
            }
        }
        ++small;
        swap(input, end, small);
        return small;
    }

    public static void swap(int[] input, int a, int b) {
        input[a] = input[a] + input[b] - (input[b] = input[a]);
    }
}
