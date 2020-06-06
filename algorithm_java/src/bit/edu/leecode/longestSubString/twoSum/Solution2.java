package bit.edu.leecode.longestSubString.twoSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 两遍哈希表算法
 */
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> table = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            table.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; ++i) {
            int number = target - nums[i];
            if (table.containsKey(number) && table.get(number) != i) {
                return new int[]{i, table.get(number)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        new Solution2().twoSum(new int[]{3, 3}, 6);
    }
}