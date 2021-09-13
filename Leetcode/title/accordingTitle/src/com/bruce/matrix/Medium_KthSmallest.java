package com.bruce.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Qi Gao
 * Date:2021/7/10
 * Version:1.0.0
 */
/*
heap 操作
378. 有序矩阵中第 K 小的元素
给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素
示例 1：
输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
示例 2：

输入：matrix = [[-5]], k = 1
输出：-5
 */
public class Medium_KthSmallest {
    public int kthSmallest(int[][] matrix, int k) {
        // brute force
        int a = matrix.length;
        int b = matrix[0].length;
        // int[] res = new int[a * b];
        // int index = 0;
        // for (int i = 0; i < a; i++) {
        //     for (int num : matrix[i]) {
        //         res[index++] = num;
        //     }
        // }
        // Arrays.sort(res);
        // return res[k - 1];
        // 用小顶堆去解决 首先构建一个数组，三个值分别meaning
        // 把每一行数组的最小放入第一个queue 每一行的第一个肯定是最小的，每次弹出的时候
        // 再往queue中添加一个ele 是当前被弹出元素的右边的元素 queue会形成小顶堆 最后弹出k
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                // 比较两个数组的第一个位置谁小
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < a; i++) {
            // 添加三个值 0.这一行数组的最小 1.这一行位置 2.列的位置信息
            queue.offer(new int[]{matrix[i][0], i, 0});
        }

        // 遍历queue 不能取到k
        for (int i = 0; i < k - 1; i++) {
            int[] temp = queue.poll();
            if (temp[2] != a - 1) {
                // 添加右边位置的元素
                queue.offer(new int[]{matrix[temp[1]][temp[2] + 1], temp[1], temp[2] + 1});
            }
        }
        return queue.poll()[0];
    }


    public int kthSmallest2(int[][] matrix, int k) {
        // brute force convert to the one dimensional arr
        int len = matrix.length;
        int[] temp = matrix[0];
        for (int i = 1; i < len; i++) {
            temp = merge(temp, matrix[i]);
        }
        // find the smallest kth
        // int index = temp.length - k;
        return temp[k - 1];
    }

    public int[] merge(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] temp = new int[len1 + len2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < len1 && j < len2) {
            if (arr1[i] <= arr2[j]) {
                temp[k++] = arr1[i++];
            }else {
                temp[k++] = arr2[j++];
            }
        }
        while (i < len1) {
            temp[k++] = arr1[i++];
        }
        while (j < len2) {
            temp[k++] = arr2[j++];
        }
        return temp;
    }
}
