package com.bruce.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/24
 * Version:1.0.0
 */
/*
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
 */
public class Medium54_SprialMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 创建list
        List<Integer> res = new ArrayList<>();
        int m  = matrix.length;
        int n = matrix[0].length;
        // 写一个辅助方法一圈圈往里面遍历
        count(matrix, 0, 0, n - 1, m - 1, res);
        return res;
    }

    public void count(int[][] matrix, int left, int top, int right, int bottom, List res) {
        // 递归方法 边界判断若大于直接返回
        if (left > right || top > bottom) return;
        // 判断是否只有一行
        if (top == bottom) {
            for (int i = left; i <= right; i++) res.add(matrix[top][i]);
            return;
        }
        // 若只有一列
        if (left == right) {
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][left]);
            }
            return;
        }
        // 计算一圈
        // 从左往右 判断条件不加等于 留给下一行去添加
        // 从上往下
        // 从右往左
        // 从下往上
        for (int i = left; i < right; i++) res.add(matrix[top][i]);
        // 这里取right 是最后一列 上一轮循环留最后一个元素 matrix[top][right] 给这里添加
        for (int i = top; i < bottom; i++) res.add(matrix[i][right]);
        for (int i = right; i > left; i--) res.add(matrix[bottom][i]);
        for (int i = bottom; i > top; i--) res.add(matrix[i][left]);


        count(matrix, left+1, top+1, right-1, bottom-1, res);
    }
}
