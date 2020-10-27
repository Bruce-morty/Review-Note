package com.bruce.sort;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class InsertionSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // 定义i为无序区的第一个元素，j为有序区最后一个元素
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > val) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j + 1] = val;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 9, 0, 8, 7, 6, 4, 3};
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后："  + Arrays.toString(arr));
    }
}
