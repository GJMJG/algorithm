## 字符串转换整数

### 题目描述


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

