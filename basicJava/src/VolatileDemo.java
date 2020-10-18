import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示volatile，保证变量在线程之间的可见性，而不保证变量的原子性
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData data = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.number = 60;
            System.out.println("modify the number to the 60");
        }, "A").start();

        while (data.number == 0) {
        }
        System.out.println(Thread.currentThread().getName() + "thread is over");

        testVolatileAutomic();
    }

    static public void testVolatileAutomic() {
        MyData data = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    data.add();
                    data.addAtomicInteger();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.print(Thread.currentThread().getName() + "\t finlly number value: " + data.number);
        System.out.println("\t finlly atomicInteger value: " + data.atomicInteger);
    }
}

class MyData {
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void change() {
        this.number = 60;
    }

    public void add() {
        number++;
    }

    public void addAtomicInteger() {
        atomicInteger.getAndIncrement();
    }
}