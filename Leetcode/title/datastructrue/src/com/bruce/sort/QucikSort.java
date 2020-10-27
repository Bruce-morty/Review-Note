package com.bruce.sort;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class QucikSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        // 写个递归方法
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        // 边界条件，只有一个元素 相等
        if (left >= right) return;
        // 求出分区的索引
        int index = partition(arr, left, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int pivot = arr[left];
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            // j > j || arr[j] < pivot 放在arr[i] 位置上
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            arr[j] = arr[i];
        }
        // i == j
        arr[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 9, 0, 8, 7, 6, 4, 3};
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后："  + Arrays.toString(arr));
    }
}
