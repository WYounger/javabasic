package collection;

import org.junit.jupiter.api.Test;

import java.util.*;

public class SetTest {
    @Test
    public void 并集(){

        Set<Integer> set = new HashSet<>();
    }

    @Test
    public void 交集(){
        Integer[] a = {1,2,3,4};
        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        list.remove(1);
        list.forEach(System.out::println);
    }

    @Test
    public void 集合运算(){
        Integer[] a = {1,2,3,4};
        Integer[] b = {1,2,3,5};

        Set<Integer> set_a = new HashSet<>(Arrays.asList(a));
        Set<Integer> set_b = new HashSet<>(Arrays.asList(b));

        //并集
//        set_a.addAll(list_b); // 1 2 3 4 5

        //交集
//        set_a.retainAll(list_b); // 1 2 3


        //差集 ClassA - B = ClassA - AB
        set_a.removeAll(set_b); // 4

        set_a.forEach(System.out::println);
    }

}
