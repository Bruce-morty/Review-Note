package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/8/25
 * Version:1.0.0
 */
/*
236. 二叉树的最近公共祖先
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）"
示例 1：

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 */
public class Medium236_LowestAncestor {
    /*
  这不是BST，左节点会比根节点大，右节点会比根节点小。写一个递推算法,有几种情况：
  p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
  p = root, 且 q 在 root 的左或右子树中；
  q = root, 且 p 在 root 的左或右子树中；
  递归左节点和右节点，记为left 和 right
  1. 若left == null && right == null -> root的左右都不包含p, q 返回null
  2. 若left != null && right != null -> 返回root 因为当前root就是最大祖先
  3. left == null, right != null -> 直接返回right, 因为p, q肯定都在右子树中
  4. right == null, left != null -> 返回left，与上同理
   */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 最近公共祖先怎么找？用dfs 定位到p和q
        if (root == null || root == q || root == p) return root;
        // 往下递归
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 判断情况
        if (left == null && right == null) return null; // 1;
        if (left == null) return right; // 3
        if (right == null) return left; // 4
        // 2
        return root;
    }
}
