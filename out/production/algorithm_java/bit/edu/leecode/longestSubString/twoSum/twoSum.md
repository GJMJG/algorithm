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

### 方法二：两次遍历哈希表

#### 思路分析

暴力匹配的算法需要重复查找数组中的元素，效率低下。考虑使用哈希表优化，哈希表的查找时间复杂度是 `O(1)`，所以可以提高时间效率。想要快速查找数字是否存在，则将数组中的数字作为哈希表的 key，对应的索引作为哈希表的 value。

需要注意的是，数组中可能存在相同的元素，而在哈希表中不可能同时存在两个相同的 key，哈希表的 key 会被后者相同的元素覆盖掉。

采用两次遍历的方法，第一遍将数组中的元素存放在哈希表中，哈希表以数组值为 key，以索引为 value。需要注意的是：**遍历结束后，哈希表的 key 对应的 value 是该数字在数组中最后一次出现的索引**。

第二次遍历数组中的每个元素，对于数组中的每个数据，判断在哈希表中是否存在 key 为 `target - nums[i]` 的数字，如果存在，则返回对应的索引。**注意的一点是：** 在判断哈希表中是否存在对应的 key，其索引不能是本身。也就是说 `i != table.get(target - nums[i])`。

#### 实现代码

```java
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
```

#### 复杂度分析

- 时间复杂度为 `O(N)`，遍历两次数组，即需要时间 `O(2N)`。
- 空间复杂度 `O(N)`，构造哈希表需要额外的 `O(N)`空间，


### 方法三：一次遍历哈希表

#### 思路分析

在上述两次遍历过程中，在遍历结束后，才进行数字的判断。可以在遍历的同时判断对应的数字是否存在。这样做一定能够成功的原因在于：若存在这样的两个数，总能够保证，遍历到其中一个数字，另外一个数字总是已经存在与哈希表中。

和两次遍历方法不同的是，一次遍历算法需要 **首先判断哈希表中是否存在对应值**，而不是先存放数据。如果先存放数据，此时数组中若存在相同数据，哈希表中的值会被覆盖掉，导致找不到需要的索引。

另外一个不同点是，一次遍历不需要判断 `i != table.get(target - nums[i])`。因为在遍历到当前值时，该值还没有存放在哈希表中，那么该值对应的索引一定不在哈希表中。

#### 实现代码

```java
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
}
```
#### 复杂度分析

- 时间复杂度为 `O(N)`，遍历一次数组，即需要时间 `O(N)`。
- 空间复杂度 `O(N)`，构造哈希表需要额外的 `O(N)`空间，
