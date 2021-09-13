package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/7/17
 * Version:1.0.0
 */
/*
110. 平衡二叉树
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class Easy110_IsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            // 不只有当前根节点 要递归地判断当前左子树和右子树
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    public boolean isBalanced2(TreeNode root) {
        return height2(root) >= 0;
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
