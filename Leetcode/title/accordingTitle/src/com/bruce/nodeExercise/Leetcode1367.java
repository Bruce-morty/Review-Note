package com.bruce.nodeExercise;

import com.bruce.node.Node;
import com.bruce.node.TreeNode;

/**
 * Author: bruce
 * Date:2020/11/18
 * Version:1.0.0
 */
/*
给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。

如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，
那么请你返回 True ，否则返回 False 。
一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。

输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：true

输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：false



 */
public class Leetcode1367 {

    public boolean isSubPath(Node head, TreeNode node) {
        if (head == null) return true;
        if (node == null) return false;

        // 判断当前节点, 如果不满足就继续往下
        return isSub(head, node) || isSubPath(head, node.left) || isSubPath(head, node.right);
    }

    public boolean isSub(Node head, TreeNode root) {
        // 如果链表到底了 返回true
        if (head == null) return true;
        // 链表没走完，树走完了，不满足
        if (root == null) {
            return false;
        }
        if (head.value != root.value) return false;
        // 值相等了继续往下判断，直到链表结束. 左边的树满足或者右边的树满足一个即可
        return isSub(head.next, root.left) || isSub(head.next, root.right);
    }
}
