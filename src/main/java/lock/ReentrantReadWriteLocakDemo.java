package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLocakDemo {
}


/**
 * 利用重入读写锁实现一个缓存工具
 */
class Cache{
    /**
     * 缓存容器
     */
    private static Map<String,Object> map  = new HashMap<>();
    /**
     * 创建可重入读写锁，获取读锁和写锁
     */
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static Lock r = rwl.readLock();
    private static Lock w = rwl.writeLock();

    /**
     * 依据key获取value
     * @param key
     * @return
     */
    public static final Object get(StringBuilder key){
        r.lock();
        try{
            return map.get(key);
        }finally{
            r.unlock();
        }
    }

    /**
     * 添加key-value
     * @param key
     * @param value
     * @return
     */
    public static final Object put(String key,Object value){
        w.lock();
        try{
            return map.put(key,value);
        }finally{
            w.unlock();
        }
    }

    /**
     * 依据key删除key-value
     * @param key
     * @return
     */
    public static final Object remove(String key){
        w.lock();
        try{
            return map.remove(key);
        }finally {
            w.unlock();
        }
    }
}