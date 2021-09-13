package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/7/14
 * Version:1.0.0
 */
/*
783. 二叉搜索树节点最小距离
给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
示例 1：
输入：root = [4,2,6,1,3]
输出：1
 */
public class Easy783_MinDistance {
    int prev;
    int ans;
    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        prev = -1;
        dfs(root);
        return ans;
    }

    /*
    用中序遍历 从小到大排序，随时记录当前节点
    */
    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev == -1) {
            prev = node.value;
        } else{
            // 用prev保存当前node的值
            ans = Math.min(ans, node.value - prev);
            prev = node.value;
        }
        dfs(node.right);
    }

    // 记录先前的节点：即如果pre == null 的话说明这是最底层的节点 还没往下走 将pre记录 成当前的node
    TreeNode pre;// 记录上一个遍历的结点
    int result = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if(root==null)return 0;
        traversal(root);
        return result;
    }
    public void traversal(TreeNode root){
        if(root==null)return;
        //左
        traversal(root.left);
        //中
        if(pre!=null){
            result = Math.min(result,root.value-pre.value);
        }
        pre = root;
        //右
        traversal(root.right);
    }
}
