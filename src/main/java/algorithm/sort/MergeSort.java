package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @title: MergeSort
 * @Author younger
 * @Date: 2020/7/19 22:36
 * @Version 1.0
 */
public class MergeSort {

    @Test
    public void test() {

        int[] arr = {5, 4, 1, 2, 3};
        sort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }


    static void sort(int[] arr, int low, int high) {
        //一个元素自然有序
        if (low < high) {
            int mid = low + (high - low) / 2;
            //左半边排序
            sort(arr, low, mid);
            //右半边排序
            sort(arr, mid + 1, high);
            //左右半边结果合并
            merge(arr, low, mid, high);
        }
    }

    static void merge(int[] arr, int low, int mid, int high) {
        //开辟临时存放元素的数组
        int[] tempArr = new int[high - low + 1];
        int i = 0;

        //左、右数组的起始下标
        int left = low;
        int right = mid + 1;

        //左、右数组按大小顺序存入临时数组
        for (; left <= mid && right <= high; ) {
            if (arr[left] <= arr[right]) {
                tempArr[i++] = arr[left++];
            } else {
                tempArr[i++] = arr[right++];
            }
        }

        //左边数组尚未完全存入临时数组，右边数组已经完全存入临时数组
        if (left <= mid) {
            for (; left <= mid; ) {
                tempArr[i++] = arr[left++];
            }
        } else {
            for (; right <= high; ) {
                tempArr[i++] = arr[right++];
            }
        }

        //将临时数组复制到目标数组
        for (int j = low; j <= high; j++) {
            arr[j] = tempArr[j - low];
        }

    }

}
