package concurrent_tools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    final int THREAD_COUNT = 30;

    @Test
    public void testSemaphore() throws InterruptedException {
        /**
         * 控制同时访问特定资源的线程数量
         * new Semaphore(N)；
         * acquire() 获取许可证
         * release() 释放许可证
         */
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        Semaphore semaphore = new Semaphore(10);
        for(int i = 0; i < THREAD_COUNT; i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    Thread.sleep(5 * 1000);
                    System.out.println(Thread.currentThread().getName());
                    latch.countDown();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.await();
    }
}
