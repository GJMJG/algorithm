## 字符串转换整数

### 题目描述

请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

- 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
- 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
- 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：

- 本题中的空白字符只包括空格字符 ' ' 。
- 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。

**示例 1:**

> 输入: "42"
>
> 输出: 42

**示例 2:**

>输入: "   -42"
>
>输出: -42
> 解释: 第一个非空白字符为 '-', 它是一个负号。 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。

**示例 3:**

> 输入: "4193 with words"
>
> 输出: 4193
>
> 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

**示例 4:**

> 输入: "words and 987"
>
> 输出: 0
>
> 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。

**示例 5:**

> 输入: "-91283472332"
>
> 输出: -2147483648
>
> 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/string-to-integer-atoi
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：直接扫描

#### 思路分析

扫描字符串的每个字符，根据给定的规则判断字符。首先忽略掉开头的空字符串，从非空字符串开始，判断首个非空字符是否为 `+` 或者 `-` 如果是则记录下最终的符号。

接着开始遍历表示数字的字符，如果如果是数字，则每次 `x10`且加上当前数字，知道字符非数字为止。

需要注意的细节是，得到的结果可能溢出，在计算 `x10` 和累加操作都有可能溢出，这里直接使用 `long` 类型数据存储中间结果，先计算再判断是否超出限定的范围。

#### 实现步骤 

1. 去除开头的空格
2. 设置一个符号标志位，初始化为0（如果是0表示字符串中没有显示地 + - ）。扫描字符串第一个非空格字符，该字符可能出现三种情况：
    - 表示符号 `+`或`-`：如果为 +，标志位设为 1，如果为 -，设置为 -1，并将扫描索引 +1.
    - 非符号，非数字字符：直接退出
    - 数字：继续扫描
3. 循环扫描数字，直到扫描到非数字。在扫描过程中，执行 计算，`result * 10 + str.charAt(i) - '0'` ，注意越界的判断。

Java中整数的最大值 +1 或者最小值 -1 会产生溢出，但是Java并不会提示这个溢出，结果仍然是保留有效的 32 位表示的整数。例如 
 
  ```java
  assert Integer.MAX_VALUE + 1 == Integer.MIN_VALUE;
  assert -Integer.MIN_VALUE == Integer.MIN_VALUE;
```` 

所以实现代码中，正确的比较方法是先转换为long类型，再取反 `-(long)Integer.MIN_VALUE`。
  
#### 代码实现

```java

public class Solution {
    public int myAtoi(String str) {
                if (str == null || str.length() == 0) {
            return 0;
        }
        long result = 0;
        int sign = 0;
        int length = str.length();
        // Remove the space at the front of str.
        int i = 0;
        while (str.charAt(i) == ' ') {
            ++i;
            if (i >= length) return 0;
        }

        if (isSign(str.charAt(i))) {
            sign = str.charAt(i) == '+' ? 1 : -1;
            ++i;
        } else if (!isNumber(str.charAt(i))) {
            return 0;
        }

        while (i < length && isNumber(str.charAt(i))) {
            long temp = result * 10 + str.charAt(i) - '0';
            result = sign == -1 ? Math.min(-(long) Integer.MIN_VALUE, temp) : Math.min(Integer.MAX_VALUE, temp);
            ++i;
        }
        return sign == -1 ? -(int) result : (int) result;
    }

    public boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isSign(char c) {
        return c == '+' || c == '-';
    }
}

```

#### 复杂度分析

- 时间复杂度 `O(N)`，循环一次
- 空间复杂度 `O(1)`

### 方法二：有限状态机

组内同学研究了这么久状态机，做题的时候反而想不到状态机。

#### 思路分析

状态机最终要的几个概念是：“状态”、“触发条件”，状态机由一个个小的状态组成，如果满足 “触发条件”，状态机就可以在状态之间转移。

本题可以使用状态机的思想，每个输入的字符就是一个“转移条件”，满足转移条件后会在相应状态之间转移。对于本题一共有四个状态，分别是：“开始”、“结束”、“匹配符号”、“匹配数字”，明确各个状态以及状态之间的转移关系之后，可以通过构造状态转移表的方式查询转移方式。

需要注意的细节是，在匹配数字过程中，需要不断累加数字。**累加的整数可能溢出**，因此在累加之前需要判断累加后有无溢出的可能。可以用 long 类型表示最终的输出结果，这样可以累加再和最大值比较，简化计算。

 Java中整数的最大值 +1 或者最小值 -1 会产生溢出，但是Java并不会提示这个溢出，结果仍然是保留有效的 32 位表示的整数。例如 
 
  ```java
  assert Integer.MAX_VALUE + 1 == Integer.MIN_VALUE;
  assert -Integer.MIN_VALUE == Integer.MIN_VALUE;
```` 
  所以实现代码中，正确的比较方法是先转换为long类型，再取反 `-(long)Integer.MIN_VALUE`。
 
#### 代码实现

```java
public class Solution {
    public int myAtoi(String str) {
        int length = str.length();
        if (str == null || str.length() == 0) {
            return 0;
        }

        Automaton automaton = new Automaton();
        for (int i = 0; i < length; i++) {
            if(automaton.isEnd) break;
            automaton.transition(str.charAt(i));
        }

        return automaton.sign * (int) automaton.result;
    }
}

class Automaton {
    // Record current state.
    String state = START;
    final static String START = "start", SIGNED = "signed", IN_NUMBER = "in_number", END = "end";
    Map<String, String[]> table;
    public int sign = 1;
    public long result = 0;
    public boolean isEnd = false;

    public Automaton() {
        table = new HashMap<>();
        table.put(START, new String[]{START, SIGNED, IN_NUMBER, END});
        table.put(SIGNED, new String[]{END, END, IN_NUMBER, END});
        table.put(IN_NUMBER, new String[]{END, END, IN_NUMBER, END});
        table.put(END, new String[]{END, END, END, END});
    }

    public void transition(char c) {
        // Transition
        state = table.get(state)[getNextTransIndex(c)];

        if (state.equals(SIGNED) && c == '-') sign = -1;
        if (state.equals(IN_NUMBER)) {
            result = result * 10 + (c - '0');
            result = sign == -1 ? Math.min(-(long)Integer.MIN_VALUE, result) : Math.min(result, Integer.MAX_VALUE);
        }
        if(state.equals(END)) isEnd = true;
    }

    int getNextTransIndex(char c) {
        if (c == ' ') return 0;
        if (c == '+' || c == '-') return 1;
        if (c >= '0' && c <= '9') return 2;
        return 3;
    }
}
```

#### 复杂度分析

- 时间复杂度 `O(N)`，遍历字符串
- 空间复杂度 `O(1)`

### 方法三：正则表达式

#### 解题思路

通过正则表达式自动匹配目标的字符串，其实正则表达式背后也是通过状态机的方式实现的。只要写好正则表达式，Java的正则表达式匹配引擎帮助我们完成匹配工作。

**Java正则表达式简单教程**

[https://www.runoob.com/java/java-regular-expressions.html](https://www.runoob.com/java/java-regular-expressions.html)

这就是本题目使用的正则表达式，对输入的字符串直接匹配，不需要额外的 `trim` 预处理。

```java
^[ ]*([+-]?)(\d+)
```

**该正则表达式的含义是**：匹配以零个或若干个空格为开头的，接着是"+"或"-"或数字，紧接着是若干个数字的子串，如果存在这样的字符串，则将匹配到的"+"或者"-"分为第一组，将数字字符串分为第二组。

> 正则表达式含义：
> 
> ^ ：字符串的起始位置 
>
> [ ] ：匹配一个空格
>
> *：零次或多次匹配前面的字符或子表达式
>
> ?：零次或一次匹配前面的字符或子表达式
>
> +：一次或多次匹配前面的字符或子表达式 
>
> [+]：匹配符号 +，
>
> [-]：匹配符号-
>
> \d ：匹配数字
>
> ()：可以使用括号对结果分组，第一组从前往后从1开始计数

另外一点是关于越界的处理。

```java
if (result > (Integer.MAX_VALUE - (number.charAt(i) - '0')) / 10) {
    return flag == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
}
```
以上是参照大神的写法，实现起来很简洁。核心的思想就是在每次 `result*10 + number` 之前，先除以10判断是否越界。常规的实现请看下面的代码。这样的实现巧妙地方在于不需要讨论正数越界还是负数越界，而是将 `Integer.MIN_VALUE` 本身看作是已经越界，但是对返回值并没有什么影响。因为即使将 `Integer.MIN_VALUE` 视作不越界，返回的也是 `Integer.MIN_VALUE`本身。这样做少些很多代码，实在是太妙了。

这段代码的意思等同如如下代码：

 ```java
if (flag > 0) {
    if (result > Integer.MAX_VALUE / 10) {
        return Integer.MAX_VALUE;
    }
    if (result == Integer.MAX_VALUE / 10 && number.charAt(i) - '0' > Integer.MAX_VALUE % 10) {
        return Integer.MAX_VALUE;
    }
} else if (flag < 0) {
    if (result > Integer.MAX_VALUE / 10) {
        return Integer.MIN_VALUE;
    }
    if (result == Integer.MAX_VALUE / 10 && number.charAt(i) - '0' > -(Integer.MIN_VALUE % 10)) {
        return Integer.MIN_VALUE;
    }
```

#### 实现代码

```java
public class Solution {
    public int myAtoi(String str) {
        String regex = "^[ ]*([ +-]?)(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return compose(matcher.group(1), matcher.group(2));
        }
        return 0;
    }

    public int compose(String sign, String number) {
        int flag = sign.equals("-") ? -1 : 1;
        int result = 0;

        for (int i = 0; i < number.length(); i++) {
            if (result > (Integer.MAX_VALUE - (number.charAt(i) - '0')) / 10) {
                return flag == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + number.charAt(i) - '0';
        }

        return flag * result;
    }

}
```
