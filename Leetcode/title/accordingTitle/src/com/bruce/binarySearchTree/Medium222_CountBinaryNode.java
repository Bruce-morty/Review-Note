package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

/**
 * Author: bruce
 * Date:2020/11/24
 * Version:1.0.0
 */
/*
给出一个完全二叉树，求出该树的节点个数。
说明：
完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 */
public class Medium222_CountBinaryNode {
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        // 递归地做法, + 1是为了计算 若左右节点都为空返回0，但是有一个节点值应该+1
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /*
    要充分利用完全二叉树的性质：它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
    并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
    因此对于最大层数为 hh 的完全二叉树，节点个数一定在 [2^h,2^{h+1}-1]的范围内， 可以在该范围内通过二分查找的方式得到
    完全二叉树的节点个数。
    具体做法是，根据节点个数范围的上下界得到当前需要判断的节点个数 k，如果第 k 个节点存在，则节点个数一定大于或等于 k，
    如果第 k 个节点不存在，则节点个数一定小于 k，由此可以将查找的范围缩小一半，直到得到节点个数。

    如何判断第 k 个节点是否存在呢？如果第 k 个节点位于第 hh 层，则 k 的二进制表示包含 h+1 位，
    其中最高位是 1，其余各位从高到低表示从根节点到第 k 个节点的路径，0表示移动到左子节点，1 表示移动到右子节点通过位运算
    得到第k 个节点对应的路径，判断该路径对应的节点是否存在，即可判断第 k 个节点是否存在。
     */
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        // 位运算对应树的节点位置 若节点为null了 说明右边的节点不存在
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            // 逐位判断这个下个位置是往左还是往右 10000 & 111000 -> 结果是0说明往左走
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    /*
    现在这个树中的值都是节点的编号，最底下的一层的编号是[2^h ，2^h - 1]，现在h = 2，也就是4, 5, 6, 7。
    4, 5, 6, 7对应二进制分别为 100 101 110 111 不看最左边的1，从第二位开始，0表示向左，1表示向右，
    正好可以表示这个节点相对于根节点的位置。
    比如4的 00 就表示从根节点 向左 再向左。6的 10 就表示从根节点 向右 再向左

    那么想访问最后一层的节点就可以从节点的编号的二进制入手。从第二位开始的二进制位表示了最后一层的节点相对于根节点的位。
    那么就需要一个bits = 2^(h - 1) 上面例子中的bits就是2，对应二进制为010。这样就可以从第二位开始判断。
    （树三层高，需要向左或向右走两次才能到叶子）
    比如看5这个节点存不存在，先通过位运算找到编号为5的节点相对于根节点的位置。010 & 101 发现第二位是0，
    说明从根节点开始，第一步向左走。
    之后将bit右移一位，变成001。001 & 101 发现第三位是1，那么第二步向右走。
    最后bit为0，说明已经找到编号为5的这个节点相对于根节点的位置，看这个节点是不是空，不是说明存在，exist返回真
    编号为5的节点存在，说明总节点数量一定大于等于5。所以二分那里low = mid

    再比如看7存不存在，010 & 111 第二位为1，第一部从根节点向右；001 & 111 第三位也为1，第二步继续向右。
    然后判断当前节点是不是null，发现是null，exist返回假。
    编号为7的节点不存在，说明总节点数量一定小于7。所以high = mid - 1
     */
}
