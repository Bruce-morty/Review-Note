package com.bruce.backtrack;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/8/4
 * Version:1.0.0
 */
/*
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径
叶子节点 是指没有子节点的节点
示例 1：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
示例 2：
输入：root = [1,2,3], targetSum = 5
输出：[]
示例 3：
输入：root = [1,2], targetSum = 0
输出：[]
 */
public class Medium113_Path2 {
    // 这有回溯的感觉 因为我们需要遍历到每个叶子节点 的路径
    // create path and result
    static List<List<Integer>> res = new ArrayList<>();
     public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
         // edge
         if (root == null) return res;
         ArrayList<Integer> path = new ArrayList<>();
         dfs_backtrack(root, targetSum, path);
         return res;
     }

     /*
     好好思考一下这个back track 的过程，和之前的不同 它不是迭代去删除path的元素
      */
    private static void dfs_backtrack(TreeNode root, int targetSum, ArrayList<Integer> path) {
         if (root == null) return;
        // caution 和之前不同的是 我们一开始就添加每条路径
        path.add(root.value);
        // 判断左右节点是否都为null
        if (root.left == null && root.right == null) {
            // 其他回溯是添加了之后就直接return 因为size已经满了
            // 但这里不需要操作，到最后一行需要把当前path中的元素删掉
            if (targetSum - root.value == 0) {
                res.add(new ArrayList<>(path));
            }
        }
        if (root.left != null) {
            dfs_backtrack(root.left, targetSum - root.value, path);
        }
        if (root.right != null) {
            dfs_backtrack(root.right, targetSum - root.value, path);
        }
        // 回溯回来把之前添加在path中的value删去
        path.remove(path.size() - 1);
    }
}
