package maptest;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.function.Consumer;

public class Main {

    @Test
    void test() {
        MyArray<String> array = new MyArray<>(new String[]{"1", "3", "2"});
        for (String str : array) {
            System.out.println(str);
        }
    }
}

class MyArray<T> implements Iterable<T> {
    private T[] array;

    public MyArray(T[] array) {
        this.array = array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;

            @Override
            public void remove() {
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
            }

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public T next() {
                return array[i++];
            }
        };
    }
}
