package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/4/5
 * Version:1.0.0
 */
/*
Given the root of a binary tree, return the postorder traversal of its nodes' values.

 */
public class Medium145_PostOrderTravesal {
    /*
    用迭代的方式：栈
    0. 定义一个prev节点 还有栈。若root不为空 就放入栈中 往左边移动
    1. root == null 出栈，赋值给root 判断root有没有右子树或者等于prev节点
    2. 添加节点到list中或者继续遍历右子树
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        // prev 的作用：标记添加过的节点 若是左子树则不需要操作 是右子树则说明当前root的右边也遍历完了 可以添加节点了
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // root == null 出栈 判断有没有右子树
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                list.add(root.value);
                prev = root;
                root = null;
            } else {
                // 把当前节点重新放回栈中 移动到右子树
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }
}
