package com.bruce.binarySearchTree.recursion_dfs;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/8/31
 * Version:1.0.0
 */
/*
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案
示例 1：
输入：n = 3
输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
示例 2：

输入：n = 1
输出：[[1]]

 */
public class Medium95_UniqueBST2 {

    /*
    思路: 充分利用二叉树的性质，left < root < right. 可以用递归的方法去找到所有可能的子树
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        // 写一个递归方法
        return generateTrees(1, n);
    }


    private List<TreeNode> generateTrees(int start, int end) {
        // 首先创建结果list
        List<TreeNode> res = new LinkedList<>();
        // edge case
        if (start > end) {
            res.add(null);
            return res;
        }
        // 只有一个值 添加它作为根节点 要记得这时候就要返回res
        if (start == end) {
            TreeNode node = new TreeNode(start);
            res.add(node);
            return res;
        }
        // 这里有回溯的思想，递归就有回溯的思想. 可以 <= end. end也要判断
        for (int i = start; i <= end; i++) {
            // 找到所有的可能左右子树 其实就是dfs
            List<TreeNode> allLeft = generateTrees(start, i - 1);
            List<TreeNode> allRight = generateTrees(i + 1, end);
            // caution 遍历这些左右子树 与根节点组合
            for (TreeNode left : allLeft) {
                for (TreeNode right : allRight) {
                    // 构建root
                    // caution 为什么每次都要构建新的node 在for外面new不行吗？
                    // 不行！因为它求得是组合数 每有一个符合的值就构建新的节点连上 left right
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        // 返回res
        return res;
    }


    /*
    用dp的方法去实现这个树，递归都可以用迭代去实现
     */
    public List<TreeNode> generateTrees2(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        //长度为 1 到 n
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<TreeNode>();
            //将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len; root++) {
                int left = root - 1;  //左子树的长度
                int right = len - root; //右子树的长度
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = leftTree;
                        //克隆右子树并且加上偏差
                        // 例如现在root = 3 n = 5, left = dp[2]right = dp[2], 加上当前root的值变成dp[4]dp[5]
                        // 可以找到右边 但是我们没有clone左子树 有很多共享的树
                        treeRoot.right = clone(rightTree, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }
        return dp[n];
    }

    /*
    重点：克隆这颗树
     */
    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.value + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    public List<TreeNode> generateTrees3(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees3(int start, int end) {
        List<TreeNode> list = new LinkedList<>();
        // 递归边界 start > end
        if (start > end) {
            list.add(null);
            return list;
        }
        // 回溯 判断左子树都小于根节点 且根节点 小于右子树
        for (int i = start; i <= end; i++) {
            // 找到所有可能的左子树
            List<TreeNode> left = generateTrees(start, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);
            // 从左右子树中都选一棵树加入到结果中 回溯
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = l;
                    curr.right = r;
                    list.add(curr);
                }
            }
        }
        return list;
    }
}
