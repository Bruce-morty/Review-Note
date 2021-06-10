package com.bruce.set_homework;

/**
 * Author: bruce
 * Date:2020/10/22
 * Version:1.0.0
 */
/*
查找一个数组中第K大的元素，看能不能将时间复杂度降到O(n)
1. 排序, arr[len - k] nlog(n)

2. 快排（分区操作）
 */
public class Homework2 {

    public static int kthLargest(int[] arr, int k) {
        int index =arr.length - k;
        kthLargest(arr, 0, arr.length - 1, index);
        return arr[index];
    }

    // 在排序的过程中顺便比较这个index在哪，不一定排好序就能找到这个元素
    private static void kthLargest(int[] arr, int left, int right, int index) {
        // 分区
        int i = partition(arr, left, right);
        // i == index index已经排好序, 那就不执行
        if (i < index) kthLargest(arr, i + 1, right, index);
        if (i > index) kthLargest(arr, left, i - 1, index);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] > pivot) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] < pivot) {
                i++;
            }
            arr[j] = arr[i];
        }
        // i = j
        arr[i] = pivot;
        return i;
    }
}


