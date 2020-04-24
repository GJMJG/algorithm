## 无重复字符的最长子串

### 滑动窗口的概念

链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/

滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i,j)向右滑动 1个元素，则它将变为 [i+1,j+1)（左闭，右开）。

### 方法一：滑动窗口

#### 思路分析

一开始看到是求最优解，思路变拐到**动态规划算法**上了。但是发现不太好划分子问题。

随后通过**举例子**的方法，发现可以采用**滑动窗口的方法**，因为字串总是连续的，可以采用两个指针分别表示滑动窗口的起始终止位置。但是在滑动过程中，需要时刻比较是否已经重复，因此需要**记录窗口中的数据**。

**步骤：**


1. 定义一个**HashSet**存放当前滑动窗口中的数据
2. 定义三个变量分别表示窗口的起始位置、终止位置、结果（最长字串长度）
3. **终止位置**开始滑动，每滑动一次，会新添加一个字符，判断当前的滑动窗口中是否已经存在该字符：1. 如果**不存在**，计算当前窗口的长度与结果的最大值，并将该数据加入到data中，继续滑动终止位置；2. 如果**存在**，滑动 **起始位置**，并减少滑动窗口中的字符
4. 循环上述过程，直到终止位置滑动到结尾

### 方法三：优化的滑动窗口

上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。 当我们找到重复的字符时，我们可以立即跳过该窗口。
也就是说，如果 s[j]s[j]s[j] 在 [i,j)[i, j)[i,j) 范围内有与 j′j'j′ 重复的字符，我们不需要逐渐增加 iii 。 我们可以直接跳过 [i，j′][i，j'][i，j′] 范围内的所有元素，并将 iii 变为 j′+1j' + 1j′+1。

#### Java（使用 HashMap）

```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
```

#### Java（假设字符集为 ASCII 128）

以前的我们都没有对字符串 s 所使用的字符集进行假设。
当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
常用的表如下所示：

* int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
* int [128] 用于ASCII码
* int [256] 用于扩展ASCII码

```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
```

#### 复杂度分析


时间复杂度：O(n)O(n)O(n)，索引 jjj 将会迭代 nnn 次。


空间复杂度（HashMap）：O(min(m,n))O(min(m, n))O(min(m,n))，与之前的方法相同。


空间复杂度（Table）：O(m)O(m)O(m)，mmm 是字符集的大小。

