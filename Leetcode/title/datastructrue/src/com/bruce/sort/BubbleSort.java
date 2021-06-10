package com.bruce.sort;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int len = arr.length - 1;
        // 把大的元素往后冒泡
        for (int i = 0; i < len; i++) {
            boolean swap = false;
            // 用判断条件控制循环次数
            for (int j = 0; j < len - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) break;;
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
