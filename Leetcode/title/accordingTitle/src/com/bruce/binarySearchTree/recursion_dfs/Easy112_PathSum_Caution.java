package com.bruce.binarySearchTree.recursion_dfs;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/9/3
 * Version:1.0.0
 */
/*
112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
这条路径上所有节点值相加等于目标和 targetSum, 叶子节点 ->是指没有子节点的节点
示例 1：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
输出：true
示例 2：
输入：root = [1,2,3], targetSum = 5
输出：false
示例 3：
输入：root = [1,2], targetSum = 0
输出：false
提示：
树中节点的数目在范围 [0, 5000] 内
-1000 <= Node.value <= 1000
-1000 <= targetSum <= 1000
 */
public class Easy112_PathSum_Caution {

    /*
    这道题的重点: 0.递归什么时候要有返回值-->"找到一条符合结果的路径就返回"
                1. 递归什么时候可以直接返回 什么时候需要接收返回值
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // back track 判断是否有符合的一条路径
        return dfs(root, targetSum);
    }

    public boolean dfs(TreeNode node, int target) {
        if (node == null) return false;
        if (node.left == null && node.right == null) {
            if (target - node.value == 0) {
                return true;
            }
        }
        if (node.left != null) {
            // 重点1在这，如果没有left去判断 直接返回dfs会有问题。假如左子树不符合还不能直接返回false 要进入right
            boolean left = dfs(node.left, target - node.value);
            if (left) {
                return true;
            }
            // 否则回溯回来就不会减去这个node.value target没变
        }
        // 这里也再次判断right是不是有一个符合的，有一个就return true 。最后判断完left和right才返回false
        // 与下面的方法一样 用 || 符号，只要左右子树有一个符合就行了 否则都是false才返回false
        if (node.right != null) {
            boolean right = dfs(node.right, target - node.value);
            if (right) {
                return true;
            }
        }
        return false;
    }

    /*
    Time Complexity: O(N) N是树的节点数
    Space Complexity: O(H) H是树的高度.
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.value == sum;
        }
        return hasPathSum2(root.left, sum - root.value) || hasPathSum2(root.right, sum - root.value);

    }

}
