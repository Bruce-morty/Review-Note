package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/19
 * Version:1.0.0
 */
/*
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1
返回仅包含 1 的最长（连续）子数组的长度
示例 1：
输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：
输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 */
public class Leetcode1004_consecutiveOne {
    /*
    将题意重新理解一下 找到最长的数组其中0的个数不超过K 所以定义一个变量记录0的个数
    定义一个值 当遇到0的时候就判断0的个数和K的大小
    方法一： 双指针 判断0和K的个数 用while循环直到0的个数等于K
     */
    public static int longestOnes(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int zeroCount = 0;
        while (right < len) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                // 判断左指针是否是0 是0的话因为移除窗口了 zero--
                if (nums[left] == 0) zeroCount--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

    /*
    方法二：双指针 将数组反转 所有为0的变为1 所有1变成0。找到最长的1的个数不超过K的数组
       前缀和数组 P[] 其中 [left, right]是最长1个数不超过K的子数组 P[right] - P[left] > K的话 移动窗口
       保存最大值
     */
    public static int longestOne2(int[] nums, int k) {
        int left = 0;
        int maxLen = 0;
        int len = nums.length;
        int right = 0;
        // 定义前左边的和 和前右指针的和
        int lSum = 0;
        int rSum = 0;
        while (right < len) {
            // 把1变成 0 和 0 变成 1 了  累加起来就是前缀和
            rSum += 1 - nums[right];
            // 用while循环 判断窗口是否形成 保存最大值
            while (rSum - lSum > k) {
                lSum += 1 - nums[left];
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
