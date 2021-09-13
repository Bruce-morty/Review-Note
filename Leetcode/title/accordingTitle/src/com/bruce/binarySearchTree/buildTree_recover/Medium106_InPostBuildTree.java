package com.bruce.binarySearchTree.buildTree_recover;

import com.bruce.node.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/7/14
 * Version:1.0.0
 */
/*
根据中序和后序去建树
 */
public class Medium106_InPostBuildTree {
    Map<Integer, Integer> map1;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 初始化map
        map1 = new HashMap<>();
        int len = inorder.length;
        // 两个数组的len肯定是一样的
        for (int i = 0; i < len; i++) {
            // 保存中序中每个节点的位置
            map1.put(inorder[i], i);
        }
        // 写一个辅助方法实现建树
        TreeNode root = helpBuildTree(inorder, postorder, 0, len - 1, 0, len - 1);
        return root;
    }

    private TreeNode helpBuildTree(int[] inorder, int[] postorder, int inorderStart, int inoderEnd, int postorderStart, int postorderEnd) {
        if (postorderStart > postorderEnd){
            // 没有节点 返回null
            return null;
        }
        // 得到根节点的值
        int rootVal = postorder[postorderEnd];
        int rootIndexOfInOrder = map1.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 查看中序中左子树的数量
        int leftSizeOfInorder = rootIndexOfInOrder - inorderStart;
        // 构建左子树
        // 左子树中的inorder：[inorder start, rootIndex - 1]不包括root  postorder: [post start, 左子树结尾位置]
        root.left = helpBuildTree(inorder, postorder, inorderStart, rootIndexOfInOrder - 1,
                postorderStart, postorderStart + leftSizeOfInorder -1);
        // 构建右子树
        // 右子树中的inorder：[根节点值 + 1, 结尾]  postorder: [左子树结尾位置 + 1， 结尾 - 1]
        root.right = helpBuildTree(inorder, postorder, rootIndexOfInOrder + 1, inoderEnd,
                postorderStart + leftSizeOfInorder, postorderEnd - 1);
        return root;
    }
}
