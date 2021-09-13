package com.bruce.binarySearchTree.recursion_dfs;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/8/5
 * Version:1.0.0
 */
/*
100. 相同的树
给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 */
public class Easy100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.value != q.value) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        if (p.value != q.value) return false;
        return isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
    }
}
