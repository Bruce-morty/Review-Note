package com.bruce.title;

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
        // 用二分查找，先在第一列找到最后一个小于等于target的值
        int flag = searForColumn(matrix, target);
        if (flag == -1) return false;
        return searchForRow(matrix, target, flag);
    }

    private static boolean searchForRow(int[][] matrix, int target, int index) {
        int low = 0;
        int high = matrix.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (matrix[mid][index] == target) return true;
            else if (matrix[mid][index] > target) {
                high = mid - 1;
            }else if (matrix[mid][index] < target) {
                low = mid + 1;
            }
        }
        return false;
    }

    /*
    这个二分查找好好看
     */
    private static int searForColumn(int[][] matrix, int target) {
        int low = -1;
        int high = matrix.length - 1;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        // 最后low = high 返回最后一个小于等于target的行索引
        return low;
    }
}
