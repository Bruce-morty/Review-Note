package com.bruce.binarySearchTree;

import com.bruce.node.Node;
import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/8/11
 * Version:1.0.0
 */
/*
1367. 二叉树中的列表
给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，
否则返回 False 。
一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径
示例 1：
输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：true
解释：树中蓝色的节点构成了与链表对应的子路径。
示例 2：
输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：true
示例 3：

输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：false
解释：二叉树中不存在一一对应链表的路径。
 */
public class Medium1367_ListNodeInTree {
    public boolean isSubPath(Node head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        // caution 我们先调用dfs 然后再分别调用自身判断左右子树
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean dfs(Node node, TreeNode root) {
        // 查看是否有一条向下的路径与链表相同
        if (node == null) return true;
        // tree is null 树走完了 链表没走完
        if (root == null) return false;
        // 先判断值和当前树的值是否相同 isSubPath方法会一直递归直到找到符合root节点 之后node往下遍历 树也往下遍历
        if (node.value != root.value) return false;
        // 值相同 走左边和右边
        return dfs(node.next, root.left) || dfs(node.next, root.right);
    }
}
