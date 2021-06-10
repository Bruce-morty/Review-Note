package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/4/27
 * Version:1.0.0
 */
/*
给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
输出：32

输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
输出：23
 */
public class Easy938_BinarySearchRange {
    /*
    需要遍历整个树。也就是DFS和BFS
    先用DFS，edge case 不只是root == null 还要判断当前节点的范围
     */
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        // 比较当前节点值和区间左 若小于low 则去右子树找
        if (root.value < low) {
            return rangeSumBST(root.right, low, high);
        }
        // 若大于high 搜寻左子树
        if (root.value > high) {
            return rangeSumBST(root.left, low, high);
        }
        // 若在范围内 返回当前节点 + 左子树 + 右子树 的和
        return root.value + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    static int sum = 0;
    /*
    用中序遍历去计算sum sum不能定义在recursion里面 要不然每次都被初始化为0
     */
    public static int rangeInorder(TreeNode root, int low, int high) {
        if (root == null) return 0;
        // 中序遍历 判断左 根 右
        rangeInorder(root.left, low, high);
        if (root.value >= low && root.value <= high) {
            sum += root.value;
        }
        rangeInorder(root.right, low, high);
        // 如果没有这个return 会怎么样
        return sum;
    }
}
