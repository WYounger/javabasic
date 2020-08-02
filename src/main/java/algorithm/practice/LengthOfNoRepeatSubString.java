package algorithm.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: 最长的无重复子串
 * @Author younger
 * @Date: 2020/7/26 22:03
 * @Version 1.0
 */
@Slf4j
public class LengthOfNoRepeatSubString {

    static int getLengthOfNoRepeatSubString(String str) {
        //记录无重复子串的起点
        int i = 0;
        //记录无重复子串的终点
        int j = 0;
        //记录最大长度
        int max = 0;
        //使用Map<c,index>判断师傅哦重复
        Map<Character, Integer> map = new HashMap<>(16);

        //遍历字符串
        for (; j < str.length(); j++) {
            Character c = str.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c) + 1);
            }
            map.put(c, j);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    @Test
    public void test() {
        String str = "abcbce";
        int length = getLengthOfNoRepeatSubString(str);
        log.info("length: {}", length);
    }
}
