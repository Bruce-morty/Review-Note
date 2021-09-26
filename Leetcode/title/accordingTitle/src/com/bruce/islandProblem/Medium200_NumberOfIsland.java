package com.bruce.islandProblem;

/**
 * Author: Qi Gao
 * Date:2021/9/16
 * Version:1.0.0
 */
/*
200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。
示例 1：
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
 */
public class Medium200_NumberOfIsland {
    public int numIslands(char[][] grid) {
        // dfs 记录岛屿数量
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 等于1的时候就进来 调用dfs方法 里面会把相邻的陆地连在一起改成2，然后res++
                // 之后已经连成陆地的2 不会进入这个判断
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) return;
        if (grid[row][col] != '1') return;
        grid[row][col] = '2';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public boolean inArea(char[][] grid, int r, int c) {
        return r>=0 && r<grid.length&&c>=0 && c<grid[0].length;
    }
}
