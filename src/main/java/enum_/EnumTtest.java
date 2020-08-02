package enum_;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class EnumTtest {
    public static void main(String[] args) {
        //获取所有枚举常量
        Season[] values = Season.values();
        Arrays.stream(values).forEach(System.out::println);
        Season.SPRING.echo();
        test(Season.WINTER);


        Class clazz = EnumTtest.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
            //判断方法上受否有该注解
            if(method.isAnnotationPresent(MyTest.class)){
                //获取标注在方法的注解实例
                MyTest annotation = method.getAnnotation(MyTest.class);
                //获取注解对象的数据
                String msg = annotation.msg();
                int age = annotation.age();
                System.out.println(msg + age);
            }else{
                System.out.println("没有被MyTest注解");
            }
        }
    }

    static void test(Season season) {
        switch (season) {
            case SPRING:
                System.out.println(Season.SPRING);
                break;
            case AUTUMN:
                System.out.println(Season.AUTUMN);
            default:
                System.out.println("what do you want?");
        }
        /**
         * 枚举相比常量的优点:
         * 1.可读性更好
         * 2.类型更加安全
         * 3.功能更加强大
         */
    }


    @MyTest(msg = "this is a msg!")
    void testMyTestAnnotation(){

    }
}

/**
 * @author Young
 */
enum Season {
    //定义所有枚举常量
    SPRING("春") {
        @Override
        public void echo() {
            System.out.println(this);
        }
    },
    SUMMER("夏") {
        @Override
        public void echo() {
            System.out.println(this);
        }
    },
    AUTUMN("秋") {
        @Override
        public void echo() {
            System.out.println(this);
        }
    },
    WINTER("冬") {
        @Override
        public void echo() {

        }
    };

    //枚举常量关联的数据。
    private final String msg;

    Season(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }

    public abstract void echo();
}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyTest {

    String msg() default "default msg";

    int age() default 0;
}

