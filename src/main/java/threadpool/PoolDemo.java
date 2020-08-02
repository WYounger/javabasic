package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                10,
                20,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        for (int i = 1; i <= 100; i++){
            pool.execute(new Task(i));
        }
    }
}

class Task implements Runnable{
    private int i;
    public Task(int i){
        this.i = i;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1 * 1000);
            System.out.println(Thread.currentThread().getName() + "---"+i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

