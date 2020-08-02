package list;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubListDemo {
    @Test
    public void testSubList() {
        List<Integer> list = new ArrayList<>();
        final int LENGTH = 10;
        for (int i = 0; i < LENGTH; i++) {
            list.add(i);
        }
        List<Integer> subList = list.subList(2, 5);
        subList.set(0, 99);
        list.forEach(System.out::println);
        list.remove(Integer.valueOf(99));
        list.forEach(System.out::println);
        subList.forEach(System.out::println);
        /**
         * 当通过subList()得到subList时，list.modCount会赋值给subList.modCount,
         * 当对原始list做结构上的改变，导致list.modCount改变,而subList.modCount不会变。
         * 当对subList进行遍历、添加、删除时，会检查list.modCount==subList.modCount,
         * 不想等就会抛出ConcurrentModificationException
         */
    }


    @Test
    public void testAsList() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.forEach(System.out::println);
        list.set(0, 123);
        list.forEach(System.out::println);
    }

    @Test
    public void testNpe() throws IOException {
    }


    static void testNotNull(int a, int b, int c, int d) {
        System.out.println(a + b + c + d);
        System.out.println();
    }

    @Test
    public void testReflect() {
        Class<A> aCLass = A.class;
        try {
            Constructor<A> constructor = aCLass.getDeclaredConstructor(String.class);
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            A younger = constructor.newInstance("younger");
            System.out.println(younger);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * producer:
     * synchronized(lock){
     *     while(count == MAX){
     *         this.wait();
     *     }
     *     count++;
     *     lock.notifyALl();
     * }
     *
     * consumer:
     * synchronized(lock){
     *     while(count == 0){
     *         lock.await();
     *     }
     *     count--;
     *     lock.notifyAll();
     * }
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}

class A {

    private String name;

    private A(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ClassA{" +
                "name='" + name + '\'' +
                '}';
    }
}

class 你好 {
    public String 名字;
}


