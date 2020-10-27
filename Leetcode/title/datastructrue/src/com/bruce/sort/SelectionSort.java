package com.bruce.sort;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class SelectionSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        // 遍历到倒数第二个元素，排好序了
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 循环一次找到最小
            int temp = arr[i];
            // 把最小放到i进行下一次循环
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 9, 0, 8, 7, 6, 4, 3};
        // 改进后只需要排序6次
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后："  + Arrays.toString(arr));
    }
}
