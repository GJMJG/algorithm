package thread;

/**
 * 通过继承{@link Thread} 的方式实现多线程
 */
class Thread1 extends Thread {
    private String threadName; // 用于标示不同的线程

    public Thread1(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + "运行，此时的 i = " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 通过实现{@link Runnable}的方式实现多线程
 */
class Thread2 implements Runnable {
    private String threadName;

    public Thread2(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + "运行，此时的 i = " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadMain {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //通过继承的方式实现多线程，创建线程直接new出来即可
        Thread1 thread1 = new Thread1("线程 A");
        Thread1 thread2 = new Thread1("线程 B");

        thread1.start();
        thread2.start();
        //通过实现Runnable接口实现多线程，创建线程需要首先new Thread，并将实现线程的类放到Thread实例中，然后启动
        new Thread(new Thread2("线程 1")).start();
        new Thread(new Thread2("线程 2")).start();
    }
}

