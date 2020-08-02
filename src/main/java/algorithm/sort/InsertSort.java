package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * @title: InsertSort
 * @Author younger
 * @Date: 2020/7/19 12:46
 * @Version 1.0
 */
public class InsertSort {
    @Test
    public void test() {

        int[] arr = {5,4,3,2,1};
        Arrays.stream(sort(arr)).forEach(System.out::println);
    }


    static int[] sort(int[] arr) {

        Objects.requireNonNull(arr);

        int len = arr.length;


        for (int i = 1; i < len; i++) {

            int t = arr[i];

            int j = i - 1;

            for (; j >= 0 && t < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }

            if(j < 0 || t > arr[j]){
                arr[j+1] = t;
            }
        }

        return arr;
    }

    /**
     * 主要思想：
     * 1. 从第二个元素开始，向前插入，保证最前面的序列有序
     * 2. 每轮拿出一个元素，向前插入
     * 3. 注意临界区间
     */

}
