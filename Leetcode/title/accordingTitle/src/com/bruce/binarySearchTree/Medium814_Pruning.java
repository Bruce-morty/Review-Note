package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: bruce
 * Date:2021/3/2
 * Version:1.0.0
 */
/*
给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。返回移除了所有不包含 1 的子树的原二叉树。
( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
示例1:
输入: [1,null,0,0,1]
输出: [1,null,0,null,1]
解释:
只有红色节点满足条件“所有不包含 1 的子树”。
右图为返回的答案。
 */
public class Medium814_Pruning {
    /*
    修剪二叉树 将所有0的改成1
     */
    public static TreeNode pruneTree(TreeNode root) {
        // 修剪二叉树的枝干 把所有只包含0的子树删掉
        // 写一个递归函数 判断根是不是1 然后再判断左右子树
        // return containsNode(root) ? root : null;
        // 另一种写法
        if (root == null) return null;
        root.left = pruneTree(root.left);   // 左子树剪枝，得到剪枝后左子树
        root.right = pruneTree(root.right); // 右子树剪枝，得到剪枝后右子树
        // 判断决定root结点是否需要剪掉：
        if (root.left == null && root.right == null && root.value == 0) return null;
        // 返回root这棵树剪枝后的结果
        return root;
    }

    /*
    递归地判断根节点和左右子树是否包含1
     */
    private static boolean containsNode(TreeNode root) {
        if (root == null) return false;
        // 先把左边子树进行判断 再判断当前根节点
        boolean nLeft = containsNode(root.left);
        boolean nRight = containsNode(root.right);
        // 遍历到叶子节点 左右子树都为null 判断根节点
        if (!nLeft) root.left = null;
        if (!nRight) root.right = null;
        // 判断根节点 caution！！：很重要 如果当前节点是0 但是子树中有1 应该返回true
        return root.value == 1 || nLeft || nRight;
    }
}
