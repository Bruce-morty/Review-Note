package com.bruce.binarySearchTree.levelOrder;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/14
 * Version:1.0.0
 */
/*
103. 二叉树的锯齿形层序遍历
给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
 */
public class Medium102_ZigzagOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 用层级遍历 然后在每一层倒叙添加
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 定义一个变量判断在奇数和偶数添加顺序不同
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList list = new LinkedList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 偶数是正添加 奇数是倒叙添加
                if ((count % 2) == 0) {
                    list.add(node.value);
                }else{
                    list.addFirst(node.value);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(list);
            count++;
        }
        return res;
    }
}
