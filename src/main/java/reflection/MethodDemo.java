package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class MethodDemo {

    @Test
    public void main() throws Exception {
        Method getName = A.class.getMethod("getName",null);
        A a = new A("younger");
        Object invoke = getName.invoke(a,null);
        if(invoke instanceof String){
            System.out.println(invoke);
        }
    }
}

class A{
    private String name;

    public A(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "name='" + name + '\'' +
                '}';
    }
}
