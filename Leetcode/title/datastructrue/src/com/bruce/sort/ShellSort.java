package com.bruce.sort;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class ShellSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        // 计算gap
        int gap = arr.length / 2;
        while (gap != 0) {
            for (int i = gap; i < arr.length; i++) {
                int val = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > val) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = val;
            }
            // 减少增量
            gap /= 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 9, 0, 8, 7, 6, 4, 3};
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后："  + Arrays.toString(arr));
    }
}
