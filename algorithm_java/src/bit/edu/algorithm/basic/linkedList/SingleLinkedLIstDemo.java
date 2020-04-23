package bit.edu.algorithm.basic.linkedList;

public class SingleLinkedLIstDemo {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
//        list.add(new Node(1, "NO1"));
//        list.add(new Node(2, "NO2"));
//        list.add(new Node(3, "NO3"));
        list.addByOrder(new Node(4, "NO4"));
        list.addByOrder(new Node(2, "NO2"));
        list.addByOrder(new Node(3, "NO3"));
        list.addByOrder(new Node(1, "NO1"));
        list.undate(new Node(6, "NEWNO3"));
        list.listNode();
        System.out.println("删除链表节点");
        list.deleteNode(1);
        list.deleteNode(2);
        list.deleteNode(3);
        list.deleteNode(4);
        list.listNode();
    }
}

class SingleLinkedList {
    private Node head = new Node(0, "");
    /**
     * 在链表的最后添加一个节点
     */
    public void add(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 在链表有序地添加一个节点，链表中的元素按照从小到大顺序排列
     */
    public void addByOrder(Node node) {
        Node temp = head;
        boolean flag = true;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.number > node.number) {
                break;
            } else if (temp.next.number == node.number) {
                System.out.printf("链表中已存在 %d 节点，不能继续插入\n", node.number);
                flag = false;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            node.next = temp.next;
            temp.next = node;
        }
    }

    public void undate(Node node) {
        Node temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.number == node.number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.setName(node.getName());
        }
    }

    public void deleteNode(int number) {
        if(number == 0) {
            return;
        }
        Node temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.number == number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到待删除节点 %d \n", number);
        }
    }

    public void listNode() {
        Node temp = head;
        if (temp.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node {
    public int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public Node next;

    public Node(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}