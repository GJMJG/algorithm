package singleton;

public class Singleton7 {
    private Singleton7() {
    }

    private static class Inner {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public Singleton7 getInstance() {
        return Inner.INSTANCE;
    }
}
