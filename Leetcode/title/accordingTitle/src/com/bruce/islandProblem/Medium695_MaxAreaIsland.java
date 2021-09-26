package com.bruce.islandProblem;

/**
 * Author: Qi Gao
 * Date:2021/9/15
 * Version:1.0.0
 */
/*
695. 岛屿的最大面积
给定一个包含了一些 0 和 1 的非空二维数组 grid 。
一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 */
public class Medium695_MaxAreaIsland {

    public int maxAreaOfIsland(int[][] grid) {
        // find the max
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(dfs(grid, i, j), max);
            }
        }
        return max;
    }

    int dfs(int[][]grid, int row, int col) {
        if (!inArea(grid, row, col)) return 0;
        if (grid[row][col] != 1) return 0; // 遍历过 或者是海洋
        grid[row][col] = 2;
        return 1 + dfs(grid, row + 1, col) + dfs(grid, row - 1, col) + dfs(grid, row, col - 1) + dfs(grid, row, col + 1);
    }

    boolean inArea(int[][] grid, int row, int col) {
        return 0 <= row && row < grid.length && 0 <= col && col < grid[0].length;
    }
}
