package com.bruce.binarySearchTree.recursion_dfs;

/**
 * Author: bruce
 * Date:2021/2/23
 * Version:1.0.0
 */
/*
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
示例:
输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
public class Medium96 {
    /*
    动规：找到子问题的解 求解出大问题
    G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数。
    F(i,n): 以 i 为根、序列长度为 n 的不同二叉搜索树个数 (1≤i≤n)。
    不同二叉树的个数 是遍历所有 (1<=i<=n) F(i,n)之和 所以G(n) = 累加F(i,n)  g(0) = 1 g(1) = 1
    F (i,n) = i为中心 左边的二叉树个数 与右边二叉树个数的笛卡尔积
            = g(i - 1) * g (n - i)
    可知 G(n) = g(0) *g(n -1) + g(1)*g(n-2) + ...+ g(n - 1) * g(0)
     */
    public int numTrees(int n) {
        // 动态规划的方法：数组应该定义为n + 1 n位置存值
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;
        // g(n) = g(1 - 1)*g(n - 1) + g(2 - 1) * g(n - 2) +...+ g(n - 1) *g(0);
        for (int i = 2; i <= n; i++) {
            // g(i)表示这个位置的二叉树个数
            for (int j = 1; j <= i; j++) {
                // j是用来遍历到中心
                g[i] += g[j - 1] * g [i - j];

            }
        }
        return g[n];
    }
}
