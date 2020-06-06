package bit.edu.leecode.longestSubString.AddTwoNumber;

/**
 * 两数相加
 */

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int x) { val = x; } }
 */
class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        int over = 0;
        int currentNum = 0;
        boolean isFirst = true;
        ListNode head = null;
        ListNode current = new ListNode(0);
        ListNode pre = null;

        while (l1 != null || l2 != null) {
            int val1 = (l1 == null) ? 0 : l1.val;
            int val2 = (l2 == null) ? 0 : l2.val;
            currentNum = (val1 + val2 + over) % 10;
            over = (val1 + val2 + over >= 10) ? 1 : 0;
            current.val = currentNum;
            pre.next = current;
            pre = current;
            if (isFirst) {
                head = pre;
                isFirst = false;
            }
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
