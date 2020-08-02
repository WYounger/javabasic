package string;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

public class StringDemo {
    public static void main(String[] args) {
        File dir = new File("E:\\courese\\Linux\\Linux性能优化实战（23）");
        File[] files = dir.listFiles();
        Arrays.stream(files).forEach(file ->{
            String fileName = file.getName();
            if(fileName.endsWith("mp3") || fileName.endsWith("pdf")){
                file.delete();
            }
        });
    }

    @Test
    public void testString() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "younger");
        map.put("age", 25);
        String str = "name = ${name}; age = ${age};";
        while (str.contains("$")) {
            String atr = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
            str = str.replace(str.substring(str.indexOf("$"),str.indexOf("}") + 1), map.get(atr).toString());
        }
        System.out.println(str);
    }

    public void tset1(){

    }

    public void tset2(){

    }

    public void tset3(){

    }

}
