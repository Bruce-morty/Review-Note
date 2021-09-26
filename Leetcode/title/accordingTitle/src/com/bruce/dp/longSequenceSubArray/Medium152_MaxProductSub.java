package com.bruce.dp.longSequenceSubArray;

/**
 * Author: Qi Gao
 * Date:2021/6/13
 * Version:1.0.0
 */
/*
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

示例 1:
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */

public class Medium152_MaxProductSub {

    // 最大连续子数组的乘积 与53题有点像，但是这题由于数字有正负 若Nums[i] < 0 乘以可能最大变最小 最小变成最大
    /*
    base case dp[0] = nums[0]
    状态: dp长度 选择: nums[i] > 0, nums[i] < 0 和 nums[i] = 0 的情况
    重点: 之前状态值的正负也要考虑：例如，在考虑最大值的时候，
    当 nums[i] > 0时，如果 dp[i - 1][1] < 0(之前的状态最大值)此时nums[i] 可以另起炉灶（这里依然是第 53 题的思想）
    此时 dp[i][1] = nums[i], dp[i][1] = max(nums[i], nums[i] * dp[i - 1][1]) if nums[i] >= 0
     */
    public static int maxProduct(int[] nums) {
        int[][] dp = new int[nums.length][2];
        // 定义dp[i][0] 代表最小值 dp[i][1]代表最大
        // dp[i][0]：以 nums[i] 结尾的连续子数组的最小值
        // dp[i][1]：以 nums[i] 结尾的连续子数组的最大值
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        // 写转移方程 和53题一样，判断相乘如果比不乘要好就取乘的值，若不乘比较好就 取nums[i]为dp的值
        for (int i = 1; i < dp.length; i++) {
            if (nums[i] < 0) {
                dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
            } else if (nums[i] >= 0) {
                dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
            }
        }
        // 只关心最大值，需要遍历
        int res = dp[0][1];
        for (int i = 1; i < dp.length; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;
        // 滚动数组
        /*
        int preMax = nums[0];
        int preMin = nums[0];

        // 滚动变量
        int curMax;
        int curMin;

        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                curMax = Math.max(preMax * nums[i], nums[i]);
                curMin = Math.min(preMin * nums[i], nums[i]);
            } else {
                curMax = Math.max(preMin * nums[i], nums[i]);
                curMin = Math.min(preMax * nums[i], nums[i]);
            }
            res = Math.max(res, curMax);

            // 赋值滚动变量
            preMax = curMax;
            preMin = curMin;
        }
        return res
         */
    }
    /*
    若 nums[i] < 0, dp[i - 1][0] < 0 则 dp[i][0]指的是以i结尾子数组的最小值, 两两相乘为正，不应取乘的值
    dp[i][0] = nums[i], 这是状态转移的选择结果。
    又dp[i]的值与 i - 1的值有关系，可以定义两个变量, 存最大和最小，同时比较每个状态,0 1 2 ... len - 1的最大值
    可以直接得到最大值，不用再写一个loop
     */

    // 优化版本
    public static int max2(int[] nums) {
        // 遍历数组时更新最大值 判断当前num[i]的正负 出现负数时将最大和最小交换
        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            // 重点 将imax和imin更新
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }


}
