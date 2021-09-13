package com.bruce.title;

/**
 * Author: Qi Gao
 * Date:2021/7/10
 * Version:1.0.0
 */
/*
根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live）
或 0 即为死细胞（dead）每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 */
public class Medium_289GameOfLife {

    /*
    在原数组上操作：时间复杂度O(mn) 空间复杂度O(1)
    重点：0. 构建一个neighbor数组 遍历8个元素 ，最小是nums[i-1, j-1] 最大 nums[i+1, j+1]
         1. 每次遍历当前节点时初始化live，查看当前ele附近的活细胞
         2. 参数判断，两个for loop 小于3 同时要加上外层循环的row 和 col
     */
    public void gameOfLife(int[][] board) {
        // define a rule 在当前数组上修改
        int n = board.length;
        int m = board[0].length;
        // 定义neighbor数组
        int[] neighbor = new int[]{-1, 0, 1};
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                // 遍历周围的细胞
                int live = 0;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (!(neighbor[r] == 0 && neighbor[c] == 0)) {
                            // 改变原数组的值
                            // 若是活细胞 判断周围的live元素
                            int l = row + neighbor[r];
                            int x = col + neighbor[c];
                            if ((l < n && l >= 0) && (x >= 0 && x < m) && Math.abs(board[l][x]) == 1) {
                                live++;
                            }
                        }
                    }
                }
                // 根据周围值修改当前元素
                if (board[row][col] == 1) {
                    // 活细胞变死了
                    if (live < 2 || live > 3) {
                        board[row][col] = -1;
                    }
                }
                if (board[row][col] == 0) {
                    if(live == 3) {
                        board[row][col] = 2;
                    }
                }
            }
        }
        // 修改完数组 重新遍历，将2修改成1，-1修改成0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0){
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
