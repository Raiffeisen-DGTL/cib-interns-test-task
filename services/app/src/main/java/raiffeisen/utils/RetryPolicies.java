package raiffeisen.utils;

/**
 * @author voroningg
 */
public class RetryPolicies {

    public static <T> T get(GetFunction<T> function) throws Exception {
        Exception exception = null;
        for (int i = 0; i < 3; i++) {
            try {
                return function.apply();
            } catch (Exception ex) {
                exception = ex;
            }
        }
        throw exception;
    }

    public static void run(RunFunction function) throws Exception {
        Exception exception = null;
        for (int i = 0; i < 3; i++) {
            try {
                function.apply();
                return;
            } catch (Exception ex) {
                exception = ex;
            }
        }
        throw exception;
    }
}
