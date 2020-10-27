package com.bruce.binarySearch;

/**
 * Author: bruce
 * Date:2020/10/26
 * Version:1.0.0
 */
    /*
    查找第一个大于等于key值的元素
     */
public class BInarySearch4 {
    public static int search (int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            // 第一个大于等于，若相等继续在左边找，判断 arr[mid + 1] = key return mid
            int mid = low + ((high - low) >> 1);
            if (key > arr[mid]) {
                low = mid + 1;
            } else {
                // 判断当前元素 + 1是否相等 key
                if (mid == 0 || arr[mid - 1] < key) return mid;
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(arr, 3));
    }
}
