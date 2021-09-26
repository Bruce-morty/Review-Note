package com.bruce.binarySearch;

/**
 * Author: bruce
 * Date:2020/10/23
 * Version:1.0.0
 */
public class BinarySearch {
    public static int search3(int[] arr, int target, int low, int high) {
       // 递归
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (arr[mid] > target) return search3(arr, target, low, mid - 1);
        if (arr[mid] < target) return search3(arr, target,  mid + 1, high);
        return mid;
    }
    /**
     * 二分查找
     *
     * @param arr    给定的数组
     * @param target 要查找的元素
     * @return 该元素所在索引, 如果数组中不存在该索引, 返回-1
     */
    public static int search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target == arr[mid]) return mid;
            if (arr[mid] < target) {
                // target在右边
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
        // return search(arr, 0, arr.length - 1, target);
    }

    public static int search(int[] arr, int low, int high, int target) {
        // 递归地方法
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (target < arr[mid]) {
            return search(arr, low, mid - 1, target);
        } else if (target > arr[mid]) {
            return search(arr, mid + 1, high, target);
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(arr, 7));
        System.out.println(search(arr, 10));
        System.out.println(search(arr, 3));
    }
}
