## 两数之和

### 题目描述

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

**示例:**

> 给定 nums = [2, 7, 11, 15], target = 9
>
> 因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum

### 方法一：暴力匹配

#### 思路分析

暴力匹配算法可以解决大部分问题，这道题算是力扣最简单的题目了。枚举数组中的所有数字，如果两数之和等于目标值，则返回。

#### 实现代码

```java
public class Solution {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; ++i) {
                for (int j = i + 1; j < nums.length; ++j) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{-1, -1};
        }
    }
}
```

### 复杂度分析

- 时间复杂度 `O(N^2)`，遍历数组中的所有元素
- 空间复杂度 `O(1)`，没有额外的内存消耗

### 方法二：