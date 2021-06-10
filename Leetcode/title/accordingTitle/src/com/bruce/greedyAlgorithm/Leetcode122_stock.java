package com.bruce.greedyAlgorithm;

/**
 * Author: bruce
 * Date:2021/2/9
 * Version:1.0.0
 */
/*
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。设计一个算法来计算你所能获取的最大利润。
你可以尽可能地完成更多的交易（多次买卖一支股票）。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
示例 1:
输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
示例 2:
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 */
public class Leetcode122_stock {


    /*
    股票买卖策略：
        单独交易日： 设今天价格p1、明天价格p2， 则今天买入明天卖出可赚取 p2 - p1 的钱（可能是负数）
        连续上涨交易日： 设此上涨交易日股票价格分别为 p1, p2, ... , pn. 则第一天买最后一天卖收益最大 即pn - p1；
        等价于每天都买卖: (p2-p1) + (p3 - p2) + .... + (pn - pn-1);
        连续下降交易日： 则不买卖收益最大，即不会亏钱。
    算法流程：
       遍历整个股票交易日价格列表 price，策略是所有上涨交易日都买卖（赚到所有利润），所有下降交易日都不买卖（永不亏钱）。
        1.设 tmp 为第 i-1 日买入与第 i 日卖出赚取的利润，即 tmp = prices[i] - prices[i - 1] ；
        2. 当该天利润为正 tmp > 0，则将利润加入总利润 profit；当利润为 0 或为负，则直接跳过；
        3. 遍历完成后，返回总利润 profit。
     */
    // 贪心算法
    //这道题 「贪心」 的地方在于，对于 「今天的股价 - 昨天的股价」，
    // 得到的结果有 3 种可能：① 正数，② 0，③负数。贪心算法的决策是： 只加正数 。
    public static int greedyMax(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
       /* int diff = 0;
        for (int i = 1; i < prices.length; i++) {
            // 贪心算法只加正数的值和0，即如果当前股票值卖出小于0则不买
            diff += Math.max(0, prices[i] - prices[i - 1]);
        }
        return diff;*/
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }
        return profit;
    }
    /*
    Dp: dp[i][j]
    定义状态：dp[i][0]和dp[i][1]： 0代表当前持有的是现金 1代表的是当前持有股票所拥有的现金数
    dp[i][0] 的值取决于前一天拿的是股票(可能是负数) 加上当前股票的值 和 前一天是现金 当前的股票不理会 的最大值
    dp[i][1] 代表当前持有股票所拥有的现金数
    动态转移: dp[i][0] =  max{dp[i-1][1]} + prices[i], dp[i-1][0]}
            dp[i][1] =  max{dp[i-1][1], dp[i-1][0] - prices[i]} 为什么减
            减是代表用前一天cash的值买这个位置的股票。如果差值小于前一天股票所持有的cash值
            说明不应该买这个股 取前一天持有股的cash值 当前持有第i天的股票等于前一天的现金值减去当前股票值才等于 dp[i][1]
     */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        int res = 0;
        for (int i = 1; i < len; i++) {
            // 确定当前的dp值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        // 最后返回这个数组的len - 1位置的cash
        return dp[len - 1][0];
    }
    // 优化：dp
    public  static int maxP(int[] prices) {
        //注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，因此我们不必存储这些无关的状态，
        // 只需要将 dp[i-1][0]和dp[i−1][1] 存放在两个变量中 通过它们计算出dp[i][0]和dp[i][1]并存回对应的变量
        //以便于第 i+1 天的状态转移即可。
        int n = prices.length;
        int preCash = 0;
        int preStock = -prices[0];
        for (int i = 1; i < n; i++) {
            int newCash = Math.max(preCash, preStock + prices[i]);
            int newStock = Math.max(preCash - prices[i], preStock);
            // 将新得到的值赋给dp0和dp1
            preCash = newCash;
            preStock = newStock;
        }
        return preCash;
    }
}
