package proxy;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    public void test() throws NoSuchMethodException {
        A a = (A) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{A.class},new Handler(new B()));
        a.do1("",0);
        a.do2();
        a.do3();
    }
}

interface A{
    void do1(String a,Integer b);
    void do2();
    void do3();
}

class B implements A{
    @Info(info = "hello do1")
    @Override
    public void do1(String a,Integer b) {
        System.out.println("do1");
    }

    @Info(info = "hello do2")
    @Override
    public void do2() {
        System.out.println("do2");
    }

    @Info(info = "hello do3")
    @Override
    public void do3() {
        System.out.println("do3");
    }
}

class Handler implements InvocationHandler{

    private A a;
    public Handler(A a){
        this.a = a;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<? extends A> aClass = a.getClass();
        Method instanceMethod = aClass.getMethod(method.getName(), method.getParameterTypes());
        if(instanceMethod.isAnnotationPresent(Info.class)){
            Info info = instanceMethod.getAnnotation(Info.class);
            System.out.println(info.info());
        }
        return method.invoke(a,args);
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface  Info{
    String info() default "";
}
