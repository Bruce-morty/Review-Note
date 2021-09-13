package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/14
 * Version:1.0.0
 */
/*
51. N 皇后
n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。
 */
public class Hard51_NQueens {

    /*
    用框架去解决，首先定义一个res list
    然后构建 n * n棋盘 这里的选择列表是 row + 1 当前位置遍历过了 去下一个位置
     */
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveQueens(int n) {
        // 创建棋盘
        // 构建路径track
        List<StringBuilder> track = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // 每进入到新的列才创建新的sb
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < n; j++) {
                str.append(".");
            }
            track.add(str);
        }
        // 写一个回溯方法 从第一行开始
        backtrack(track, 0);
        return res;
    }

    // 路径：track 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 track 的最后一行
    private void backtrack(List<StringBuilder> track, int row) {
        // 边界 若track。size == row 遍历到尾了 添加进res集合
        if (track.size() == row) {
            List<String> path = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                // 添加到路径中
                path.add(track.get(i).toString());
            }
            res.add(path);
            return;
        }

        // 回溯
        int n = track.get(row).length();
        // 重点 这里是遍历列，当前行每一列有可能放 queen
        for (int col = 0; col < n; col++) {
            if (!isValid(track, row, col)) continue;
            // 合法 可以放在这个位置
            track.get(row).setCharAt(col, 'Q');
            // 进入下一行
            backtrack(track, row + 1);
            // 删除选择
            track.get(row).setCharAt(col, '.');
        }
    }

    private boolean isValid(List<StringBuilder> track, int row, int col) {
        // 判当前行和当前列能否放Q
        // 遍历当前每一行
        int size = track.size();
        for (int i = 0; i < size; i++) {
            if (track.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        // 遍历左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (track.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        // 遍历右上 当前元素的右边是 col + 1然后一直遍历到边界 col < size
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            if (track.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
