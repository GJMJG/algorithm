## 力扣 67. 二进制求和

### 题目描述

给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

 **示例 1:**

> 输入: a = "11", b = "1"
> 输出: "100"

**示例 2:**

> 输入: a = "1010", b = "1011"
> 输出: "10101"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-binary
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：模拟加法

#### 思路分析

二进制的加法运算可以类比于十进制的加法运算，二进制是逢二进一，末尾对齐，逐位相加。

输入的两个数字需要末尾对齐，位数不一样的情况下，较短的数字需要在前面几位补 0。假设 `n = max(a.length(), b.length())` ，循环n次，定义一个变量 `carry` 记录每次运算的进位，初始值为0。每次运算时，取出当前累加的两个数字 `ai` 和 `bi`，则累加结果由当前位以及进位两部分组成。

- 前位为：

$$
(carry + a_i + b_i) \% 2
$$

- 进位为：

$$
\frac{(carry + a_i + b_i)}{2}
$$

**注意** 在计算到最后一位后，可能进位不为0，所以此时需要在循环之后多计算一次。这里有两种实现方式：使用`while()`循环和 `for`循环，原理都是类似的。使用 while 循环的好处是，直接在条件处判断最后一次运算后进位是否为0。

在计算过程中，使用 `StringBuilder` 保存计算的结果，最终的结果需要做一次反转，因为计算顺序是从最后一位开始的，最终的 String 结果的第一位其实是真实结果的最后一位。

#### 实现代码

- while 循环

  ```java
  class Solution1 {
      public String addBinary(String a, String b) {
          int lenA = a.length() - 1;
          int lenB = b.length() - 1;
          int over = 0;
          int numA = 0, numB = 0;
  
          StringBuilder res = new StringBuilder();
          while (lenA >= 0 || lenB >= 0 || over != 0) {
              numA = lenA < 0 ? 0 : a.charAt(lenA) - '0';
              numB = lenB < 0 ? 0 : b.charAt(lenB) - '0';
              int num = numA + numB + over;
              over = num / 2;
              num %= 2;
  
              res.append(num);
              --lenA;
              --lenB;
          }
  
          return res.reverse().toString();
      }
  }
  ```

- for 循环

  ```java
  class Solution {
      public String addBinary(String a, String b) {
          StringBuffer ans = new StringBuffer();
  
          int n = Math.max(a.length(), b.length()), carry = 0;
          for (int i = 0; i < n; ++i) {
              carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
              carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
              ans.append((char) (carry % 2 + '0'));
              carry /= 2;
          }
  
          if (carry > 0) {
              ans.append('1');
          }
          return ans.reverse().toString();
      }
  }
  ```

#### 复杂度分析

- 时间复杂度 `O(N)`，扫描一遍字符串，其中 `N = max(a.length(), b.length())`。
- 空间复杂度 `O(1)` ，不需要额外的空间。

### 方法二：位运算

#### 思路分析

参考官方题解，但是代码没有通过测试用例，因为用例中包含长度大于 33 的字符串，不能直接解析为 Integer 类型数字。

总结两个位运算规律：

- **异或运算可以实现加法，但是注意可能包含进位**
- **与运算可以获取进位，需要注意将结果进行左移位，将进位对齐到进位的位置**

举个例子：

```
没有进位的异或运算，直接就是最终答案
4 + 8 = 12
0100 ^ 1000 = 1100
```

```
有进位，直接需要加上进位才能得到最终答案
3 + 1 = 4
0011 ^ 0001 = 0010
```

```
获取进位
3 + 1 = 4
0011 & 0001 = 0001 最后一位为 1 表示右进位，注意需要左移 1 位到正确的位置上
```

所以将上述两种特性加在一起，将两数解析为整数 `x` 以及 `y`，接着进行位运算。

当进位不为 0，将 x 当作答案，y 当作进位，最终结果就是 `答案 + 进位`。

- 计算 x 和 y 直接异或运算的结果 `ans`，此时没有考虑进位 `ans = x ^ y`
- 计算 x 和 y 相加的进位 `carry = (x & y ) << 1`
- 将 x 值更新为 `ans`，y 的值更新为 `carry`，接着下一轮循环 

#### 实现代码

```java
class Solution {
    public String addBinary(String a, String b) {
        int x = Integer.parseInt(a, 2);
        int y = Integer.parseInt(b, 2);
        int ans = 0;
      
        while (y != 0) {
            ans = x ^ y;
            int over = (x & y) << 1;
            y = over;
            x = ans;
        }
        return Integer.toBinaryString(ans);
    }
}
```

#### 复杂度分析

- 时间复杂度：O(∣a∣+∣b∣+X⋅max⁡(∣a∣+∣b∣))，字符串转化成数字需要的时间代价为 O(∣a∣+∣b∣)，计算的时间代价为 O(max⁡{∣a∣,∣b∣})，X 为位运算所需的时间，因为这里用到了高精度计算，所以位运算的时间不一定为 O(1)。
- 空间复杂度：这里使用了 x  和 y 来保存 a 和 b  的整数形式。

