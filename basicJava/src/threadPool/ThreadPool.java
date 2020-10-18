package threadPool;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executor threadPool = new ThreadPoolExecutor(2, 3, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        ExecutorService threadPool2 = Executors.newFixedThreadPool(2);

        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("task1");
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable thread is running");
            }
        };

        Callable<Integer> callablTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 5;
            }
        };

        Future<?> s = threadPool2.submit(runnable);
        Future<Integer> result = threadPool2.submit(callablTask);
        System.out.println("结果：" + s.get());
        System.out.println("结果：" + result.get());

        threadPool2.shutdown();
    }
}
