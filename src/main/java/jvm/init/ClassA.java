package jvm.init;

import lombok.Data;

/**
 * @title: ClassA
 * @Author younger
 * @Date: 2020/7/30 23:10
 * @Version 1.0
 */
public class ClassA {
    private static final ClassA instance = new ClassA();
    
    private static int a;
    private static int b;

    static {
        a = 1;
//        b = 0;
        System.out.println("in static block");
    }

    public static ClassA getInstance() {
        return instance;
    }

    public ClassA(){
        System.out.println("in constructor");
        a++;
        b++;
    }

}
