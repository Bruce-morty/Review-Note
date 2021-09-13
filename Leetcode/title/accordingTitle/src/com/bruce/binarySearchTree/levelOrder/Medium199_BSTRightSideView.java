package com.bruce.binarySearchTree.levelOrder;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Qi Gao
 * Date:2021/8/26
 * Version:1.0.0
 */
/*
199. 二叉树的右视图
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
示例 1:
输入: [1,2,3,null,5,null,4]
输出: [1,3,4]
示例 2:

输入: [1,null,3]
输出: [1,3]
 */
public class Medium199_BSTRightSideView {

    /*
    BFS: 每次level遍历 添加最后一个node 可知是右视图第一个节点
     */
     public List<Integer> rightSideView(TreeNode root) {
         // 用层级遍历
         Queue<TreeNode> queue = new LinkedList<>();
         queue.offer(root);
         List<Integer> list = new ArrayList<>();
         if (root == null) return list;
         while (!queue.isEmpty()) {
             // 得到queue的长度
             int size = queue.size();
             for (int i = 0; i < size; i++) {
                 TreeNode node = queue.poll();
                 // check 是当前层级的最后一个节点
                 if (i == size - 1) list.add(node.value);
                 if (node.left != null) queue.offer(node.left);
                 if (node.right != null) queue.offer(node.right);
             }
         }
         return list;
     }

     /*
     用dfs来解决，遍历顺序：root -> right -> left
     同时用dept保证当前的层级是与 list应该添加的 size() - 1位置。list每次都只应该添加该层的最右节点
      */
     public List<Integer> rightSideView2(TreeNode root) {
         List res = new ArrayList();
         dfs(res, root, 0);
         return res;
     }


    private void dfs(List<Integer> res, TreeNode node, int level) {
         // 为null就return
        if(node != null) {
            // 如果当前节点所在深度还没有出现在res里->在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中
            if(res.size() == level) {
                res.add(node.value);
            }
            // 先往右走
            dfs(res, node.right, level + 1);
            dfs(res, node.left, level + 1);
        }
    }
}
