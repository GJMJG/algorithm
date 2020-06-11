package bit.edu.leecode.longestSubString.swapNode;

/**
 * 指针方式
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = null;
        ListNode current = head;
        ListNode newHead = head.next;
        ListNode pre = head;
        while (current != null && current.next != null) {
            pre.next = current.next;
            temp = current.next.next;
            current.next.next = current;
            current.next = temp;

            pre = current;
            current = current.next;
        }

        return newHead;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}