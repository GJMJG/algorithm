package bit.edu.algorithm.basic.intersectionOfTwoArray;

import java.util.*;

/**
 * 使用Hashtable存出现的次数
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> table = new HashMap<>();
        int[] result = new int[nums1.length];

        for (int a : nums1) {
            if (table.containsKey(a)) {
                table.put(a, table.get(a) + 1);
            } else {
                table.put(a, 1);
            }
        }

        int index = 0;
        for (int b : nums2) {
            if (table.containsKey(b) && table.get(b) > 0) {
                result[index++] = b;
                int count = table.get(b) - 1;
                if (count == 0) {
                    table.remove(b);
                } else {
                    table.put(b, table.get(b) - 1);
                }
            }
        }

        return Arrays.copyOfRange(result, 0, index);
    }
}