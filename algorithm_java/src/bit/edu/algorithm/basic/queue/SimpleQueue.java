package bit.edu.algorithm.basic.queue;

import java.util.Scanner;

public class SimpleQueue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        Scanner scanner = new Scanner(System.in);
        char input = ' ';
        boolean loop = true;
        while (loop) {
            System.out.println("s(show) ：显示队列");
            System.out.println("e(exit) ：退出程序");
            System.out.println("a(add) ：添加元素");
            System.out.println("g(get) ：弹出元素");
            System.out.println("h(hean) ：显示队列头部元素");
            input = scanner.next().charAt(0);
            switch (input) {
                case 's':
                    try {
                        queue.showList();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.println("输入一个数：");
                        queue.addElement(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int value = queue.popElement();
                        System.out.printf("输出数据：%d \n", value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列的头数据是：%d \n", queue.showHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("退出程序");
    }
}

class ArrayQueue {
    private int[] array;
    private int maxSize;
    private int front;
    private int rear;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = rear = -1;
    }

    public void addElement(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满，不能添加数据");
        }
        ++rear;
        array[rear] = n;
    }

    public int popElement() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空，不能弹出数据");
        }
        return array[++front];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据可以展示");
        }
        return array[front + 1];
    }

    public void showList() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能列出队列数据");
        }
        System.out.println("队列中的数据：\n");
        for (int i = 0; i <= rear; i++) {
            System.out.printf("array[%d]:%d \n", i, array[i]);
        }
    }
}
