package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/17
 * Version:1.0.0
 */
/*
257. 二叉树的所有路径
给定一个二叉树，返回所有从根节点到叶子节点的路径
说明: 叶子节点是指没有子节点的节点
示例:
输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]
解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class Easy257_TotalPath {

    /*
    会用到回溯，因为要添加所有路径得值 说明每个分支都要遍历到
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        // 写一个辅助方法回溯
        dfs(root, res, "");
        return res;
    }

    private void dfs(TreeNode root, List<String> res, String s) {
        // 判断如果为null直接返回
        if (root == null) return;
        // 回溯思想 创建新的路径添加节点
        StringBuilder sb = new StringBuilder();
        sb.append(s).append(Integer.valueOf(root.value));
        // 判断左右子树是否存在
        if (root.left == null && root.right == null) {
            // 根节点了 添加到结果中
            res.add(sb.toString());
        }else {
            sb.append("->");
            if (root.left != null) {
                // 将路径得复制传过去 不会操作原来的sb，相当于remove 刚刚插入的元素
                dfs(root.left, res, sb.toString());
            }
            if (root.right != null) {
                dfs (root.right, res, sb.toString());
            }
        }
    }
}
