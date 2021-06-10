package com.bruce.binarySearch;

/**
 * Author: bruce
 * Date:2020/10/24
 * Version:1.0.0
 */
public class BinarySearch2 {
    /*
查找最后一个与key相等的元素
 */
    public static int search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 最后一个应该在右边找
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 退出循环之后，low = high + 1， return high, 避免找不存在的最小数会越界，要判断
        if (high >= 0 && arr[high] == key) {
            return high;
        }
        return -1;
    }

    public static int search2(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                // 因为要用mid + 1索引，要判断这个索引能不能用。而且若mid是最大的话，也就是最后一个相等的元素
                if (mid == arr.length - 1 || arr[mid + 1] > key) return mid;
                low = mid + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(arr, 3));
    }
}
