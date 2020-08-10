package bit.edu.leecode.fourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 里层外层两次夹逼，运用两次双指针。但是代码太复杂了
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        new Solution().fourSum(new int[]{-1, 2, 2, -5, 0, -1, 4}, 3);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int length = nums.length;
        int inside_left = 0, inside_right = 0;
        for (int out_left = 0; out_left < length - 3; ) {
            for (int out_right = length - 1; out_right > out_left + 2; ) {
                int sum = 0;
                inside_left = out_left + 1;
                inside_right = out_right - 1;

                while (inside_left < inside_right) {
                    sum = nums[out_left] + nums[out_right] + nums[inside_left] + nums[inside_right];
                    if (sum < target) {
                        inside_left++;
                        while (inside_left > 1 && inside_left < length && nums[inside_left] == nums[inside_left - 1]) {
                            inside_left++;
                        }
                    } else if (sum > target) {
                        inside_right--;
                        while (inside_right < length - 2 && inside_right > 0 && nums[inside_right] == nums[inside_right + 1]) {
                            inside_right--;
                        }
                    } else {
                        Integer[] array = {nums[out_left], nums[inside_left], nums[inside_right], nums[out_right]};
                        result.add(Arrays.asList(array));
                        inside_left++;
                        inside_right--;
                        while (inside_left > 1 && inside_left < length && nums[inside_left] == nums[inside_left - 1]) {
                            inside_left++;
                        }
                        while (inside_right < length - 2 && inside_right > 0 && nums[inside_right] == nums[inside_right + 1]) {
                            inside_right--;
                        }
                    }
                }

                out_right--;
                while (out_right < length - 1 && out_right > 0 && nums[out_right] == nums[out_right + 1]) {
                    out_right--;
                }
            }

            out_left++;
            while (out_left > 0 && out_left < length && nums[out_left] == nums[out_left - 1]) {
                out_left++;
            }
        }
        return result;
    }
}