package generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestG {

    class A{}
    class B extends A{}
    class C extends A{}


    @Test
    public void test(){
        List<? extends A> list = new ArrayList<B>();
        
    }
}