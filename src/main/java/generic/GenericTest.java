package generic;

import org.junit.jupiter.api.Test;

public class GenericTest {

    @Test
    public void test() throws InterruptedException {
        Thread thread = new Thread(()->{System.out.println("哈哈哈哈");});
        thread.start();
        thread.join();
        System.out.println("end the main");
    }
}


abstract class Factory<T>{

    final T e;

    public Factory(){
        e = create();
    }
    abstract T create();
}

class A extends Factory<String>{
    @Override
    String create() {
        return null;
    }
}

class MyList<T>{
    private T[] arr;
    public MyList(){
        arr = (T[]) new Object[10];
    }

    public void put(int index,T e){
        arr[index] = e;
    }

    public T get(int index){
        return  arr[index];
    }

    public T[] getArr(){
        return arr;
    }
}

