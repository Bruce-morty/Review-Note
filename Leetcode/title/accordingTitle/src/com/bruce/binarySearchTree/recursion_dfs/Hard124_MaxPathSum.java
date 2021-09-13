package com.bruce.binarySearchTree.recursion_dfs;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/7/17
 * Version:1.0.0
 */
/*
124. 二叉树中的最大路径和
路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次
该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和
示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 */
public class Hard124_MaxPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {

        // 思路：比较左子树路径和右子树路径哪个大 就加上当前节点返回给上一层
        if (root == null) return 0;
        dfs(root);
        return max;
    }

    /*
    为什么有返回值 什么时候该有 什么时候不需要有！
     */
    public int dfs(TreeNode node) {
        if (node == null) return 0;
        // 计算左子树得最大值, 若为负数则选择0
        int leftVal = Math.max(dfs(node.left), 0);
        int rightVal = Math.max(dfs(node.right), 0);

        // 更新max 比较历史的max 和当前的值哪个更大
        max = Math.max(max, node.value + leftVal + rightVal);
        // 返回经过node 的单边最大分支给当前node的父节点计算
        // 这里是重点需要理解，递归函数的返回值为什么不返回max 因为递归的定义是返回一个节点最大贡献，只计算一条路径
        // 从前面代码知道分别得到左右子树的最大贡献，返回一条路径的最大值给上一层 用全局变量记录，让它决定要不要加上当前节点
        return node.value + Math.max(leftVal, rightVal);
    }
}
