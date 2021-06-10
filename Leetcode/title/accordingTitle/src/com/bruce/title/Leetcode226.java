package com.bruce.title;

import com.bruce.node.TreeNode;

/**
 * Author: bruce
 * Date:2020/12/8
 * Version:1.0.0
 */
/*
翻转一棵二叉树。
     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

备注:
这个问题是受到 Max Howell 的 原问题 启发的 ：
谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 */
public class Leetcode226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // 利用树的递归性质，把每一个左右子树拿出来
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        // 递归地去连上 左子树等于原来的右 右子树等于原来的左
        root.left = left;
        root.right = right;
        return root;
    }
}
