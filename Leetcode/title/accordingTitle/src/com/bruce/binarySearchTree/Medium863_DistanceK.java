package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/7/28
 * Version:1.0.0
 */
/*
863. 二叉树中所有距离为 K 的结点
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K
返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回
示例 1：

输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
输出：[7,4,1]
解释：
所求结点为与目标结点（值为 5）距离为 2 的结点，
值分别为 7，4，以及 1
 */
public class Medium863_DistanceK {
    // 有可能父节点也符合 所以要判断 用map保存父节点
    Map<Integer, TreeNode> map = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 写一个方法保存父节点
        findPar(root);
        // dfs 去找到距离为k的节点, 从target开始 from指父节点
        findRoute(target, null, 0, k);
        return res;
    }

    private void findRoute(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) return;
        if (depth == k) {
            res.add(node.value);
            return;
        }
        if (node.left != from) {
            // 往左边找 同时depth + 1
            findRoute(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findRoute(node.right, node, depth + 1, k);
        }
        // caution 往父节点找
        if (map.get(node.value) != from) {
            // 这里的from 是当前node target是map保存的node 的parent
            findRoute(map.get(node.value), node, depth + 1, k);
        }
    }

    private void findPar(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            map.put(root.left.value, root);
            findPar(root.left);
        }
        if (root.right != null) {
            map.put(root.right.value, root);
            findPar(root.right);
        }
    }
}
