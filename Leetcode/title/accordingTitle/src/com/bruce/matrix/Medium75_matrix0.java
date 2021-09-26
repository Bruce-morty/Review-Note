package com.bruce.matrix;

import java.util.Arrays;

/**
 * Author: Neo
 * Date:2021/3/21
 * Version:1.0.0
 */
/*
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
示例 1:
输入:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
进阶:
一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？
 */
public class Medium75_matrix0 {
    public void setZeroes(int[][] matrix) {
        // 暴力 创建一个新数组 当遇到0 先标记 置为1 遍历完后将对应的数组中有0的位置 行列都置为0
        int m = matrix.length;
        // 列 column
        int n = matrix[0].length;
        int[][] newArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) newArr[i][j] = 1;
            }
        }
        // 标记结束 将对应行和列改为0 m 行 n 列. 固定m 遍历 n matrix[m][i] 是在m行修改每一列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (newArr[i][j] == 1) {
                    Arrays.fill(matrix[i], 0);
                    // 遍历每一行，将每一行对应的列置为0
                    for (int x = 0; x < m; x++) {
                        matrix[x][j] = 0;
                    }
                }
            }
        }
        // 只用一个标记变量，然后倒叙地去处理数组 空间复杂度是O(1)
        // int m = matrix.length, n = matrix[0].length;
        // boolean flagCol0 = false;
        // for (int i = 0; i < m; i++) {
        // 标记了第一列的0个数之后 将判断每一列的0的位置 若有0将第一行和第一列列置为的当前位置变成0
        //     if (matrix[i][0] == 0) {
        //         flagCol0 = true;
        //     }
        //     for (int j = 1; j < n; j++) {
        //         if (matrix[i][j] == 0) {
        //             matrix[i][0] = matrix[0][j] = 0;
        //         }
        //     }
        // }
        /*
        然后通过第一行和第一列中各个位置的0 去修改其他行和列 为了避免先修改第一个位置导致整个第一列和第一行变成0
        应该从后往前修改 逆序修改数组的值
         */
        // for (int i = m - 1; i >= 0; i--) {
        //     for (int j = 1; j < n; j++) {
        //         if (matrix[i][0] == 0 || matrix[0][j] == 0) {
        //             matrix[i][j] = 0;
        //         }
        //     }
        //     if (flagCol0) {
        //         matrix[i][0] = 0;
        //     }
        // }
    }
}

