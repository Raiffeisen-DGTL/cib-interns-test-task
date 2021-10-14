package raiffeisen.utils;


/**
 * @author voroningg
 */
public class Transactional {
    public static <T> T get(Object lockObject, GetFunction<T> function) {
        synchronized (lockObject) {
            return function.apply();
        }
    }

    public static void run(Object lockObject, RunFunction function) {
        synchronized (lockObject) {
            function.apply();
        }
    }
}
