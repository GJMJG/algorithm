package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：也叫可递归锁，需要主动释放锁资源
 * 案例演示了可重入锁的使用，开启了多个线程，某个线程获取锁后，执行完该任务才释放锁。
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        MyService service = new MyService();

        MyThread thread1 = new MyThread(service);
        MyThread thread2 = new MyThread(service);
        MyThread thread3 = new MyThread(service);
        MyThread thread4 = new MyThread(service);
        MyThread thread5 = new MyThread(service);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    static class MyService {
        private Lock lock = new ReentrantLock();

        public void testMethod() {
            try {
                lock.lock();
                System.out.println("start wait --");
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread name : " + Thread.currentThread().getName() + "  " + (i + 1));
                }
            } finally {
                lock.unlock();
            }

        }
    }

    static class MyThread extends Thread {
        private MyService service;

        public MyThread(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }
}
