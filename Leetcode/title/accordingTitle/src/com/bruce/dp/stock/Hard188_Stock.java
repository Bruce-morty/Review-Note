package com.bruce.dp.stock;

/**
 * Author: Qi Gao
 * Date:2021/9/21
 * Version:1.0.0
 */
/*
188. 买卖股票的最佳时机 IV
给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
示例 1：
输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2
k 是有界限的，不能一直买卖 不像 leetcode122, 可以无限次操作 找到最大利润
 */
public class Hard188_Stock {
    /*
    情况四是最通用的情况，对于每一天需要使用不同的 k 值更新所有的最大收益，对应持有 0 份股票或 1 份股票。如果 k 超过一个临界值
    最大收益就不再取决于允许的最大交易次数，而是取决于股票价格数组的长度，因此可以进行优化。那么这个临界值是什么呢？
    一个有收益的交易至少需要两天（在前一天买入，在后一天卖出，前提是买入价格低于卖出价格）。如果股票价格数组的长度为 n，
    则有收益的交易的数量最多为 n / 2（整数除法）。因此 k 的临界值是 n / 2。如果给定的 k 不小于临界值，即 k >= n / 2，
    则可以将 k 扩展为正无穷，此时问题等价于情况二 根据状态转移方程，可以写出时间复杂度为 O(nk) 和空间复杂度为 O(nk) 的解法。
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        if (k >= length / 2) {
            return maxProfit(prices);
        }
        int[][][] dp = new int[length][k + 1][2];
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < length; i++) {
            for (int j = k; j > 0; j--) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // caution 这里是j - 1 因为当前手上是有股票的, 必然已经经历过一次交易了.j - 1
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[length - 1][k][0];
    }

    /*
        可以压缩空间，因为只与前一天的值有关
    */
    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        if (k >= len / 2) return maxProfit(prices);
        // 当前的值只与前一天有关, 只需要建立k大小的数组
        int[][] dp = new int[k + 1][2];
        for (int i = 1; i <= k; i++) {
            dp[i][0] = 0;
            dp[i][1] = -prices[0];
        }
        // 遍历prices
        for (int i = 1; i < len; i++) {
            for (int j = k; j > 0; j--) {
                // dp含义: k次交易所得到的最大利润
                // 当前只取前一天的
                dp[j][0] = Math.max(dp[j][1] + prices[i], dp[j][0]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[length - 1][0];
    }


}
