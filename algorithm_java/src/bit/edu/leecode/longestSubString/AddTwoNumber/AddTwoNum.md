## 两数相加

### 题目描述

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

> 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
>
> 输出：7 -> 0 -> 8
>
> 原因：342 + 465 = 807

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 大数相加思路

#### 思路分析

按照正常计算加法的思路，先从个位数字相加开始，超过十则进位。接着计算十位，将两个数字的十位相加，并加上个位数的进位，得到的结果超过十则继续进位。题目中的链表正好是逆序存放的，这就是说每个表示数字的链表的头节点表示 **个位** 数，只要按照正常的计算顺序从链表的头节点开始计算即可。

需要注意以下几点：

- 两个链表长度可能不同，短的链表不够的位数需要补零
- 最终返回新链表的头节点，因此在第一次计算（计算个位数）逻辑稍有不同，需要初始化头节点
- 两个链表的数字都计算之后，不能立刻退出循环，此时可能仍然存在进位，例如计算 `l1= 5->null, l2= 5->null, result=0->1`，此时存在进位，需要在最终结果中多一次进位。

#### 实现代码

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        int over = 0;   // 是否有进位
        int currentNum = 0;
        boolean isFirst = true;   // 在初始化头节点的时候用到
        ListNode head = null;   // 最终返回的头节点
        // 同时维护两个指针，保证链表不断
        ListNode pre = null;
        ListNode current = null;
        // 两链表之一不为空则继续循环。如果 l1、l2为空，但此时有进位（如 l1= 5->null, l2= 5->null, result=0->1）则需要多循环一次
        while (l1 != null || l2 != null || over == 1) {
            current = new ListNode(0);
            // 节点为空则补0
            int val1 = (l1 == null) ? 0 : l1.val;
            int val2 = (l2 == null) ? 0 : l2.val;
            currentNum = (val1 + val2 + over) % 10;
            over = (val1 + val2 + over >= 10) ? 1 : 0;   // 更新进位
            current.val = currentNum;
            // 初始化头节点
            if (isFirst) {
                pre = current;
                head = pre;
                isFirst = false;
            } else {
                // 连接节点，并向后移动 pre 指针
                pre.next = current;
                pre = current;
            }
            // 向后移动结点指针。可能存在 l1 l2 为空，但还需要循环的情况（上述进位为 1），需要判空
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        return head;
    }
}
```

#### 复杂度分析

- 时间复杂度 `O(N)`，只需要同时遍历一次两个链表
- 空间复杂度 `O(1)`，没有额外的内存开销
