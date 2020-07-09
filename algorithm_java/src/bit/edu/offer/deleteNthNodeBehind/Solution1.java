package bit.edu.offer.deleteNthNodeBehind;

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