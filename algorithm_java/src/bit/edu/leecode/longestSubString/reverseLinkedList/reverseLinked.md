## 反转链表

### 题目描述


### 方法一：指针迭代

#### 思路分析

遍历列表，将当前链表的 `next`指针指向上一个节点，因此需要一个 `pre` 节点记录上一个指针。由于节点只有指向下一个节点的指针，指向上个节点之后，就找不到下个节点了，因此还需要一个 `next` 指针，记录下一个节点。链表反转后， `pre` 指针就是原链表的最后一个节点，也是新链表的头节点。

#### 代码实现

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode preNode = null;

        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;
    }
}
```

#### 复杂度分析

- 时间复杂度 `O(N)`，遍历链表
- 空间复杂度 `O(1)`
