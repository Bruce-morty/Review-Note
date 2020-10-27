package com.bruce.set_homework;

/**
 * Author: bruce
 * Date:2020/10/23
 * Version:1.0.0
 */
/*
如何在循环有序数组中，如何用二分查找 "等于给定值的元素"。
比如：int[] arr = {4, 5, 6, 1, 2, 3}, key = 2;
返回：4
 */
public class Homework3 {

    public static int search(int[] arr, int key) {
        // 循环数组,判断查找到的元素在左边有序还是右边有序
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (key == arr[mid]) return mid;
            // 左边有序
            if (arr[mid] >= arr[low]) {
                // key在左边
                if (arr[low] <= key && key < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (key <= arr[high] && key > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 1, 2, 3};
        System.out.println(search(arr, 2));
        System.out.println(search(arr, 5));
        System.out.println(search(arr, 0));
    }
}
