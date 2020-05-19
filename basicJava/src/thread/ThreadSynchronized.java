package thread;

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
            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在售卖第" + ticket + "张票");
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}