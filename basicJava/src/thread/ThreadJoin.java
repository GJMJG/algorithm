package thread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 演示线程的join方法
 */
public class ThreadJoin {
    public static void main(String[] args) {
        //主线程开始运行
        System.out.println(Thread.currentThread().getName() + "开始运行~");
        // 创建两个线程
        Thread th1 = new Thread5();
        Thread th2 = new Thread5();
        th1.setName("线程 A");
        th2.setName("线程 B");
        //启动两个线程
        th1.start();
        //分别对两个线程使用join方法
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th2.start();
        try {
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这时thread1 和 thread2 都执行完毕之后才能执行主线程打印此句话因为两个子线程都被主线程调用了join() 方法");
        System.out.println(Thread.currentThread().getName() + "运行结束~");
    }
}

/**
 * 该线程每隔半秒打印从1到5五个数字
 */
class Thread5 extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + "开始运行");
        for (int i = 0; i < 5; i++) {
            System.out.println(currentThread().getName() + "---->" + i);
        }
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }
}