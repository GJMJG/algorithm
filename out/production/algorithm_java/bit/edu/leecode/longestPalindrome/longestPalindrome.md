## 最长回文子串

### 题目描述
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

回文串（palindromic string）是指这个字符串无论从左读还是从右读，所读的顺序是一样的；简而言之，回文串是左右对称的。所谓最长回文子串问题，是指对于一个给定的母串，找出最长的回文子串。

示例 1：

```
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
```

示例 2：

```
输入: "cbbd"
输出: "bb"
```

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：暴力匹配

暴力匹配几乎可以解决所有的问题，但是效率不高，因为存在很多重复的无用计算。很多优化的解法是在暴力匹配的基础上，以空间获取时间或其他的方法优化的。

#### 算法思路

要求最长回文字串，可以枚举字符串的所有字串，并判断这些子串是否为回文串，从所有的回文字串中挑选出最长的即可。

可以进一步优化，在判断子串是否为回文串前，先判断子串长度是否大于“当前已经得到的最长回文子串”,不然则无需判断该子串是否为回文串。

另外一个细节是，将字符串转换为字符数组。使用 `string.charAt()` 方法每次会判断下标是否越界。

基于上述几个简单的优化，暴力匹配也能通过提交。

#### 实现代码

```java
public class Solution1 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        String longestPalindromString = "";
        int longestConunt = 0;
        //将最长回文串对应的起始索引记录下来，不断更新索引，最终才返回对应的串
        int start = 0, end = 0;
        //s.charAt()  每次检查下标是否越界，因此将字符串转换为字符数组提高效率
        char[] stringArray = s.toCharArray();
        for (int i = 0; i < stringArray.length; ++i) {
            for (int j = i + 1; j < stringArray.length; ++j) {
                // 求最长的回文串，如果当前的索引间距小于目前的回文串长度，那么不必继续判断是否为回文串
                if (longestConunt < j - i && isPalindrom(stringArray, i, j)) {
                    longestConunt = j - i;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 判断输入的字符串是否为回文串。头尾两个指针相对运动，逐个比较指针对应的位置上字符是否相同
     */
    public boolean isPalindrom(char[] arrays, int start, int end) {
        while (start <= end) {
            if (arrays[start] != arrays[end]) {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }
}
```

#### 复杂度分析

时间复杂度：`O(N^3)`，遍历所有的子串复杂度是 O(N^2)，判断是否为回文串时间复杂度 O(N)。

空间复杂度：`O(N)`，需要的空间与字符串长度无关。

### 方法二：动态规划

#### 算法思路

**自顶向下分析问题**

对于一个子串而言，如果它是回文串，并且长度大于 2，那么将它首尾的两个字母去除之后，它仍然是个回文串。例如对于字符串 “ababa”，如果我们已经知道 “bab”是回文串，那么 “ababa” 一定是回文串，这是因为它的首尾两个字母都是 “a”。

显然，可以使用递归的方式实现，递归判断去掉首尾字符的子串是否为回文串。但是基于递归的方式涉及大量重复计算，效率不高。

**自底向上解决问题**

动态规划算法基于循环实现，相比于递归效率大幅提高。遇到子串问题采用动态规划解决时，常定义一个二维数组维护每个子问题的解，也就是每个状态。

记 `P[i,j]` 表示字符串中第 i 到 j 的子串是否为回文串，那么：
$$
P[i,j] =
\left\{
\begin{matrix}
 & true &，如果子串 S_i .. S_j 是回文串  \\ 
 &false &，其他情况
\end{matrix}\right.
$$
这里的 其他情况 是指子串不是回文串，或者此时的字符串不合法例如 i > j 。

动态规划的状态转移方程：

$$
P(i,j) = P(i+1,j-1) \, \wedge \, (S_i == S_j) 
$$

这里还需要边界条件，类似于递归中的结束条件，动态规划中也有边界，正是在边界的基础上推导得出的最终结果。这里的边界条件是：只有一个字符的串一定是回文串，有两个字符的串，如果两个字符相同则为回文串。

$$
P[i,j] \, = \, \left\{
\begin{matrix}
& true & ,i ==j \\
& true & ,i+1==j \; \wedge S_i = S_j
\end{matrix} \right .
$$

动态规划算法从小的问题开始计算，逐步扩大到大的问题，因此动态规划的算法要注意**循环顺序** 这里的循环就是从长度较小的串开始向长度较长的串转移。

#### 实现代码

```java
public class Solution2 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (s.length() < 2) {
            return s;
        }

        // 记录从 i 到 j 子串是否为回文串
        boolean[][] dp = new boolean[length][length];
        char[] stringArray = s.toCharArray();
        int start = 0, end = 0;
        int longestConunt = 0;
        for (int j = 1; j < length; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (i == j || j - i == 1) {
                    dp[i][j] = stringArray[i] == stringArray[j];
                } else {
                    if (stringArray[i] != stringArray[j]) {
                        dp[i][j] = false;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && longestConunt < j - i + 1) {
                    longestConunt = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}
```

#### 复杂度分析

- 时间复杂度 `O(n^2)` ，动态规划的总状态是 N^2，每个状态的时间复杂度是 O(1)。
- 空间复杂度： O（N^2），需要构造二维数组。

### 中心扩散
