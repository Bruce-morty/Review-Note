package com.bruce.title;

import com.bruce.node.TreeNode;

/**
 * Author: bruce
 * Date:2020/11/24
 * Version:1.0.0
 */
/*
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 */
public class Leetcode222 {
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        // 递归地做法, + 1是为了计算 若左右节点都为空返回0，但是有一个节点值应该+1
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /*
    要充分利用完全二叉树的性质：它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
     */
    public static int count2(TreeNode root) {
        return -1;
    }
}
