package thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class SemaphoreDemo {

    @Test
    public void testSem() throws InterruptedException {
        ObjPool<String,Long> pool = new ObjPool<>(2,"1");
        Long l =pool.exec(t -> {
            return Long.parseLong(t);
        });
        System.out.println(l);
    }

}

class ObjPool<T, R> {
    /**
     * 对象池
     */
    private final List<T> pool;

    /**
     * 信号量
     */
    private final Semaphore semaphore;

    /**
     * 初始化
     * @param size 对象池大小
     * @param t 对象池对象
     */
    public ObjPool(int size, T t) {
        pool = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    /**
     * @param func
     * @return
     * @throws InterruptedException
     */
    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        //获取信号量
        semaphore.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            //释放信号量
            semaphore.release();
        }
    }
}
