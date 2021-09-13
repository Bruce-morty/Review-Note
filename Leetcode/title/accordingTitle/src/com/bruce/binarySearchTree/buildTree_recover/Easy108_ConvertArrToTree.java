package com.bruce.binarySearchTree.buildTree_recover;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/9/1
 * Version:1.0.0
 */
/*
108. 将有序数组转换为二叉搜索树
给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树
示例 1：
输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：

 */
public class Easy108_ConvertArrToTree {

    /*
    首先由于数组升序 可以知道这个树是中序遍历的。怎么确定树的高度只相差1呢
    我们可以选择中间数字作为二叉搜索树的根节点，这样分给左右子树的数字个数相同或只相差1可以使得树保持平衡。
    如果数组长度是奇数，则根节点的选择是唯一的，如果数组长度是偶数，则可以选择中间位置左边的数字作为根节点
    或者选择中间位置右边的数字作为根节点，选择不同的数字作为根节点则创建的平衡二叉搜索树也是不同的。
     */
    /*
    time complexity: O(n) n是数组的长度 每个数字只访问一次
    space complexity: o(log n) 递归栈的深度
     */
     public TreeNode sortedArrayToBST(int[] nums) {
         // 用中序遍历恢复一棵树
         TreeNode node = build(nums, 0, nums.length - 1);
         return node;
     }

     public TreeNode build(int[] nums, int start, int end) {
         if (start > end) {
             return null;
         }
         if (start == end) {
             return new TreeNode(nums[start]);
         }
         // 中序遍历
         int index = start + (end - start) / 2;
         TreeNode left = build(nums, start, index - 1);
         TreeNode root = new TreeNode(nums[index]);
         root.left = left;
         TreeNode right = build(nums, index + 1, end);
         root.right = right;
         return root;
     }

     /*
     方法2 也是中序遍历恢复一颗树 与上面的方法一样
      */
    public TreeNode sortedArrayToBST2(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        // 以升序数组的中间元素作为根节点 root。
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归的构建 root 的左子树与右子树。
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }
}
