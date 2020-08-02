package concurrent_tools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    @Test
    public void testBarrier() throws BrokenBarrierException, InterruptedException {
        /**
         *CyclicBarrier(N,action)
         * 调用await()一次,N--
         * 当N=0时，首先会执行action,然后其他线程从await()继续执行
         */
        CyclicBarrier barrier = new CyclicBarrier(2,()-> System.out.println(1));
        new Thread(()->{
            try {
                barrier.await();
                System.out.println(2);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        barrier.await();
        System.out.println(3);
    }
}

