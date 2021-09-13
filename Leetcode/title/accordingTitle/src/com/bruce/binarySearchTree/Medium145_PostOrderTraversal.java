package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

import java.util.*;

/**
 * Author: Qi Gao
 * Date:2021/7/6
 * Version:1.0.0
 */
/*
 实现 postOrder
 */
public class Medium145_PostOrderTraversal {

    /*
    用栈实现，即迭代的方式实现后序遍历
    0. 定义一个prev节点 还有栈。若root不为空 就放入栈中 往左边移动
    1. root == null 出栈，赋值给root 判断root有没有右子树或者等于prev节点
    2. 添加节点到list中或者继续遍历右子树
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // 创建返回list
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        // 怎么遍历? L -> R -> D 进入循环之前 push进入一个root 同时定义一个prev节点 判断是否和上一个节点相同
        // prev 的作用：标记添加过的节点 若是左子树则不需要操作 是右子树则说明当前root的右边也遍历完了 可以添加节点了
        TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            // 遍历到底 一直遍历左节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 判断是否有右子树
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                // 很重要 理解！若左右子树都是null 这时候可以添加了  递归到最深一层的左节点
                res.add(root.value);
                // 更新prev指针 用来判断root的右边是不是已经添加进去了
                prev = root;
                root = null;
            } else {
                // 重新把root入栈，进入到右子树中
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    // 用一个栈实现，还可以用两个栈实现
    /*
    前序遍历顺序为：根 -> 左 -> 右
    后序遍历顺序为：左 -> 右 -> 根
    如果1： 我们将前序遍历中节点插入结果链表尾部的逻辑，修改为将节点插入结果链表的头部 那么结果链表就变为了：右 -> 左 -> 根
    如果2： 我们将遍历的顺序由从左到右修改为从右到左，配合如果1 那么结果链表就变为了：左 -> 右 -> 根  这刚好是后序遍历的顺序
     */
    public static List<Integer> postOrder(TreeNode root) {
        // 构建一个stack
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 将根节点出栈 放入结果数组的最后
            TreeNode node = stack.pop();
            res.addFirst(node.value);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return res;
    }
}
