package com.bruce.binarysearch;

/**
 * Author: Qi Gao
 * Date:2021/3/30
 * Version:1.0.0
 */
/*
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。

输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
 */
public class Medium74_searchMartix {
    public static boolean search(int[][] matrix, int target) {
        // 暴力法 从每一行的最后一个元素开始找 若比target小直接break 从下一行开始
             int m = matrix.length;
             int n = matrix[0].length;
             for (int i = 0; i < m; i++) {
                 // 从每一行最后一个开始判断
                 for (int j = n - 1; j >= 0; j--) {
                     if (matrix[i][j] == target) return true;
                     if (matrix[i][j] < target) {
                         // 这一行的最大都小于就break
                         break;
                     }
                 }
             }
             return false;
    }

    public static boolean search2(int[][] matrix, int target) {
        // 用二分查找，将二维数组映射成一维就可以比较大小了
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
