package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * @title: BubbleSort
 * @Author younger
 * @Date: 2020/7/19 9:37
 * @Version 1.0
 */
public class BubbleSort {


    @Test
    public void test() {

        int[] arr = {5,4,3,2,1};
        Arrays.stream(sort(arr)).forEach(System.out::println);
    }


    static int[] sort(int[] arr) {

        Objects.requireNonNull(arr);

        int lenth = arr.length;

        for(int i = 1;i < lenth; i++){
            boolean isOrdered = true;
            for(int j = 0; j < lenth - i;j++){
                //递增排序
                if(arr[j+1] < arr[j]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    isOrdered = false;
                }
            }
            if(isOrdered){
                return arr;
            }
        }
        return arr;
    }

    /**
     * 主要思想：
     * 1.每轮遍历，前后两个元素两两交换，保证前小于(或大于)后，一轮结束后，最大(或最小)在数组尾部，
     *   尾部元素都是按照顺序排列好了
     * 2.总共进行n-1轮遍历
     * 3.优化: 当一轮遍历后，没有发生交换，说明序列已经有序，所以设置了后一个ordered的标识位
     *
     */
}
