package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/4/13
 * Version:1.0.0
 */
/*
给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值

输入：root = [4,2,6,1,3]
输出：1
输入：root = [1,0,48,null,null,12,49]
输出：1
 */
public class Easy783_FindMinDistance {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
        int prev;
        int ans;
        /*
        中序遍历 可以知道前后的元素差值肯定最小, 固定一个prev指针和ans变量保存最小元素值
         */
        public int minDiffInBST(TreeNode root) {
            // 0. 定义当前节点 找到与左右的差值判断哪个小 保存下来 1.往下遍历 怎么判断往左边还是右边
            // 2. 左和右都要遍历
            ans = Integer.MAX_VALUE;
            prev = -1;
            // List<Integer> list = new ArrayList<>();
            // inOrder(root, list);
            // for (int i = 0; i < list.size() - 1; i++) {
            //     int curr = list.get(i + 1) - list.get(i);
            //     if (curr < ans) {
            //         ans = curr;
            //     }
            // }
            // return ans;
            dfs(root);
            return ans;
        }

        public void inOrder(TreeNode node, List list) {
            if (node == null) return;
            inOrder(node.left, list);
            list.add(node.value);
            inOrder(node.right, list);
        }

        /*
        用中序遍历
        */
        public void dfs(TreeNode node) {
            if (node == null) return;
            dfs(node.left);
            if (prev == -1) {
                prev = node.value;
            } else{
                // 计算完前后结果之后 用prev保存当前node的值
                ans = Math.min(ans, node.value - prev);
                prev = node.value;
            }
            dfs(node.right);
        }
}
