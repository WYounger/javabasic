package sort;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestSort {
    @Test
    public void testSort() throws InterruptedException {
        final Object lock = new Object();
        char[] a = "123456".toCharArray();
        char[] b = "ABCDEF".toCharArray();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                synchronized (lock) {
                    System.out.println(a[i]);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                synchronized (lock) {
                    System.out.println(b[i]);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}

class A implements Comparable<A> {
    private int a;

    public A(int a) {
        this.a = a;
    }

    @Override
    public int compareTo(A o) {
        return a - o.getA();
    }

    public int getA() {
        return a;
    }

    @Override
    public String toString() {
        return a + "";
    }
}

class B {
    private A a;

    public B(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    @Override
    public String toString() {
        return a.toString();
    }
}

/**
 * 单例模式实现: 双重检验锁
 */
class Singleton {
    private static volatile Singleton singleton;

    public static Singleton getSingletion() {
        //第一次检验
        if (singleton == null) {
            //加锁
            synchronized (Singleton.class) {
                //第二次检验
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

/**
 * CAS 模拟计数器
 */
@Data
class Count {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i;

    /**
     * 使用CAS实现线程安全计数器
     */
    public void safeCount() {
        //CAS 不成功一直死循环
        for (; ; ) {
            //获取值
            int i = atomicI.get();
            //比较并设值
            boolean success = atomicI.compareAndSet(i, ++i);
            //设置成功，结束循环
            if (success) {
                break;
            }
        }
    }

    /**
     * 线程不安全计数器
     */
    public void count() {
        i++;
    }
}
