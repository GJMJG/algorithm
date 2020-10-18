## 力扣7. 反转整数

### 题目描述

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

**示例 1:**

> 输入: 123
>
> 输出: 321

** 示例 2:**

> 输入: -123
>
> 输出: -321

**示例 3:**

> 输入: 120
>
> 输出: 21

**注意: **

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：逐位扫描 & 溢出前检查

#### 思路分析

整数反转，初步思路是先从低位逐次取出数字，并作为反转后数字的高位。等到原数字中的所有位数数字扫描结束后，完成转换。

但是新产生的数字可能产生溢出，需要考虑数字溢出的情况。溢出检查需要在计算反转数字之前进行，否则该计算过程就已经产生溢出，最终得不到正确结果。

假设上次反转之后的数字为 `reverse`，每次取出的数字为`number`，则计算反转后数字的算法为：
$$
reverse = 10 \times reverse + number\\
其中，number = x \% 10
$$
在计算过程中， `10* reverse + number `可能产生溢出，所以每次计算之前先进行如下判断：

如果输入为正数，即 result > 0，则不产生溢出的条件为：
$$
result \leqslant \frac {Integer.MAX\_Value - number} {10}
$$
如果输入为负数 ，即`result < 0` ，则不产生溢出的条件为：
$$
result \geqslant \frac{Integer.MIN\_VALUE - number}{10}
$$
如果上述不等式成立说明经过计算产生的结果将溢出。

#### 代码实现

```java
class Solution {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int number = x % 10;
            if (x < 0 && result > (Integer.MAX_VALUE - number) / 10) return 0;
            if (x > 0 && result < (Integer.MIN_VALUE - number) / 10) return 0;
            result = 10 * result + number;
            x /= 10;
        }
        return result;
    }
}
```

#### 复杂度分析

* 时间复杂度 `O(logN)` ，数字N大概有 long_10 (N)位数字 
* 空间复杂度 `O(1)`