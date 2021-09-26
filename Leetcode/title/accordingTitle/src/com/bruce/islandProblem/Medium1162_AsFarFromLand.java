package com.bruce.islandProblem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Author: Qi Gao
 * Date:2021/9/22
 * Version:1.0.0
 */
/*
162. 地图分析
你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。
其中 0 代表海洋，1 代表陆地，请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是
|x0 - x1| + |y0 - y1| 。
如果网格上只有陆地或者海洋，请返回 -1
示例 1：
输入：[[1,0,1],[0,0,0],[1,0,1]]
输出：2
解释：
海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 */
public class Medium1162_AsFarFromLand {

    /*
    用多源BFS 去解决，将所有为陆地的格子加入队列。然后往四周扩散 找到海洋格子 记录距离，把遍历过的格子加入队列，继续扩散
    Time Complexity: O(n^2) -> 遍历这个数组 需要把所有1的元素放入queue
    Space Complexity: O(n)
     */
    public int maxDistance(int[][] grid) {
        int len = grid.length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 把所有陆地格子放入队列
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        // 没有陆地格子 或者没有海洋格子
        if (queue.isEmpty() || queue.size() == len * len) return -1;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int dis = -1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            // 刚出队列 dis = 0 代表从当前陆地格子出发距离是0
            dis++;
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                int r = node[0];
                int c = node[1];
                // 遍历moves
                for (int[] m : moves) {
                    int r1 = r + m[0];
                    int c1 = c + m[1];
                    // 若当前是海洋格子说明这个格子是有效的 添加进队列 继续判断它的周边是不是海洋格子
                    // 直到找到边界或陆地 即这是最远距离
                    if (inArea(grid, r1, c1) && grid[r1][c1] == 0) {
                        // 已经遍历过
                        grid[r1][c1] = 2;
                        // 把陆地格子再添加进去
                        queue.offer(new int[]{r1,c1});
                    }
                }
            }
        }
        return dis;
    }

    public boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }

    /*
    不需要多余的空间
     */
    public int maxDistance2(int[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        // 先把所有的陆地都入队。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean hasOcean = false;
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0], y = point[1];
            // 取出队列的元素，将其四周的海洋入队。
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                // 当前遇到海洋格子 grid[x][y]是陆地格子, 距离加一 目前等于2, 遇到下一个海洋 = 3...
                grid[newX][newY] = grid[x][y] + 1; // 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                hasOcean = true;
                queue.offer(new int[] {newX, newY});
            }
        }

        // 没有陆地或者没有海洋，返回-1。
        if (point == null || !hasOcean) {
            return -1;
        }

        // 返回最后一次遍历到的海洋的距离。由于之前在陆地格子上加1 现在要减去1
        return grid[point[0]][point[1]] - 1;
    }
}
