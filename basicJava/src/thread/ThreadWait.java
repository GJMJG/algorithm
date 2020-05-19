package thread;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * 演示线程的{@code wait}方法，要求如下：
 * 建立三个线程，A线程打印10次A，B线程打印10次B，C线程打印10次C。要求线程同时进行，交替打印10次ABC。
 */
public class ThreadWait {
    public static void main(String[] args) throws InterruptedException {
/*        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Thread6 thA = new Thread6("A", c, a);
        Thread6 thB = new Thread6("B", a, b);
        Thread6 thC = new Thread6("C", b, c);

        new Thread(thA).start();
        Thread.sleep(100);
        new Thread(thB).start();
        Thread.sleep(100);
        new Thread(thC).start();
        Thread.sleep(100);*/

        OrderPrint printA = new OrderPrint("A", 0);
        OrderPrint printB = new OrderPrint("B", 1);
        OrderPrint printC = new OrderPrint("C", 2);

        printA.start();
        printB.start();
        printC.start();
    }
}

class Thread6 implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    public Thread6(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {   //上一个对象锁，先申请上一个对象的锁，如果上个线程释放对象锁，则获取该对象锁
                synchronized (self) {   // 当前对象锁
                    System.out.print(name + ((count == 1 && name.equals("C")) ? "" : "->"));
                    count--;
                    self.notify(); // 唤醒下一个等待线程
                }
                try {
                    prev.wait();// 释放当前线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 使用取余的方法实现
 */
class OrderPrint extends Thread {
    private static int[] lock = new int[0];
    private static int count = 30;
    private String name;
    private int id;

    public OrderPrint(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void run() {
        while (count > 0) {
            synchronized (lock) {
                if (count % 3 == id) {
                    System.out.print(name + " -> ");
                    count--;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}