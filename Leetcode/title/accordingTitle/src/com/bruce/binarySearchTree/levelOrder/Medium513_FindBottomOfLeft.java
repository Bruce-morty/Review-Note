package com.bruce.binarySearchTree.levelOrder;

import com.bruce.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Qi Gao
 * Date:2021/8/26
 * Version:1.0.0
 */
/*
513. Find Bottom Left Tree Value
Add to List Share  Given the root of a binary tree,
return the leftmost value in the last row of the tree.
Example 1:
Input: root = [2,1,3]
Output: 1
Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
 */
public class Medium513_FindBottomOfLeft {

    // 用bfs去做 遍历到最底那一层 记录第一个节点即为 最底端的最左节点
    public int findBottomLeftValue(TreeNode root) {
        // use bfs
/*         if (root == null) return -1;
         Queue<TreeNode> queue = new LinkedList<>();
         int res = 0;
         queue.offer(root);
         while(!queue.isEmpty()) {
             int size = queue.size();

             for (int i = 0; i < size; i++) {
                 TreeNode node = queue.poll();
                 if (i == 0) res = node.val;
                 if (node.left != null) queue.offer(node.left);
                 if (node.right != null) queue.offer(node.right);
             }
         }
         return res;*/
        dfs(root, 0);
        return maxVal;
    }

    int maxLen = Integer.MIN_VALUE;
    int maxVal = 0;
    /*
    dfs 比较快，bfs要遍历每一层 花费更多时间
     */
    public void dfs(TreeNode node, int leftLen) {
        if (node == null) return;
        // go to the bottom of tree. 保证这是叶子节点，比较是否大于最大深度 大于的话再记录当前值
        if (node.left == null && node.right == null) {
            // check the leftLen and maxLen
            if (leftLen > maxLen) {
                maxLen = leftLen;
                maxVal = node.value;
            }
        }
        // iterate left
        dfs(node.left, leftLen + 1);
        dfs(node.right, leftLen + 1);
    }
}
