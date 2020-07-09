## 力扣19. 删除链表的倒数第n个节点

### 题目描述

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

**示例：**

>  给定一个链表: 1->2->3->4->5, 和 n = 2.
>
> 当删除了倒数第二个节点后，链表变为 1->2->3->5.

**说明：**

给定的 n 保证是有效的。

**进阶：**

你能尝试使用一趟扫描实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### 方法一：双指针

#### 思路分析

链表的很多问题都可以考虑使用多个指针的方法。链表的删除需要定位到要删除节点的前一个节点，因此难度在于如何定位到正确的位置。可以计算出链表的长度，再减去 `n`，定位到 `length - n + 1` 的位置即可删除，但是这样做需要遍历两遍链表，第一遍遍历为了获取链表长度。双指针就是一前一后两个指针，两个指针之间的间隔正好是 n ，如果前面的指针走到末尾，那么后面指针便定位到正确的位置。

注意代码中对于 **删除头节点**的操作，需要做额外的判断，是否删除的是头节点，因为头节点没有上一个指针，和普通节点略有不同。

#### 代码实现

```java
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode front = head;
        ListNode behind = head;
        int i = 0;
        while (i < n) {
            front = front.next;
            ++i;
        }

        // 表示要删除的节点是头节点
        if (front == null) {
            return head.next;
        }

        while (front.next != null) {
            front = front.next;
            behind = behind.next;
        }

        behind.next = behind.next.next;
        return head;
    }
}
```



#### 复杂度分析

- 时间复杂度 `O(N)`，遍历一遍链表
- 空间复杂度 `O(1)`

### 代码优化

#### 思路分析

在方法一的实现中，头节点的删除需要做额外的判断，那能不能将头节点处理成普通节点，这样删除时不需额外判断。**可以使用一个哑节点**，这个节点只是用来充当额外的头节点，用来处理原先头节点。**所谓没有条件创造条件**。

#### 实现代码

```java
public class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, behind = dummy;

        for (int i = 0; i < n + 1; ++i) {
            if (first == null) {
                return head;
            }
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            behind = behind.next;
        }

        behind.next = behind.next.next;
        return dummy.next;
    }
}
```
