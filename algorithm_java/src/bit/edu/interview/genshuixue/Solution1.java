package bit.edu.interview.genshuixue;

class Node {
    int data;
    Node next;
}

public class Solution1 {
    /**
     * 两个指针 a,b 分别指向头节点 比较a ，b数据，取出小的那个，链接到新链表上 返回新链表头节点
     *
     * @param n1
     * @param n2
     * @return
     */
    public Node mergeTwoLists(Node n1, Node n2) {
        Node a = n1;
        Node b = n2;
        Node head = new Node();
        Node tempNode = head;

        while (a != null || b != null) {
            if (a == null) {
                tempNode.next = b;
                break;
            } else if (b == null) {
                tempNode.next = a;
                break;
            }

            if (a.data > b.data) {
                tempNode.next = b;
                b = b.next;
            } else {
                tempNode.next = a;
                a = a.next;
            }

            tempNode = tempNode.next;
        }

        return head.next;
    }
}
