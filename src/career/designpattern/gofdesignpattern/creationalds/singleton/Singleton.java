package career.designpattern.gofdesignpattern.creationalds.singleton;

public class Singleton {

    // volatile prevents half-initialized object visibility
    private static volatile Singleton instance;

    // private constructor prevents external instantiation
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {                    // First check (no locking)
            synchronized (Singleton.class) {        // Acquire lock
                if (instance == null) {            // Second check (inside lock)
                    instance = new Singleton();     // Create instance
                }
            }
        }
        return instance;
    }
}
