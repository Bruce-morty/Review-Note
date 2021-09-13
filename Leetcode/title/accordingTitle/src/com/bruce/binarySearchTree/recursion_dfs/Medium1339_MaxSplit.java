package com.bruce.binarySearchTree.recursion_dfs;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/8/19
 * Version:1.0.0
 */
/*
给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
输入：root = [1,2,3,4,5,6]
输出：110
解释：删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
 */
public class Medium1339_MaxSplit {

    /*分为两个步骤：
            1.求整个二叉树的和
        2.遍历所有子树和，求最大值
    */

    double ans = Double.MIN_VALUE;
    double allSum;
    double nodeSum;
    public int maxProduct(TreeNode root) {
        allSum = sum(root);
        dfs(root);
        return (int)(ans % (int)(1e9 + 7));
    }

    public double sum(TreeNode node){
        if(node == null) return 0;
        return node.value + sum(node.left) + sum(node.right);
    }

    /*
    这个dfs要有返回值 返回的是每个子树的和给上一层 然后记录ans
     */
    public double dfs(TreeNode node){
        if(node == null)    return 0 ;
        nodeSum = node.value + dfs(node.left) + dfs(node.right);
        ans = Math.max(ans, (allSum - nodeSum) * nodeSum);
        return nodeSum;
    }
}
