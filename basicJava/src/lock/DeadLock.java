package lock;

/**
 * 实现一个死锁，死锁产生的四个条件：
 * <li>互斥使用：资源不可同时被多个线程使用</li>
 * <li>资源不可抢占：资源申请者不可以强行从资源占有者中夺取</li>
 * <li>占有且等待：资源申请者在申请其他资源时，保持对原有资源的占有</li>
 * <li>循环等待：资源申请者相互等待，形成一个环路</li>
 */
public class DeadLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        // 第一个线程，拥有a资源，想要获取b资源
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + " get a lock");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "want to get b");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + " get b lock");
                }
            }
        }).start();

        //第二个线程，拥有b资源，想要获取a资源
        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " get b lock");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "want to get a");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + " get a lock");
                }
            }
        }).start();
    }
}
