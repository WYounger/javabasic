package algorithm.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @title: 在无序的序列中查找第k小的元素
 * @Author younger
 * @Date: 2020/7/26 22:56
 * @Version 1.0
 */
@Slf4j
public class NLatestNum {

    static int getNLatestNum(int[] arr, int k) {
        return doGetNLatestNum(arr, 0, arr.length - 1, k);
    }

    //arr[low]为基准值，判断它是第几小
    static int doGetNLatestNum(int[] arr, int low, int high, int k) {
        int pivot = getPivot(arr, low, high);
        if (pivot == k) {
            return arr[pivot];
        } else if (k < pivot) {
            return doGetNLatestNum(arr, low, pivot - 1, k);
        } else {
            return doGetNLatestNum(arr, pivot + 1, high, k);
        }
    }

    /**
     * 利用快速排序的思想，将数组分为分为两段，返回基准值的位置
     *
     * @param arr
     * @param low  下界
     * @param high 上界
     * @return
     */
    private static int getPivot(int[] arr, int low, int high) {
        int std = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= std) {
                high--;
            }
            if (low < high) {
                arr[low++] = arr[high];
            }
            while (low < high && arr[low] < std) {
                low++;
            }
            if (low < high) {
                arr[high--] = arr[low];
            }
        }
        arr[low] = std;
        return low;
    }

    @Test
    public void test() {
        int[] a = {4, 3, 2, 1, 6, 5};
        int k = 2;
        int result = getNLatestNum(a, k-1);
        log.info("latest {} number is {}", k, result);
    }
}
