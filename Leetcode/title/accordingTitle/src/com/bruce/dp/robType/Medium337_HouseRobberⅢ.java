package com.bruce.dp.robType;

import com.bruce.node.TreeNode;

import java.util.HashMap;

/**
 * Author: Qi Gao
 * Date:2021/6/17
 * Version:1.0.0
 */
/*
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”
除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”
如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class Medium337_HouseRobberⅢ {

    /*
    树的问题一般用后序遍历, 先找到判断左右再处理中间。树状的DP思路:
    设这棵树有 爷爷 父亲 儿子, 我们定义dp数组含义: dp[i]为以i 为根节点 所能偷到的最大钱
    可知：最大数值 = max(两个父亲偷到的最大钱, 爷爷偷到的+四个孙子偷到的钱)
    为什么不能 一个父亲节点 + 另外两个儿子节点的值呢? --这种情况已经包含在 "两个父亲偷到的最大" 里了
    4 个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱 哪个组合钱多，就当做当前节点能偷的最大钱数。这就是动态规划里面的最优子结构
    int method1 = rob(root.left.left)+rob(root.left.right)+rob(root.right.left)+rob(root.right.right)
    + rob(root.val)
    int method2 = rob(root.left) + rob(root.right)
     */
    public int rob1(TreeNode root) {
        // 暴力解法
        if (root == null) return 0;
        // 找出当前根节点得值 “中序遍历”
        int money = root.value;
        if (root.left != null) {
            money += rob1(root.left.left) + rob1(root.left.right);
        }
        if (root.right != null) {
            money += rob1(root.right.left) + rob1(root.right.right);
        }
        // 比较
        return Math.max(money, rob1(root.left) + rob1(root.right));
    }

    /*
    针对解法一种速度太慢的问题，经过分析其实现，我们发现爷爷在计算自己能偷多少钱的时候，同时计算了 4 个孙子能偷多少钱
    也计算了 2 个儿子能偷多少钱。这样在儿子当爷爷时，就会产生重复计算一遍孙子节点
    记忆化求解
    由于二叉树不适合拿数组当缓存，我们这次使用哈希表来存储结果，TreeNode 当做 key，能偷的钱当做 value
     */
    public int rob2(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return robInternal(root, map);
    }

    /*
    写一个递归的方法去记录每个节点可以偷到的钱
     */
    private int robInternal(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        int money = root.value;
        if (root.left != null) {
            money += robInternal(root.left.right, map) + robInternal(root.left.left,map);
        }
        if (root.right != null) {
            money += robInternal(root.right.left, map) + robInternal(root.right.right, map);
        }
        // 比较加上只投父亲节点的值
        int res =  Math.max(money, robInternal(root.left, map) + robInternal(root.right, map));
        map.put(root, res);
        return res;
    }

    /*
    终极方法，定义多一维状态，判断这个节点是偷还是不偷。
    0. 当前节点选择偷时，那么两个孩子节点就不能选择偷了
    1. 当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
    --->
    定义一个长度为2的数组 int[] res = new int[2]
    任何一个节点能偷到的最大钱的状态可以定义为:
    当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
    当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     */
    public int rob3(TreeNode root) {
        int[] res = robDP(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robDP(TreeNode root) {
        // base case: return 0 whatever it rob node or not 因为它是null
        if (root == null) return new int[]{0, 0};
        // 状态转移的过程
        int[] left = robDP(root.left);
        int[] right = robDP(root.right);
        // 构建dp数组去比较大小
        int[] dp = new int[2];
        // 设dp[0] 为不偷node可以取得的最大值，dp[1] 为偷node可取的最大值
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = root.value + left[0] + right[0];
        // 把两个可能的最大值已经放入dp arr了 可以直接返回
        return dp;
    }
}
