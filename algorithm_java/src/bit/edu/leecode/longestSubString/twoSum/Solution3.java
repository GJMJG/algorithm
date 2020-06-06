package bit.edu.leecode.longestSubString.twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 一遍哈希表解法
 */
class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> table = new HashMap<>();

        int number = 0;
        for (int i = 0; i < nums.length; ++i) {

            // 先判断是否有满足的值，再去存放当前数据，否则出现数据覆盖的可能
            number = target - nums[i];
            if (table.containsKey(number)) {
                return new int[]{i, table.get(number)};
            }
            table.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] out = new Solution3().twoSum(new int[]{3, 3}, 6);
        System.out.println(Arrays.toString(out));
    }
}