package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
 */
public class HardOffer37_LCOF {

    /*
    层级遍历：实现序列化和反序列化，序列化的时候添加逗号，deserialization 的时候要分割逗号
     */
    public String serialize(TreeNode root) {
        if (root == null) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> d = new LinkedList<>();
        d.add(root);
        while (!d.isEmpty()) {
            TreeNode node = d.poll();
            if (node != null) {
                sb.append(node.value + ",");
                d.add(node.left);
                d.add(node.right);
            } else {
                sb.append("null,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
        // d.addLast(root);
        // while (!d.isEmpty()) {
        //     TreeNode poll = d.pollFirst();
        //     sb.append(poll.val + "_");
        //     if (!poll.equals(emptyNode)) {
        //         d.addLast(poll.left != null ? poll.left : emptyNode);
        //         d.addLast(poll.right != null ? poll.right : emptyNode);
        //     }
        // }
        // return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("[]")) return null;

        String[] s = data.substring(1, data.length() - 1).split(",");
        int n = s.length;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!s[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.left);
            }
            i++;
            if (!s[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
