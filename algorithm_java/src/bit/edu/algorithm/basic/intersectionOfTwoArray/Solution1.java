package bit.edu.algorithm.basic.intersectionOfTwoArray;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 排序之后处理
 */
public class Solution1 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index = 0, index1 = 0, index2 = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];

        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] == nums2[index2]) {
                result[index++] = nums1[index1];
                ++index1;
                ++index2;
            } else if (nums1[index1] < nums2[index2]) {
                ++index1;
            } else {
                ++index2;
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }

    public static void main(String[] args) {
        int[] result = new Solution1().intersect(new int[]{1, 2, 2, 3, 1,3}, new int[]{2, 2, 3});
        System.out.println(Arrays.toString(result));
    }
}
