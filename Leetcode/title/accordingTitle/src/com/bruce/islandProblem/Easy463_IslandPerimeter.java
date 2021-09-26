package com.bruce.islandProblem;

/**
 * Author: Qi Gao
 * Date:2021/9/16
 * Version:1.0.0
 */
/*
463. 岛屿的周长
给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（
或者说，一个或多个表示陆地的格子相连组成的岛屿）。
岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，
且宽度和高度均不超过 100 。计算这个岛屿的周长。
示例 1：
输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
输出：16
解释：它的周长是上面图片中的 16 个黄色的边
 */
public class Easy463_IslandPerimeter {

    /*
    这道题是dfs 遍历网格的精髓 我们观察模板，dfs会从当前格子的上下左右四个方向扩散。
    我们可以想一下如果碰到海洋，或者碰到边界就停下了，当前周长 + 1 返回 最终结果就计算出来了。
    假设陆地格子为1，从四个方向扩散碰到海洋分别返回1， 相加等于4.得到结果.
    caution dfs会遍历当前格子的每一条边！记住这个
     */
    public int islandPerimeter(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, r, c);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int r, int c) {
        // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
        if (!inArea(grid, r, c)) {
            return 1;
        }
        // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
        if (grid[r][c] == 0) {
            return 1;
        }
        // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);

    }

    private boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }


    // 方法二： 数学方法 不用递归调用stack
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    /*
    Time Complexity: O(4 * n^2) 要在loop里面 再遍历四个方向 multiple 4
    Space Complexity: O(1)
     */
    public int islandPerimeter2(int[][] grid) {
        // 用数学方法,
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    // 从当前格子四个方向判断是否到达边界
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) {
                            cnt++;
                        }
                    }
                    res += cnt;
                }
            }
        }
        return res;
    }

    /*
    若有相邻的格子 直接减去2，因为两个边相交了，同时只判断上面和下面的格子 重合的话已经把周长放在之前的 4里面
    Time Complexity: O(n^2)
    Space Complexity: O(1)
     */
    public int islandPerimeter3(int[][] grid) {
        // 用数学方法,
        if (grid == null || grid.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        res -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        res -= 2;
                    }
                }
            }
        }
        return res;
    }
}
