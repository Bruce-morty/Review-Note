package com.bruce.binarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/29
 * Version:1.0.0
 */
/*
1104. 二叉树寻路
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的
示例 1：
输入：label = 14
输出：[1,3,4,14]
示例 2：
输入：label = 26
输出：[1,2,6,10,26]
 */
public class Medium1104_FIndRoute {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        if (label < 1) return res;
        // find depth
        int depth = 0;
        int num = label;
        while (num != 0) {
            num /= 2;
            depth++;
        }
        // 找到第几层深度之后
        // 重点 推出父节点的公式，一般来说父节点等于 f = lable /= 2
        // 上一层的开始元素是 a = 1 << (depth - 1) 结尾: b = (1 << depth) - 1
        // 但排序改变之后 设x为父节点 x - a = b - f
        for (int i = depth - 1; i > 0; i--) {
            res.add(0, label);
            depth--;
            label /= 2;
            // 当前父节点 x = a + b - f
            label = (1 <<(depth - 1)) + ((1 << depth) - 1) - label;
        }
        res.add(0, 1);
        return res;
    }
}
