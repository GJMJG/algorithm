## 力扣28. 实现 strStr

### 题目描述

实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

**示例 1:**

> 输入: haystack = "hello", needle = "ll"
> 输出: 2

**示例 2:**

>  输入: haystack = "aaaaa", needle = "bba"
>  输出: -1

**说明:**

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-strstr
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：子串逐一比较

#### 思路分析

既然要匹配一个字符串，可以遍历所有的子串，逐个比较是不是等于目标字符串。不过这里有一个附加条件可以避免穷举所有的子串：目标字符串已知。所以大致思路如下：将长度为 `L=needle.length()` 的滑动窗口从字符串 `haystack` 的开头开始滑动，比较滑动窗口中的字符串是否和 `needle` 字符串相等，如果相等则放回索引，否则返回 `-1`。

#### 实现代码

```java
public class Solution {
    public int strStr(String haystack, String needle) {
        int L = haystack.length(), n = needle.length();

        for (int start = 0; start < L - n + 1; ++start) {
            if (haystack.substring(start, start + n).equals(needle)) {
                return start;
            }
        }
        return -1;
    }
}

```

#### 复杂度分析

- 时间复杂度 `O((N-L)*L)`，其中`N = haystack.length()`，`L = needle.length()`，在内循环中比较子串是否相等复杂度为 L，需要比较 `N - L` 个子串。
- 空间复杂度 `O(1)`

### 方法二：双指针

#### 思路分析

在上述实现中，使用了 String 自带的 `subString` 函数，即使字符串中间的某个字符串不匹配，还是会逐位比较。可以自己实现比较的方法，按字符逐个比较，如果某一位不相等则立刻返回 `false`。

不过也可以从另外一个角度理解：有两个指针 `a 、b` 分别指向`haystack 和 needle` 两个字符串，首先两个指针分别指向第一个字符，接着两指针同时向后扫描，检查字符是否相同。如果不同，则两指针开始回退，`a` 指针回退到 `pn - curr_len +1`，`b` 指针回退到 `0`。继续扫描。

这里将指针回退部分抽象为 `isMatched()` 函数，其实本质上还是两个指针的逐位比较哦啊，通过逐个扫描字符判断字符串是否相等，避免了两个指针的回退，基于循环就可以实现。

#### 代码实现

```java
class Solution1 {
    public int strStr(String haystack, String needle) {
        if (needle.equals("") || needle == null) {
            return 0;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; ++i) {
            if (isMatched(haystack, needle, i)) return i;
        }
        return -1;
    }

    public boolean isMatched(String haystack, String needle, int start) {
        int i = start;
        int j = 0;

        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                ++i;
                ++j;
            } else {
                break;
            }
        }
        return j == needle.length() ? true : false;
    }
}
```

#### 复杂度分析

* 时间复杂度 `O((N-L)*L)`，其中`N = haystack.length()`，`L = needle.length()`，在内循环中比较子串是否相等复杂度为 L，需要比较 `N - L` 个子串，最好情况下复杂度 `O(N)`，假设不匹配的字符串在第一个字符就不匹配了。
* 空间复杂度 `O(1)`。

### 方法三：KMP 算法

其实这是一道字符串匹配问题，可以用正则表达式，KMP 解决的主要是字符串匹配问题。这里需要补充。