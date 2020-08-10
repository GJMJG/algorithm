package bit.edu.leecode.fourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. 排序，假设四个指针 a < b < c < d 2. 三层循环：第一层改变 a，第二层改变 b，第三层改变 c、d 3. 第一层循环 a = 0;a <length - 3  第二层循环 b = a +1;b < length
 * -2，第三层循环 c = b + 1;c < length -1，d = length - 1，如果值大于target，* --right，否则 ++left 4. 注意去重，计算可能的最小值和最大值，减少不必要循环
 */
class Solution1 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int length = nums.length;
        for (int a = 0; a < length - 3; ++a) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            if (!check(target, nums, a)) continue;

            for (int b = a + 1; b < length - 2; ++b) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                /**
                 * 避免不必要的循环，大于最大值或小于最小值无须再次循环
                 */
                int min = nums[a] + nums[b] + nums[b + 1] + nums[b + 2];
                int max = nums[a] + nums[b] + nums[length - 1] + nums[length - 2];
                if (min > target || max < target) continue;

                int c = b + 1, d = length - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum >= target) {
                        if (sum == target) {
                            Integer[] array = {nums[a], nums[b], nums[c], nums[d]};
                            result.add(Arrays.asList(array));
                        }
                        --d;
                        // 去重
                        while (d > c && d < length - 1 && nums[d] == nums[d + 1]) {
                            --d;
                        }
                    } else {
                        ++c;
                        while (c < length && c > b + 1 && nums[c] == nums[c - 1]) {
                            ++c;
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean check(int target, int[] nums, int index) {
        int length = nums.length;
        int min = nums[index] + nums[index + 1] + nums[index + 2] + nums[index + 3];
        int max = nums[index] + nums[length - 1] + nums[length - 2] + nums[length - 3];
        if (min > target || max < target) {
            return false;
        }
        return true;
    }
}