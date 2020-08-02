package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class InterruptTest {
    @Test
    public void test() throws InterruptedException {
        Runner runner = new Runner();
        new Thread(runner).start();
        //main线程休眠10s,中断runner线程
        TimeUnit.SECONDS.sleep(10);
        runner.cancel();
    }
}

class Runner implements Runnable{
    private long i;
    private volatile boolean on = true;
    @Override
    public void run() {
        while(on && !Thread.currentThread().isInterrupted()){
            i++;
        }
        System.out.println("i = " + i);
    }
    public void cancel(){
        on = false;
    }
}