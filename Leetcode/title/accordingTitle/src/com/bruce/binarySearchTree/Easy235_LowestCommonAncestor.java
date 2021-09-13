package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/7/19
 * Version:1.0.0
 */
/*
235. 二叉搜索树的最近公共祖先
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6
解释: 节点 2 和节点 8 的最近公共祖先是 6。
 */
public class Easy235_LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 写一个方法找到最近的公共祖先
        if (root == null) return null;
        TreeNode ans;
        // 设p是较大的那个值
        if (q.value < p.value) {
            ans = getLCA(root, p.value, q.value);
        }else {
            // q是较大的值
            ans = getLCA(root, q.value, p.value);
        }
        return ans;
    }

    public TreeNode getLCA(TreeNode node, int cmp1, int cmp2) {
        if (node == null) return null;
        // 比较node和cmp1 cmp2的值 然后进行判断要往哪边走
        if (node.value <= cmp1 && node.value >= cmp2) {
            return node;
        } else if (node.value < cmp2) {
            // 比最小的还要小 往右边走
            return getLCA(node.right, cmp1, cmp2);
        }else if (node.value > cmp1) {
            return getLCA(node.left, cmp1, cmp2);
        }
        return null;
    }
}
