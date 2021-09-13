package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/7/14
 * Version:1.0.0
 */
/*
101. 对称二叉树
给定一个二叉树，检查它是否是镜像对称的。
例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3

 */
public class Easy101_isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        // 判断左子树和 另一节点的右子树是否一样
        // 传两个指针 都指向root 随后 p 右移时，q左移，p 左移时，q 右移。
        // 每次检查当前 p 和 q 节点的值是否相等，如果相等再判断左右子树是否对称。
        return check(root, root);
    }

    private boolean check(TreeNode q, TreeNode p) {
        // 若左子树和右子树都为null return true
        if (q == null && p == null) {
            return true;
        }
        // 前面判断过了 若其中一个为null说明不等
        if (q == null || p == null) {
            return false;
        }
        return p.value == q.value && check(q.left, p.right) && check(q.right, p.left);
    }

}
