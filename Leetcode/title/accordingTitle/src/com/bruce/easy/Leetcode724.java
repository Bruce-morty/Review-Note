package com.bruce.easy;

/**
 * Author: bruce
 * Date:2021/1/29
 * Version:1.0.0
 */
/*
给你一个整数数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
数组 中心索引 是数组的一个索引，其左侧所有元素相加的和等于右侧所有元素相加的和。
如果数组不存在中心索引，返回 -1 。如果数组有多个中心索引，应该返回最靠近左边的那一个。
注意：中心索引可能出现在数组的两端。
示例 1：
输入：nums = [1, 7, 3, 6, 5, 6]
输出：3
解释：
索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
同时, 3 也是第一个符合要求的中心索引。
示例 2：
输入：nums = [1, 2, 3]
输出：-1
解释：
数组中不存在满足此条件的中心索引。
 */
public class Leetcode724 {
    // 用前n项和来做，保存每个位置的和 然后遍历数组进行比较
    public static int pivotIndex(int[] nums) {
        // 前n项和的模板
        int len = nums.length;
        int[] sum = new int[len + 1];
        // 将数组的和一个个存入sum中 index = 0不存值
        for (int i = 1; i <= len; i++) {
            // sum这个位置的前n项和 等于 sum的 n - 1项 + nums这个索引的值
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 遍历数组
        for (int i = 1; i <= len; i++) {
            // 分别记录左边和右边
            int left = sum[i - 1];
            int right = sum[len] - sum[i];
            // 前n项和中 是从1开始存值 所以index ~[1, len]
            // 返回的是中间索引 因为是从 1 开始遍历 中间索引是 i - 1 caution!
            if (left == right) return i - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex(arr));
    }
}
