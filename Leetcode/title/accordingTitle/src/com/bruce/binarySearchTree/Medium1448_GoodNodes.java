package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/8/17
 * Version:1.0.0
 */
/*
1448. 统计二叉树中好节点的数目
给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值
示例 1：

输入：root = [3,1,4,3,null,1,5]
输出：4
解释：图中蓝色节点为好节点。
根节点 (3) 永远是个好节点。
节点 4 -> (3,4) 是路径中的最大值。
节点 5 -> (3,4,5) 是路径中的最大值。
节点 3 -> (3,1,3) 是路径中的最大值。
 */
public class Medium1448_GoodNodes {

    int sum = 0;
    public int goodNodes(TreeNode root) {
        // 遍历每个节点 记录最大值 比较最大值和节点值 若greater than max, sum++
        int max = root.value;
        dfs(root, max);
        return sum;
    }

    /*
    重点：写dfs的时候首先确定 返回值是什么，参数需要传什么。
    再想logical，里面的code用什么traversal 该在 left recursion前判断还是后判断
     */
    public void dfs(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        // 判断max
        if (node.value >= max) {
            sum++;
            max = node.value;
        }
        // 进入下一层
        dfs(node.left, max);
        dfs(node.right, max);
    }
}
