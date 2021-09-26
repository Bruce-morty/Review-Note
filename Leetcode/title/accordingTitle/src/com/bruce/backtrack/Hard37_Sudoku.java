package com.bruce.backtrack;

/**
 * Author: Qi Gao
 * Date:2021/9/22
 * Version:1.0.0
 */
/*
37. 解数独
编写一个程序，通过填充空格来解决数独问题。

数独的解法需 遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
数独部分空格内已填入了数字，空白格用 '.' 表示。
示例：
 */
public class Hard37_Sudoku {
    /*
    用回溯算法，并且回溯要有返回值，找到一个符合条件的就返回 且所有的数字都放入了数独中了
    Time Complexity: O(9 ^ m) 指数级别
     */
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    public boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return backtrack(board, i + 1, 0);
        }
        if (i == m) {
            // 找到一个可行解，触发 base case
            return true;
        }

        if (board[i][j] != '.') {
            // 如果有预设数字，不用我们穷举
            return backtrack(board, i, j + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            // 如果遇到不合法的数字，就跳过
            if (!isValid(board, i, j, ch))
                continue;

            board[i][j] = ch;
            // 如果找到一个可行解，立即结束
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        // 穷举完 1~9，依然没有找到可行解，此路不通
        return false;
    }
    public boolean isValid(char[][] board, int r, int c, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == ch) return false;
            if(board[i][c] == ch) return false;
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == ch) return false;
        }
        return true;
    }
}
