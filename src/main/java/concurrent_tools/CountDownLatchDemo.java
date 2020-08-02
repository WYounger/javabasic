package concurrent_tools;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLatchDemo {

    @Test
    public void testCountDownLatch() throws InterruptedException {
        /**
         * 适合分而治之、自下而上的流程
         * 初始CountDownLatch(N),每调用一次countDown(),N--
         * 当 N = 0时，调用await()的线程可以继续向下执行
         */
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(()->{
            System.out.println(1);
            latch.countDown();
        }).start();
        new Thread(()->{
            System.out.println(2);
            latch.countDown();
        }).start();
        latch.await();
        System.out.println(3);
    }
}


