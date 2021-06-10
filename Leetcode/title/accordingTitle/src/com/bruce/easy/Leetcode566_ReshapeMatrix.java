package com.bruce.easy;

/**
 * Author: bruce
 * Date:2021/2/17
 * Version:1.0.0
 */
/*
566. 重塑矩阵
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
示例 2:

输入:
nums =
[[1,2],
 [3,4]]
r = 2, c = 4
输出:
[[1,2],
 [3,4]]
解释:
没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 */
public class Leetcode566_ReshapeMatrix {

    // 将二维转成一维的方法确定一维的位置 然后重新写入到新数组中
    // i = index / n j = index % n n是列  (i,j) 是这个元素在矩阵的位置
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null) return null;
        // 找到边界 行 和 列
        int row = nums.length;
        int column = nums[0].length;
        if (r * c != row * column) return nums;
        int[][] newNum = new int[r][c];
        // 例如 [[2,3],[1,4]] -> [2,3,1,4] 2*2->1*4 映射为一维的方法是：
        //[i,j] 这个位置的元素 -> i * n + j 等于一维数组的索引位置  一维数组的长度是 m * n m是行 n为列
        // [1,0] 在一维数组中位置 取决于有几列元素 如果有三列 则[1,0]元素 在一维数组中索引是arr[3]
        // arr[0],arr[1],arr[2] 是矩阵(0,0) (0,1) (0,2)的值 0 * n + 0 = 0 ->arr[0]. 0*n+1 = 1->arr[1]
        // 将二维数组映射为一维
        for (int i = 0; i < column * row; i++) {
            newNum[i / c][i % c] = nums[i / column][i % column];
        }
        return newNum;
    }
}
