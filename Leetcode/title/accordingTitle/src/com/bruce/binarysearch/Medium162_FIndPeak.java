package com.bruce.binarysearch;

/**
 * Author: Qi Gao
 * Date:2021/9/15
 * Version:1.0.0
 */
/*
162. 寻找峰值
峰值元素是指其值严格大于左右相邻值的元素。
给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
你可以假设 nums[-1] = nums[n] = -∞
你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
示例 1：
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
示例 2：
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
 */
public class Medium162_FIndPeak {

    /*
    Time Complexity: O(log n)
    Space Complexity: O(1)
     */
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        // 用二分查找 mid的左边大边大于mid 说明左边是递减的 否则右边递增
        // 一直压缩
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // caution 不能用 mid - 1, 因为经过(right - left) >> 1 可能会越界
            // e.g [0,1] mid会取索引为0的值，然后mid - 1 越界. 那为什么mid + 1 不越界呢
            // 不管怎么样 / 2 之后会得到较小的值，例如left和right 收缩到最右边中间只差 1 (right - left ) / 2 = 0
            // mid 取到left的值，然后判断 nums[mid] 和 nums[mid+ 1] 就是比较nums[left] 和 nums[right]
            if (nums[mid] > nums[mid + 1]) {
                // 去左边找递增的值
                right = mid;
            }else {
                // 左边递减 去右边找递增的值
                left = mid + 1;
            }
        }
        return left;
    }
}
