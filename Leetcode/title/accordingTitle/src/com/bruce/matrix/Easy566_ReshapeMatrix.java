package com.bruce.matrix;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
示例 1:
输入:
nums =
[[1,2],
 [3,4]]
r = 1, c = 4
输出:
[[1,2,3,4]]
解释:
行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
 */
public class Easy566_ReshapeMatrix {

    /*
    知道一个概念: 如何把一个二维矩阵转化成一维 m[i][j] = m[n * i + j]  n = column
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null) return null;
        // 找到边界
        int column = nums[0].length;
        int row = nums.length;
        if (r * c < row * column || r * c > row * column) return nums;
        int[][] newNum = new int[r][c];
        // 将二维数组映射为一维
        for (int i = 0; i < column * row; i++) {
            newNum[i / c][i % c] = nums[i / column][i % column];
        }
        return newNum;
    }

    public int[][] matrixReshape2(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;
        // if 不等 返回原matrix
        if (m * n != r * c)
            return mat;
        int[][] res = new int[r][c];
        // 定义两个变量去遍历res矩阵
        int row = 0, col = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[row][col] = mat[i][j];
                col++;
                // 进入到res matrix的下一个row中 column重置为0
                if (col == c) {
                    col = 0;
                    row++;
                }
            }
        }
        return res;
    }
}
