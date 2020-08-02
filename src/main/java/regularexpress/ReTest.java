package regularexpress;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReTest {

    @Test
    public void test(){
        String str = "cat mycat cat catcat";
        String reg = "\\bcat\\b";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()){
            System.out.println(matcher.start() + "---" + matcher.end());
        }
    }
}
