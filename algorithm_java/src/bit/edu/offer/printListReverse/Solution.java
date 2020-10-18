package bit.edu.offer.printListReverse;

import java.util.ArrayList;
import java.util.List;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

/**
 * 基于递归，效率不高
 */
public class Solution {
    List<Integer> nodeList = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        printList(head);

        int[] result = new int[nodeList.size()];
        for (int i = 0; i < nodeList.size(); ++i) {
            result[i] = nodeList.get(i);
        }
        return result;
    }

    public void printList(ListNode node) {
        if (node != null) {
            printList(node.next);
            nodeList.add(node.val);
        }
    }
}