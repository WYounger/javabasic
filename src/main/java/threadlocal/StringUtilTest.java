package threadlocal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringUtilTest {

    static int str = 0;

    @Test
    public void test() {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                StringUtil.set(str + "");
//                str++;
//                try {
//                    Thread.sleep(10 - str);
//                    System.out.println(StringUtil.get());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
        A a = A.builder().a("test").b(1).build();
        System.out.println(a);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class A{
    private String a;
    private Integer b;
}

/**
 * 数据库连接池
 */
class ConnectionPool{
    /**
     * 用LinkedList数据结构保存连接
     */
    private LinkedList<DbConnection> pool = new LinkedList<>();

    /**
     * 初始化连接池
     * @param initialSize
     */
    private ConnectionPool(int initialSize){
        if(initialSize > 0){
            for (int i = 0; i < initialSize; i++){
                pool.add(ConnectionDriver.create());
            }
        }
    }

    /**
     * 释放连接
     * @param connection
     */
    public void releaseConnection(DbConnection connection){
        if(connection != null){
            synchronized(pool){
                pool.add(connection);
                //唤醒所有在等待池等待pool对象的锁的线程进入到同步队列
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接
     * @param mills 超时时间
     * @return
     * @throws InterruptedException
     */
    public DbConnection getConnection(long mills) throws InterruptedException {
        //获取pool的锁
        synchronized (pool){
            //完全超时
            if(mills < 0){
                while (pool.isEmpty()){
                    //pool为空，wait()释放锁，进入到等待队列
                    pool.wait();
                }
                //不空，取连接
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0){
                    //pool为空并且还未超时，进入等待队列
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                //退出while
                DbConnection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
/**
 * 模拟数据库驱动获取数据库连接
 */
class ConnectionDriver{
    public static DbConnection create(){
        return new DbConnection();
    }
}
/**
 * 模拟数据库连接
 */
class DbConnection {
    private Lock lock = new ReentrantLock();

    public void test(){
        lock.lock();
        try{
            //here do something
        }finally{
            lock.unlock();
        }
    }
}