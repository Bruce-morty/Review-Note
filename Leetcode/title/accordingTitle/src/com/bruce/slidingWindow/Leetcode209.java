package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/10
 * Version:1.0.0
 */
/*
209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
并返回其长度。如果不存在符合条件的子数组，返回 0 。
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。

 */
public class Leetcode209 {

    public int minSubArrayLen(int target, int[] nums) {
        // 定义两个指针
        int left = 0;
        int right = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            // 如果大于指定值的话记录当前窗口长度 移除左指针 左指针++
            while (sum >= target) {
                sum -= nums[left];
                res = Math.min(res, right - left + 1);
                left++;
            }
            right++;
        }
        if (res == Integer.MAX_VALUE) res = 0;
        return res;
        // int lo = 0;
        // int hi = 0;
        // int min = Integer.MAX_VALUE;
        //     while (hi < nums.length) {
        //         target -= nums[hi++];
        //         while (target <= 0) {
        //             min = Math.min(min, hi - lo);
        //             target += nums[lo++];
        //         }
        //     }
        //     return min == Integer.MAX_VALUE ? 0 : min;
    }
}
