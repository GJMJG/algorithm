package bit.edu.offer.duplicateComplexLink;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Solution {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode tempNode = pHead;
        while (tempNode != null) {
            RandomListNode temp = tempNode.next;
            RandomListNode newNode = new RandomListNode(tempNode.label);
            tempNode.next = newNode;
            newNode.next = temp;
            tempNode = temp;
        }

        tempNode = pHead;
        while (tempNode != null) {
            RandomListNode complexLink = tempNode.random;
            if (complexLink != null) {
                tempNode.next.random = complexLink.next;
            }
            tempNode = tempNode.next.next;
        }

        RandomListNode clonedListHead = pHead.next;
        tempNode = clonedListHead;
        while (tempNode.next != null) {
            tempNode.next = tempNode.next.next;
            tempNode = tempNode.next;
        }

        return clonedListHead;
    }

    public RandomListNode cloneAllNode(RandomListNode pHead) {
        RandomListNode tempNode = pHead;
        while (tempNode != null) {
            RandomListNode temp = tempNode.next;
            RandomListNode newNode = new RandomListNode(tempNode.label);
            tempNode.next = newNode;
            newNode.next = temp;
            tempNode = temp;
        }
        return pHead;
    }

    public RandomListNode cloneComplexEdge(RandomListNode pHead) {
        RandomListNode tempNode = pHead;
        while (tempNode != null) {
            RandomListNode complexLink = tempNode.random;
            if (complexLink != null) {
                tempNode.next.random = complexLink.next;
            }
            tempNode = tempNode.next.next;
        }
        return pHead;
    }

    public RandomListNode getClonedListHead(RandomListNode pHead) {
        RandomListNode clonedListHead = pHead.next;
        RandomListNode tempNode = clonedListHead;
        while (tempNode.next != null) {
            tempNode.next = tempNode.next.next;
            tempNode = tempNode.next;
        }
        return clonedListHead;
    }

}