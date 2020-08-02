package algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @title: QuickSort
 * @Author younger
 * @Date: 2020/7/19 23:21
 * @Version 1.0
 */
@Slf4j
public class QuickSort {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 5, 4};
        sort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }


    static void sort(int[] arr, int low, int high) {
        //一个元素自然有序
        if (low < high) {
            //左侧 < mid,mid < 右侧
            int pivot = partition(arr, low, high);
            //排序左侧
            sort(arr, low, pivot - 1);
            //排序右侧
            sort(arr, pivot + 1, high);
        }
    }

    /**
     * 将一个序列，按照第一个元素作为基准，左边元素小于(大于)它，右侧元素大于(小于)它
     *
     * @param arr  排序序列
     * @param low  序列的最小下标
     * @param high 序列的最大下标
     * @return
     */
    static int partition(int[] arr, int low, int high) {
        //记录基准元素
        int t = arr[low];

        //low和high没有相遇
        while (low < high) {

            //右侧元素大于基准元素，继续向左移动
            while (low < high && arr[high] >= t) {
                high--;
            }
            //右侧元素小于基准元素，移动到左侧
            if (low < high) {
                arr[low++] = arr[high];
            }

            //左侧元素小于基准元素，继续向右移动
            while (low < high && arr[low] < t) {
                low++;
            }
            //左侧元素大于基准元素，移动到右侧
            if (low < high) {
                arr[high--] = arr[low];
            }
        }

        arr[low] = t;

        return low;
    }

    /**
     * 3 1 2 4
     * * 1 2 4
     * * 1 2 4
     * 2 1 * 4
     *
     */
}
