# 判断字符串是否唯一

这是 Leecode 上《程序员面试经典》中的一道题目，找出解决方案不难，难的是不使用额外的数据结构解决问题。

题目链接 [https://leetcode-cn.com/problems/is-unique-lcci/](https://leetcode-cn.com/problems/is-unique-lcci/)

## 题目描述

实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

示例 1：

> 输入: s = "leetcode"
>
> 输出: false 

示例 2：

>输入: s = "abc"
>
>输出: true

限制：

    0 <= len(s) <= 100 
	如果你不使用额外的数据结构，会很加分。
	
## 结题思路分析

### 方法一：哈希表
借助 HashSet 中元素不能重复的特点，依次将字符串中的字符放入 HashSet 中，遍历结束后计算Set中的元素个数，如果存在重复字符，Set 中的元素个数肯定小于字符串的长度。

**实现步骤**

1. 创建一个 HashSet
2. 遍历字符串中的字符，将字符添加到 Set 中
3. 比较 Set 的大小和字符串长度，`set<astr.length()` 则返回 `false`

**实现代码**

```java
class Solution {
    public boolean isUnique(String astr) {
        HashSet<Character> set = new HashSet<>();
        char[] stringArray = astr.toCharArray();
        for (char c : stringArray) {
            set.add(c);
        }
        return set.size() < astr.length() ? false : true;
    }
}
```

**进一步优化**

[看了网友好的解法](https://leetcode-cn.com/problems/is-unique-lcci/solution/javali-yong-setqu-zhong-by-evelynnnnn/)，进一步优化一下，在向 HashSet 中添加元素的时候，如果重复就可以直接返回，不需要继续执行。

**优化代码**

