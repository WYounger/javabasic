package thread;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(()->{
            return "return value";
        });
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10));

        executor.submit(task);

        String result = task.get();
        System.out.println(result);
    }

}
