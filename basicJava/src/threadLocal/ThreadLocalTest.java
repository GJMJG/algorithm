package threadLocal;

/**
 * ThreadLocal 用于创建一个线程级别的局部变量，该变量为线程私有，仅自己可见，不影响其他线程。在多线程情况下保证成员变量的安全，常用的方法有
 * get() set() initialValue
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal1.get());
        threadLocal1.set(99);
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal1.get());

        new Thread(new MyRun()).start();
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {
        @Override
        public void run() {
            threadLocal1.set((int) (Math.random() * 99));
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal1.get());
        }
    }
}
