package threadlocal;


public class StringUtil {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String data){
        threadLocal.set(data);
    }

    public static String get(){
        return threadLocal.get();
    }
}