package bit.edu.leecode.threeSumClosest;

import java.util.Arrays;

class Solution {
    /**
     * 1.数组排序 2.假设a < b < c，循环a 3.b、c分别为剩下数组的左右边界，计算a+b+c更新结果，如果 a+b+c>target，右边界左移，a+b+c<target，左边界右移
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int left = 0, right = 0, temp = 0;
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                temp = nums[i] + nums[left] + nums[right];
                if (temp > target) {
                    --right;
                } else if (temp < target) {
                    ++left;
                } else return temp;
                result = Math.abs(temp - target) < Math.abs(result - target) ? temp : result;
            }
        }
        return result;
    }
}