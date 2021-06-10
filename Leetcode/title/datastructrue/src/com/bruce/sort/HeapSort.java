package com.bruce.sort;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class HeapSort {
    /*
      1. 首先建大顶堆
      2. 把最后一个元素减去，继续堆化
      3. 重复这个操作直到数组长度为1
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        buildHeap(arr);
        int len = arr.length;
        while (len > 1) {
            swap(arr, 0, len - 1);
            // 移除最大的元素之后 长度减1
            len--;
            // 再次堆化
            heapify(arr, 0, len);
        }
    }

    private static void buildHeap(int[] arr) {
        // 找到第一个非叶子节点
        for (int i = (arr.length - 2) / 2; i >=0 ; i--) {
            heapify(arr, i, arr.length);
        }
    }

    private static void heapify(int[] arr, int i, int length) {
        while (i < length) {
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;
            int maxIndex = i;
            // 把leftChild < length放在后面的话，arr[leftChild] 会报NullPointer.
            if (leftChild < length && arr[maxIndex] < arr[leftChild]) {
                maxIndex = leftChild;
            }
            if (rightChild < length && arr[maxIndex] < arr[rightChild]) {
                maxIndex = rightChild;
            }
            if (maxIndex == i) break;
            // max现在是左孩子或者右孩子
            swap(arr, i, maxIndex);
            // swap 之后，i是最大，maxIndex是孩子节点，改变索引 让i成为父节点，进入下次循环
            i = maxIndex;
        }
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 9, 0, 8, 7, 6, 4, 3};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
