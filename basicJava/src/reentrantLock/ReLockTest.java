package reentrantLock;

import sun.misc.Lock;

/**
 * 不可重入锁，如果某个方法在执行时，已经获取到该锁，那么在方法中尝试再次获取锁时，线程被阻塞。
 */
public class ReLockTest {
    Lock lock = new Lock();

    public static void main(String[] args) throws InterruptedException {
        ReLockTest test = new ReLockTest();
        test.method();
        test.doSomthing();
    }

    public void method() throws InterruptedException {
        lock.lock();
        System.out.println("method() 方法获取锁，并开始执行");
        doSomthing();
        lock.unlock();
    }

    public void doSomthing() throws InterruptedException {
        lock.lock();
        System.out.println("soSomthing() 方法获取锁，并开始执行");
        lock.unlock();
    }
}

/**
 * 实现一把可重入锁，核心思想是：记录当前占用的线程，记录线程锁定的次数
 */
class ReLock {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int holdCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        while (isLocked && lockedBy != t) {
            wait();
        }
        isLocked = true;
        lockedBy = t;
        holdCount++;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            holdCount--;
            if (holdCount == 0) {
                isLocked = false;
                notify();
                lockedBy = null;
            }
        }
    }

    public int getHoldCount() {
        return holdCount;
    }
}