package com.bruce.binarySearch;

/**
 * Author: bruce
 * Date:2020/10/26
 * Version:1.0.0
 */
/*
查找最后一个小于等于key值的元素
 */
public class BinarySearch3 {
    public static int search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 最后一个小于等于，也就是说要在右边找
            if (key < arr[mid]) {
                high = mid - 1;
            } else {
                // 小于等于, 因为要用到mid + 1，索引需要判断一下，若mid == 最后一个index 可以判断就是最后一个
                if (mid == arr.length - 1 ||arr[mid + 1] > key) return mid;
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(arr, 3));
        System.out.println(search(arr, 11));
        System.out.println(search(arr, -1));
    }
}
