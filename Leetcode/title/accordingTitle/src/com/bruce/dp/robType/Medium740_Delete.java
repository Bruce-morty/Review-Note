package com.bruce.dp.robType;

/**
 * Author: Qi Gao
 * Date:2021/5/10
 * Version:1.0.0
 */
/*
给你一个整数数组 nums ，你可以对它进行一些操作。
每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

示例 1：
输入：nums = [3,4,2]
输出：6
解释：
删除 4 获得 4 个点数，因此 3 也被删除。
之后，删除 2 获得 2 个点数。总共获得 6 个点数。
示例 2：
输入：nums = [2,2,3,3,3,4]
输出：9
解释：
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
 */
public class Medium740_Delete {
    // 分析题意 在删除某个值的时候获得所有这个值 * 个数 并且删掉所有nums[i] - 1 and nums[i] + 1
    // 我们要获得最大值可以知道需要用动规
    /*
    问题：获得最大点数，每个位置的数字取决于前一个数字是否是最优解
    状态：当前的最大值是删掉这个元素还是不删
    选择：不删-> 最大值是前一个位置的最大值，不删当前位置. 删 -> 前两个位置的最大值加上删掉当前节点的值
     */
    public static int deleteAndEarn(int[] nums) {
        // edge
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return nums[1];
        // 找最大值
        int len = nums.length;
        int max = nums[0];
        for (int i = 0; i < len; i++) {
            max = Math.max(max, nums[i]);
        }
        // 找到最大值之后定义辅助数组all 下标是数组的值，值是每个元素出现的个数
        // nums = [2, 2, 3, 3, 3, 4]; all=[0, 0, 2, 3, 1]; 0没有意义得不到点数
        int[] all = new int[max + 1];
        for (int item : nums) {
            all[item]++;
        }
        // dp 的含义 删掉当前元素可以取得的最大点数，dp的长度应该是max + 1 遍历整个nums的值从小到大
        // correct: dp[i] 含义应该是：遍历到i个元素可以获得的最大点数
        // 因为 1 * n = n 定义了base case
        int[] dp = new int[max + 1];
        dp[1] = all[1];
        dp[2] = Math.max(dp[1], all[2] * 2);
        for (int i = 2; i < max + 1; i++) {
            // 删的话 就是前两个位置的最大加上当前值乘以个数。为什么，因为删掉说明前一个位置的值需要被删掉 nums[i] - 1
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + all[i] * i);
        }
        // 最终最大值是
        return dp[max];
    }
    // 疑惑：dp的含义是"删除当前元素获得的最大点数" 怎么保证删除最大的一个元素时获得的是最大的点数呢

    /*
    序列DP
     */
    int[] cnts = new int[10009];
    public int deleteAndEarn2(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int x : nums) {
            cnts[x]++;
            max = Math.max(max, x);
        }
        // f[i][0] 代表「不选」数值 i；f[i][1] 代表「选择」数值 i
        int[][] f = new int[max + 1][2];
        for (int i = 1; i <= max; i++) {
            f[i][1] = f[i - 1][0] + i * cnts[i];
            f[i][0] = Math.max(f[i - 1][1], f[i - 1][0]);
        }
        return Math.max(f[max][0], f[max][1]);
    }

}
