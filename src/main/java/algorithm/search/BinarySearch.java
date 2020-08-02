package algorithm.search;

import org.junit.jupiter.api.Test;

/**
 * @title: BinarySearch
 * @Author younger
 * @Date: 2020/6/21 18:15
 * @Version 1.0
 */
public class BinarySearch {

    /**
     *  二分查找的适用的前提条件
     *  1. 数据序列有序，单调递增或单调递减
     *  2. 数据使用数组存储，便于确定通过索引快速定位到中间的元素
     *
     */

    /**
     * 二分查找算法描述
     *
     * @param arr    目标数组
     * @param target 带查找目标
     * @return
     */
    private static int binarySearch(int[] arr, int target) {
        //数组左右两侧下标
        int left = 0;
        int right = arr.length - 1;

        //left<=right
        while (left <= right) {
            //分割点下标
            int mid = (left + right) / 2;
            //目标值小于中间值
            if (target < arr[mid]) {
                //向左侧查找
                right = mid - 1;
            }
            //目标值大于中间值
            else if (target > arr[mid]) {
                //向右侧查找
                left = mid + 1;
            }
            //目标值等于中间值
            else {
                return mid;
            }
        }
        //没找到返回-1
        return -1;
    }

    @Test
    public void testBinarySearch() {
        int[] arr = {1,2,3,4,5};
        int index = binarySearch(arr,6);
        System.out.println(index);
    }
}
