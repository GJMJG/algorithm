package thread;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过实现Runnable接口实现资源的共享，因为实现Runnable的某个类，实例化之后，可以开启多个线程去执行实例的run方法。
 * <p>本例中，通过继承Thread也可以实现资源的共享，原因在于，这里虽然使用继承方式实现，但在创建线程时，将这个类看作是实现了 Runnable的类，
 * 因此可以实现资源的共享，注意观察开启线程的方法</p>
 * <p>如果使用如下方法开启线程，是达不到资源共享目的的
 * <pre>
 *     <code>
 *         Thread thread1 = new Thread4();
 *         Thread thread2 = new Thread4();
 *         Thread thread3 = new Thread4();
 *
 *         thread1.start();
 *         thread2.start();
 *         thread3.start();
 *     </code>
 * </pre></p>
 */
public class ThreadSynchronized {
    public static void main(String[] args) {
        Thread4 ticket = new Thread4();
        Thread th1 = new Thread(ticket);
        Thread th2 = new Thread(ticket);
        Thread th3 = new Thread(ticket);

        th1.start();
        th2.start();
        th3.start();
    }
}

class Thread4 extends Thread {
    private int ticket = 200;

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public Object lock;

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //            synchronized (this) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "正在售卖第" + ticket + "张票");
                ticket--;
            } else {
                break;
            }
            //            }
        }
    }
}