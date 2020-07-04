package bit.edu.leecode.longestSubString.AddTwoNumber;

/**
 * 两数相加
 */
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

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
