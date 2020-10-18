## 力扣6. 两数之和

### 题目描述

### 方法一：取余数计算

#### 思路分析

通过找规律发现字符位置与行数之间存在对应关系，通过取余的办法计算每个字符应该所处的行数。创建一个长度为n的数组，表示有 n 行，每个数组元素为一个链表，表示该行中的字符。扫描整个字符串，将字符放置在对应行的链表中，最终逐行打印链表中的字符，得到最终结果。

#### 实现代码

```java
public class Solution {
    public String convert(String s, int numRows) {
       if (numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        List<Character>[] table = new ArrayList[numRows];
        //Initial the list.
        for (int i = 0; i < numRows; i++) {
            table[i] = new ArrayList<>();
        }

        for (int i = 0; i < s.length(); i++) {
            int position = i % (2 * (numRows - 1));
            if (position >= numRows) {
                position = 2*(numRows -1) - position;
            }
            List<Character> rowList = table[position];
            rowList.add(s.charAt(i));
        }

        // Print the elements in every list.
        for (List<Character> list : table) {
            for (char c : list) {
                res.append(c);
            }
        }
        return res.toString();
    }

}

```

#### 复杂度分析

- 时间复杂度 `O(N)`，遍历字符串为 `O(N)`，打印字符串 `O(N)`，总共复杂度 `O(2N)`。
- 空间复杂度 `O(N)`，链表临时存放字符串中的每个字符

