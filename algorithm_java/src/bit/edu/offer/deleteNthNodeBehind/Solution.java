package bit.edu.offer.deleteNthNodeBehind;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

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