package bit.edu.offer.getKthNumber;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // 找到start和end中的某个位置，该位置左边的数字都小于array[index]，右边都大于或等于array[index]
    public static int partition(int[] array, int start, int end) {
        if (array == null || array.length == 0 || start < 0 || end >= array.length) {
            throw new RuntimeException("参数不合法");
        }
        int index = start - 1;
        //        int base = start + new Random().nextInt(end - start + 1);//[start, end]
        int base = start + (int) (Math.random() * (end - start + 1));
        swap(array, end, base);
        base = end; // 最后一位数字作为基准
        for (int i = start; i < end; ++i) {
            // array[i] < array[base]，增加index
            if (array[i] < array[base]) {
                ++index;
                if (i != index) {
                    swap(array, i, index);
                }
            }
        }
        ++index;
        swap(array, index, base);
        return index;
    }

    public static void swap(int[] array, int a, int b) {
        if (array == null || array.length == 0 || a < 0 || b >= array.length) {
            throw new RuntimeException("参数不合法");
        }
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        int[] array = {7, 6, 9, 2, 5, 1, 3, 8, 4};
        List<Integer> res = new Solution().GetLeastNumbers_Solution(array, 3);
        System.out.println(res.toString());
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> as = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k == 0) return as;
        int start = 0, end = input.length - 1;
        int index = partition(input, start, end);
        while (index != k - 1) {
            //向左找
            if (index > k - 1) {
                end = index - 1;
            } else { //向右找
                start = index + 1;
            }
            index = partition(input, start, end);
        }

        for (int i = 0; i < k; i++) {
            as.add(input[i]);
        }
        return as;
    }
}