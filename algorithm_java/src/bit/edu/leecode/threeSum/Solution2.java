package bit.edu.leecode.threeSum;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序+双指针,将时间复杂度降低为 O(N^2)
 * <p>第一二重循环中的元素确定后，第三个数字也是确定的，第二个数字不断增长，对应的第三个数字必定不断减少，因此第三重循环和第二重循环
 * 本质上是并列关系，不需要从小到大开始循环，而是从大到小循环，这样总的时间复杂度实际上是O(N) ，加上第一重循环的复杂度O(N)，整个程序 的复杂度是 O(N^2)</p>
 */
class Solution2 {
    List<List<Integer>> res = new ArrayList<>();

    // Assuming that first < second < third
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;

        for (int first = 0; first < len - 2 && nums[first] <= 0; ++first) {
            int third = len - 1; //Reset the third pointer.
            if (first != 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            for (int second = first + 1; second < third; ++second) {
                if (second != first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (nums[first] + nums[second] + nums[third] >= 0 && third > second) {
                    if (third != len - 1 && nums[third] == nums[third + 1]) {
                        --third;
                        continue;
                    }
                    if (checkSum(nums[first], nums[second], nums[third])) break;
                    --third;
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