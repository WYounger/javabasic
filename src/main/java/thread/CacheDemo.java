package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(()-> 1+1);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());
    }
}

class Cache<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock r = lock.readLock();
    private Lock w = lock.writeLock();

    public V get(K k) {
        V v = null;
        //查询缓存是否存在
        r.lock();
        try {
            v = map.get(k);
        } finally {
            r.unlock();
        }
        //存在，直接返回
        if (v != null) {
            return v;
        }
        //不存在，查询数据库
        w.lock();
        try {
            //再次判断是否存在。因为可能在其他线程先访问过数据库
            v = map.get(k);
            if (v == null) {
                //查询数据库,获取v，写入缓存
                map.put(k, v);
            }
            return v;
        } finally {
            w.unlock();
        }
    }

    public V put(K k, V v) {
        w.lock();
        try {
            return map.put(k, v);
        } finally {
            w.unlock();
        }
    }
}
