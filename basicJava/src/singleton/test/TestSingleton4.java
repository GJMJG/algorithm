package singleton.test;

import singleton.implement.Singleton4;

import java.util.concurrent.*;

public class TestSingleton4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton4> c = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Singleton4> future1 = es.submit(c);
        Future<Singleton4> future2 = es.submit(c);

        Singleton4 s1 = future1.get();
        Singleton4 s2 = future2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
    }
}
