package com.bruce.binarySearch;

/**
 * Author: bruce
 * Date:2020/10/23
 * Version:1.0.0
 */
/*
查找第一个与key相等的元素
 */
public class BinarySearch1 {
    public static int searchComplex(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < key) {
                low = mid + 1;
            } else {
                // 都去左边找
                high = mid - 1;
            }
        }
        // 退出loop的时候，high 等于 low == mid 减去1
        if (low < arr.length && arr[low] == key) return low;
        return -1;
    }
    public static int search(int[] arr, int key) {
        // 第一个，也就是有重复的元素
        // 用循环去写，如果如果找到小于等于的，就到左边去找
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                // 如果mid == 0就知道是第一个了,否则mid - 1会空指针
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
