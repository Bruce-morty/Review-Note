package com.bruce.binarysearch;

/**
 * Author: bruce
 * Date:2020/12/1
 * Version:1.0.0
 */
/*
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。

示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：
输入：nums = [], target = 0
输出：[-1,-1]

输入： nums = [1], target = 1
输出：[0,0]
 */
public class Leetcode34 {
    public static int[] searchRange(int[] nums, int target) {
        // 思路，要找第一个target index和第一个比target大的index
        // 要复用查找的代码，定义一个flag 若false就查右边
        int leftIndex = binarySearch(nums, target, true);
        // 因为是找比target大的第一个元素 减去1是最后一个位置
        int rightIndex = binarySearch(nums, target, false) - 1;
        // 要判断 若只有一个 即left == right 也可以，照样返回
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[] {leftIndex, rightIndex};
        }
        return new int[] {-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean flag) {
        // caution: define result 这是nums的长度，因为返回过去要减1
        int res = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 若相等的话，就根据flag 知道是不是找第一个target
            if (target < nums[mid] || flag && nums[mid] == target) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
