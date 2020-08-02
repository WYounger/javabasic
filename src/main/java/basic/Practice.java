package basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Practice {

    @Test
    public void testArraysSort(){
        List<Student>  list = new ArrayList<>();
        list.add(new Student("wangy"));
        System.out.println(list.remove(new Student("wangy")));
        list.forEach(System.out::println);
    }
}


@AllArgsConstructor
@NoArgsConstructor
@Data
class Student{
    private String str;

    @Override
    public boolean equals(Object other){
        if(this == other){
            return  true;
        }
        if(other instanceof Student){
            Student o = (Student)other;
            if(str != null){
                if(str.equalsIgnoreCase(o.getStr())){
                    return true;
                }
                return false;
            }else{
                return Objects.isNull(o.getStr());
            }
        }
        return false;
    }
}