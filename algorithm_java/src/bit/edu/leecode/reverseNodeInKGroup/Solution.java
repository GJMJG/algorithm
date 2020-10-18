package bit.edu.leecode.reverseNodeInKGroup;


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode tail = newHead;
        ListNode pre = newHead;

        while (head != null) {
            tail = tail.next;
            for (int a = 0; a < k; a++) {
                if (tail == null) {
                    return newHead.next;
                }
            }

            ListNode nextHead = tail.next;
            pre.next = reverseSubList(head, tail);
            head.next = nextHead;
            pre = head;
            tail = pre;
            head = nextHead;
        }

        return newHead.next;
    }

    public ListNode reverseSubList(ListNode startNode, ListNode endNode) {
        if (startNode == null || endNode == null) {
            return startNode;
        }

        ListNode currentNode = startNode;
        ListNode preNode = null;
        ListNode end = endNode.next;
        while (currentNode != end) {
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }

        return preNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i <= 5; ++i) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }

        new Solution().reverseKGroup(head, 2);
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
