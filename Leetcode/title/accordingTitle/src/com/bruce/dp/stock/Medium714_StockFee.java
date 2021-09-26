package com.bruce.dp.stock;

/**
 * Author: Qi Gao
 * Date:2021/9/21
 * Version:1.0.0
 */
/*
714. 买卖股票的最佳时机含手续费
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用
你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了
返回获得利润的最大值。
注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费
示例 1：
输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
输出：8
解释：能够达到的最大利润:
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 */
public class Medium714_StockFee {
    /*
    这道题包含 手续费, 我们只要确定买入和卖出 这个阶段出现手续费就可以了.
    即 只有在不持有股票的时候需要把手续费计入
    dp[i][0]: 不持有股票的时候的最大profit dp[i][1]: 持有股票时的最大profit
    dp[i][0] = max(dp[i - 1][1] + prices[i] - fee, dp[i - 1][0]);
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        // int[][] dp = new int[len][2];
        // dp[0][0] = 0;
        // dp[0][1] = -prices[0];
        // for (int i = 1; i < len; i++) {
        //     // 发生手续费只有在持有现金的时候
        //     dp[i][0] = Math.max(dp[i - 1][1] + prices[i] - fee, dp[i - 1][0]);
        //     dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        // }
        //return dp[len - 1][0];
        // 优化
        int preC = 0;
        int preS = -prices[0];
        for (int i = 1; i < len; i++) {
            int newC = Math.max(preS + prices[i] - fee, preC);
            int newS = Math.max(preS, preC - prices[i]);
            preS = newS;
            preC = newC;
        }
        return preC;

    }
}
