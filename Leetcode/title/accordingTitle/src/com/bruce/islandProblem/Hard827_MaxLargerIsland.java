package com.bruce.islandProblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Qi Gao
 * Date:2021/9/15
 * Version:1.0.0
 */
/*
827. 最大人工岛
给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
返回执行此操作后，grid 中最大的岛屿面积是多少？
岛屿 由一组上、下、左、右四个方向相连的 1 形成。

示例 1:

输入: grid = [[1, 0], [0, 1]]
输出: 3
解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
示例 2:

输入: grid = [[1, 1], [1, 0]]
输出: 4
解释: 将一格0变成1，岛屿的面积扩大为 4。
示例 3:
 */
public class Hard827_MaxLargerIsland {

    /*
    算法思想：最重要的是需要遍历grid的时候要记录岛屿的面积和记录它的编号.
    我们可以遍历两次grid, 第一次记录岛屿的面积，并对他们分别编号. 用map记录，value - 岛屿的面积
     */
    public int largestIsland(int[][] grid) {
        if (grid==null || grid.length == 0){
            return 1;
        }

        int res = 0;
        int index = 2;//index表示岛屿的编号，0是海洋1是陆地，从2开始遍历
        HashMap<Integer,Integer> indexAndAreas = new HashMap<>();//岛屿编号：岛屿面积

        /**
         * 计算每个岛屿的面积，并标记是第几个岛屿
         */
        for (int r=0;r<grid.length;r++){
            for (int c=0;c<grid[0].length;c++){
                if (grid[r][c] == 1){//遍历没有访问过的岛屿格子
                    int area = area(grid,r,c,index);//返回每个岛屿的面积，dfs
                    indexAndAreas.put(index,area);//存入岛屿编号、岛屿面积
                    index++;//岛屿编号增加
                    res = Math.max(res,area);//记录最大的岛屿面积
                }
            }
        }

        if (res == 0) return 1;//res=0表示没有陆地，那么造一块，则返回1即可

        /**
         * 遍历海洋格子，假设这个格子填充，那么就把上下左右是陆地的格子所在的岛屿连接起来
         */
        for (int r=0;r<grid.length;r++){
            for (int c=0;c<grid[0].length;c++){
                if (grid[r][c] == 0){ //遍历海洋格子
                    HashSet<Integer> hashSet = findNeighbour(grid,r,c);//把上下左右邻居放入set去重
                    if (hashSet.size() < 1)continue;//如果海洋格子周围没有格子不必计算
                    int twoIsland = 1;//填充这个格子，初始为1，这个变量记录合并岛屿后的面积
                    for (Integer i: hashSet){
                        twoIsland += indexAndAreas.get(i);//该格子填充，则上下左右的陆地的都连接了，通过序号获得面积，加上面积
                    }
                    res = Math.max(res,twoIsland);//比较得到最大的面积
                }
            }
        }
        return res;
    }


    /**
     * 对于海洋格子，找到上下左右
     * 每个方向，都要确保有效inArea以及是陆地格子，则表示是该海洋格子的陆地邻居
     * @param grid
     * @param r
     * @param c
     * @return
     */
    private HashSet<Integer> findNeighbour(int[][] grid,int r,int c){
        HashSet<Integer> hashSet = new HashSet<>();
        if (inArea(grid,r-1,c)&&grid[r-1][c] != 0){
            hashSet.add(grid[r-1][c]);
        }
        if (inArea(grid,r+1,c) && grid[r+1][c] != 0){
            hashSet.add(grid[r+1][c]);
        }
        if (inArea(grid,r,c-1) && grid[r][c-1] != 0){
            hashSet.add(grid[r][c-1]);
        }
        if (inArea(grid,r,c+1) && grid[r][c+1] != 0){
            hashSet.add(grid[r][c+1]);
        }
        return hashSet;
    }

    /**
     * dfs方法，将格子填充为index，即表示这个格子属于哪个岛的
     * 计算岛屿面积，上下左右，当然这个可以优化的，因为不需要计算上面的，会有重复
     * @param grid
     * @param r
     * @param c
     * @param index
     * @return
     */
    private int area(int[][] grid, int r, int c,int index){
        if (!inArea(grid,r,c)){
            return 0;
        }
        //不为1，表示为海洋格子或者已经遍历过了
        if (grid[r][c] != 1){
            return 0;
        }
        grid[r][c] = index;//设置当前格子为某个岛屿编号
        return 1 + area(grid,r-1,c,index) + area(grid,r+1,c,index) + area(grid,r,c-1,index) + area(grid,r,c+1,index);
    }

    /**
     * 判断grid[r][c]是否大小合适
     * @param grid
     * @param r
     * @param c
     * @return
     */
    private boolean inArea(int[][] grid,int r,int c){
        return r>=0 && r<grid.length&&c>=0 && c<grid[0].length;
    }
}
