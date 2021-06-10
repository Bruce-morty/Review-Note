package com.bruce.slidingWindow;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2021/2/4
 * Version:1.0.0
 */
/*
给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

示例：
输入：[1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 */
public class Leetcode643 {
    public static double find(int[] nums, int k) {
        int len = nums.length;
        if (nums.length == k) return Arrays.stream(nums).sum() / k;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (int i = k; i < len; i++) {
            sum = sum - nums[i - k] + nums[i];
            max = Math.max(sum, max);
        }
        return max;

    }

    // 找出平均值 先找出第一个窗口的值 再往后遍历 找出最大的sum
    // easy. 这道题的窗口已经定下来了，长度就是K
    public static double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        // 定义变量保存窗口sum最大值
        int max = sum;
        for (int i = k; i < len; i++) {
            // 现在窗口终点是k - 1 起点是0，窗口向右移动 需要加上右指针 减去窗口左指针
            sum = sum + nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return 1.0 * max / k;
    }
}
