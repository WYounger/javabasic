package foreach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForEach {

    public static void main(String[] args) {
        String[] arr = {"1","2","3"};
        for (String str : arr){
            System.out.println(str);
        }
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class A {
    private String name;
}
