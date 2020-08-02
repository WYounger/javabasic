package thread;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int threadCount  = 200;
        //每个线程争夺的次数
        int count = 20;
        ConnectionPool pool = new ConnectionPool(10);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadCount);
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for(int i = 0; i < threadCount; i++){
            new Thread(new ConnectionRuuner(count,got,notGot,start,end,pool)).start();
        }
        start.countDown();
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
    }
}


/**
 * 模拟连接对象
 */
class Connection{}

/**
 * 连接池
 */
class ConnectionPool{
    //由于经常涉及到connection的连接和释放,所以选用LinkedList来存储connection
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initalSize){
        if(initalSize <= 0){
            initalSize = 10;
        }
        for (int i = 0; i < initalSize; i++){
            pool.addLast(new Connection());
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized(pool){
                //连接释放后需要进行通知,让其他消费者感知连接池已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //没有超时时间,死等
            if(mills <= 0) {
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                //有超时时间
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}


class ConnectionRuuner implements Runnable{
    //争抢次数
    private int count;
    private AtomicInteger got;
    private AtomicInteger notGot;
    private CountDownLatch start;
    private CountDownLatch end;
    private ConnectionPool  pool;

    public ConnectionRuuner(int count,AtomicInteger got,AtomicInteger notGot,CountDownLatch start,CountDownLatch end,ConnectionPool pool){
        this.count = count;
        this.got = got;
        this.notGot = notGot;
        this.start = start;
        this.end = end;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            //等待所有线程一起争夺
            start.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(count > 0){
            try {
                Connection connection = pool.fetchConnection(500);
                if(connection != null){
                    //use connection do some thing
                    TimeUnit.MILLISECONDS.sleep(10);
                    pool.releaseConnection(connection);
                    got.incrementAndGet();
                }else{
                    notGot.incrementAndGet();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                count --;
            }
        }
        end.countDown();
    }
}

