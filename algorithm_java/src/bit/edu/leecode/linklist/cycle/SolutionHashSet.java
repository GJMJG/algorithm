package bit.edu.leecode.linklist.cycle;

import java.util.HashSet;
import java.util.Set;

public class SolutionHashSet {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> hashSet = new HashSet<ListNode>();
        while (head != null) {
            if (hashSet.contains(head)) {
                return true;
            } else {
                hashSet.add(head);
            }
            head = head.next;
        }
        return false;
    }
}
