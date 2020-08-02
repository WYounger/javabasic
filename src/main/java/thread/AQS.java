package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class AQS {
}


class TwinsLock1 implements Lock{

    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count <= 0){
                throw new IllegalArgumentException("count必须大于0");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount < 0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int reduceCount) {
            for(;;){
                int current = getState();
                int newCount = current + reduceCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
                return false;
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

class  HH{
    @Test
    public void test() throws InterruptedException {
        final Lock lock = new TwinsLock1();

        for (int i = 0; i < 10; i++) {
           Thread t = new Thread(()->{
               while (true) {
                   lock.lock();
                   try {
                       TimeUnit.SECONDS.sleep(1);

                       System.out.println(Thread.currentThread().getName());
                       TimeUnit.SECONDS.sleep(1);

                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } finally {
                       lock.unlock();
                   }
               }
           });
           t.setDaemon(true);
           t.start();
        }

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);

            System.out.println();
        }
    }
}