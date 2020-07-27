package bit.edu.interview.baidu;

/**
 * 两个线程实现 a b 交替打印
 */
public class TwoThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread4("a", TwoThread.class));
        Thread thread2 = new Thread(new MyThread4("b", TwoThread.class));
        thread1.start();
        thread2.start();
    }
}

class MyThread4 implements Runnable {
    private Object lock;
    private String content;

    public MyThread4(String content, Object lock) {
        this.content = content;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                System.out.println(content);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
