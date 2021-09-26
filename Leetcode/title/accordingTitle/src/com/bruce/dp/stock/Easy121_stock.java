package com.bruce.dp.stock;

/**
 * Author: Qi Gao
 * Date:2021/9/21
 * Version:1.0.0
 */
/*
121. 买卖股票的最佳时机
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
示例 1：
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 */
public class Easy121_stock {
    public int maxProfit(int[] prices) {
        // 暴力法是直接遍历数组 找到当前买和之后买的数 保存最大值
        if (prices == null || prices.length < 1) return 0;
        int len = prices.length;
        int res = 0;
        // for (int i = 0; i < len; i++) {
        //     for (int j = i + 1; j < len; j++) {
        //         if (prices[j] > prices[i]) {
        //             res = Math.max(res, prices[j] - prices[i]);
        //         }
        //     }
        // }w
        // 暴力法会超时 我们用另一个方法 找到最小的数
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else if (prices[i] - minValue > res) {
                // 当前值减去min要大于 res 才把这个值算作maxProfit
                res = prices[i] - minValue;
            }
        }
        return res;
    }

    /*
    dp思想去解决 每次找到前一次买的最小值 然后加上当前prices可以卖出 取最大值
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit0 = 0, profit1 = -prices[0];
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, -prices[i]);
        }
        return profit0;
    }
}
