package innerclass;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        System.out.println(list.toString());
    }
}


@FunctionalInterface
interface A{
    void doSomething(String param1,int param2);
}

class B{
    private A a;
    public B(A a){
        this.a = a;
    }
    public void f(){
        System.out.println("at first");
        a.doSomething("abc",1);
        System.out.println("final");
    }
}