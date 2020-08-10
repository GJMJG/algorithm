package bit.edu.leecode.threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 三数之和的暴力枚举
 */

class Solution1 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int first = 0; first < len; ++first) {
            // 跳过重复的数字
            if (first != 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            for (int second = first + 1; second < len; ++second) {
                if (second != first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                for (int third = second + 1; third < len; ++third) {
                    if (third != second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }
                    checkSum(nums[first], nums[second], nums[third]);
                }
            }
        }
        return res;
    }

    public boolean checkSum(int a, int b, int c) {
        if (a + b + c == 0) {
            res.add(Arrays.asList(a, b, c));
            return true;
        }
        return false;
    }
}