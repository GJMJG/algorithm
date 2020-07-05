## 力扣14. 最长公共前缀

### 题目描述

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

**示例 1:**

> 输入: ["flower","flow","flight"]
> 输出: "fl"

**示例 2:**

> 输入: ["dog","racecar","car"]
> 输出: ""
> 解释: 输入不存在公共前缀。


说明:

所有输入只包含小写字母 a-z 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-common-prefix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：横向扫描

#### 思路分析

所谓横向扫描是指，逐个扫描数组中的每个字符串，在扫描过程中不断更新最长前缀。先从数组中的第一个字符串`strs[0]`开始，暂时认为该字符串就是最终结果 `res`。接着开始扫描数组中下一个字符串`strs[1]`，比较 `strs[1]` 和 `res` 获得两者的最长公共前缀，并更新结果 `res`。依次扫描数组中的所有字符串，扫描到最后一个字符串 `strs[length - 1]`，此时得到的 `res` 即是所有字符串的最长公共前缀。

#### 代码实现

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";

        String res = strs[0];
        for (int i = 0; i < strs.length; ++i) {
            res = getLongestString(strs[i], res);
            if (res.length() == 0) break;
        }
        return res;
    }

    public String getLongestString(String inputSt, String commonSt) {
        int minLength = Math.min(inputSt.length(), commonSt.length());
        int index = 0;
        for (int i = 0; i < minLength; ++i) {
            if (inputSt.charAt(i) == commonSt.charAt(i)) ++index;
            else break;
        }
        return inputSt.substring(0, index);
    }
}
```

#### 复杂度分析

- 时间复杂度 `O(mn)`，`n` 为数组长度，`m` 为最终结果 最长公共前缀 字符串的长度。扫描所有元素 `O(n)`，扫描到每个元素更新结果需要比较 `O(m)` 次。
- 空间复杂度 `O(1)`，使用额外的空间复杂度为常数。

### 方法二：纵向扫描

#### 思路分析

相比于横向扫描，纵向扫描每次扫描的是数组中所有 **字符串的某个位置上的字符**。每次扫描数组中字符串的 `第i个`位置上的字符，比较是否相等，相等则继续下一轮扫描，否则返回索引从 `0-i` 的子串。

#### 代码实现

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        int column = strs[0].length();
        for (int i = 0; i < column; ++i) {
            char currentChar = strs[0].charAt(i);
            for (int j = 0; j < strs.length; ++j) {
                if (i == strs[j].length() || strs[j].charAt(i) != currentChar) return strs[0].substring(0, i);
            }
        }
        // 如果执行到此，则strs[0] 本身就是最长公共前缀
        return strs[0];
    }
}
```

#### 复杂度分析

- 时间复杂度 `O(mn)`，`n` 为数组长度，`m` 为最终结果 最长公共前缀 字符串的长度。扫描所有元素 `O(n)`，扫描到每个元素更新结果需要比较 `O(m)` 次。
- 空间复杂度 `O(1)`，使用额外的空间复杂度为常数。

### 方法三：分治算法

#### 思路分析

**分治算法**，主要是利用了递归思想。将大的问题划分为小规模的问题，逐层划分。既然是最长公共前缀`completePrefix` ，那么从数组中任意取出若干个字符串，他们的最长公共前缀 `particalPrefix` 一定等于或包含于 `completePrefix`。分治算法可以看作是 **横向切分**。

形式化描述为：LCP 的计算满足结合律，有以下结论：
$$
LCP(S_1…S_n)=LCP(LCP(S_1…S_k),LCP(S_{k+1}…S_n))
$$
基于上述结论，可以使用分治法得到字符串数组中的最长公共前缀。对于问题 
$$
LCP(S_i...S_j)
$$


可以分解成两个子问题 
$$
LCP(S_i...S_{mid}) 与 LCP(S_{mid + 1}...S_j) 
\\其中，mid = \frac {i+j} {2}
$$


对两个子问题分别求解，然后对两个子问题的解计算最长公共前缀，即为原问题的解。

通过上述分析过程可以看出，可以**基于递归**的方式实现。递归函数比较输入的两个字符串数组的最长公共前缀，通过递归方式分别求解两个字符串数组各自的最长公共前缀。

#### 代码实现

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) return strs[start];
        else {
            String a = longestCommonPrefix(strs, start, (start + end) >> 1);
            String b = longestCommonPrefix(strs, (start + end >> 1) + 1, end);
            return commonPrefix(a, b);
        }
    }

    public String commonPrefix(String a, String b) {
        int length = Math.min(a.length(), b.length());
        int index = 0;
        while (index < length && a.charAt(index) == b.charAt(index)) {
            ++index;
        }
        return a.substring(0, index);
    }
}
```

#### 复杂度分析

- 时间复杂度：`O(mn)`，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。时间复杂度的递推式是 T(n)=2⋅T(n2)+O(m)，通过计算可得 T(n)=O(mn)。

- 空间复杂度：O(mlog⁡n)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。空间复杂度主要取决于递归调用的层数，层数最大为 log⁡n，每层需要 m 的空间存储返回结果。递归的空间复杂度计算公式为：`递归深度 x 每层的空间复杂度` 

### 方法四：二分法

#### 思路分析

二分法可以理解为一种 **纵向切分**。在使用 *纵向扫描* 的方法中，扫描的顺序是从第一纵列开始，最坏情况下需要扫描到最后一个字符（数组中最短的字符串），这种顺序扫描其实重复了很多次扫描工作，能不能尽量降低重复扫描次数呢。可以借鉴 **二分查找** 算法的思想，每次折半，减少无用的查找。

首先将需要扫描的字符串索引一分为二，中间的位置为 `mid = (0 + column)/2` 查找从 `0-mid` 的子串是否为公共子串，如果是说明最长公共前缀的最后一个位置索引在mid之后，否则在前。

#### 代码实现

```java
class Solution{
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        int minLength = Integer.MAX_VALUE;
        for (String st : strs) {
            minLength = Math.min(minLength, st.length());
        }

        int left = 0, right = minLength, mid = Integer.MAX_VALUE;
        while (left < right) {
            mid = left + (right - left + 1) / 2;
            if (isCommonPrefix(strs, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }

        }

        return strs[0].substring(0, left);
    }

    /*public boolean isCommonPrefix(String strs[], int position) {
        String prefix = strs[0].substring(0, position);
        for (String st : strs) {
            if (!st.substring(0, position).equals(prefix)) return false;
        }

        return true;
    }*/

    public boolean isCommonPrefix(String[] strs, int position) {
        String prefix = strs[0].substring(0, position);
        for (String st : strs) {
            for (int i = 0; i < position; ++i) {
                if (st.charAt(i) != prefix.charAt(i)) return false;
            }
        }
        return true;
    }
}
```

#### 复杂度分析

### 方法五： 字典树

#### 思路分析

#### 代码实现



#### 复杂度分析

### 方法六：排序

#### 思路分析

利用字符串排序，排序后无需再考虑中间的字符串，只需要考虑首末两个字符串。

#### 代码实现

```java
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);

        StringBuilder res = new StringBuilder();
        int length = strs[0].length();
        for (int i = 0; i < length; ++i) {
            if (strs[0].charAt(i) != strs[strs.length - 1].charAt(i)) break;
            res.append(strs[0].charAt(i));
        }
        return res.toString();
    }
}
```

#### 复杂度分析

