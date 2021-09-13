package com.bruce.binarySearchTree.recursion_dfs;

import com.bruce.node.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Qi Gao
 * Date:2021/8/23
 * Version:1.0.0
 */
/*
653. 两数之和 IV - 输入 BST
给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
示例 1：
输入: root = [5,3,6,2,4,null,7], k = 9
输出: true
 */
public class Eassy653_TwoSumBST {

    /*
    重点：每次都要判断当前节点的左右子树，所以用set存节点。需要有返回值吗，要
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode root, int k, Set<Integer> set) {
        // 找到最后也没找到
        if (root == null) return false;
        if (set.contains(k - root.value)) return true;
        // 不存在 把当前节点存入set 判断子树中是否存在 相加 = k
        set.add(root.value);
        // 左右子树有一个符合就行
        return dfs(root.left, k, set) || dfs(root.right, k, set);
    }
}
