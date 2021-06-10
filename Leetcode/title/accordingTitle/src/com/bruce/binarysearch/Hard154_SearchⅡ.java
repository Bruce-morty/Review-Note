package com.bruce.binarysearch;

/**
 * Author: Qi Gao
 * Date:2021/4/9
 * Version:1.0.0
 */
/*
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7]
在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]]
给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，
并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
示例 1：
输入：nums = [1,3,5]
输出：1
示例 2：
输入：nums = [2,2,2,0,1]
输出：0

 */
public class Hard154_SearchⅡ {
    public int findMin(int[] nums) {
        // int min = Integer.MAX_VALUE;
        // for (int i = 0; i < nums.length; i++) {
        //     if (nums[i] < min) {
        //         min = nums[i];
        //     }
        // }
        // return min;
        // 用二分查找 找到最小值
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 计算mid
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                // 中间值小于右边 说明最小值在左边
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // 可能会有相同元素 不知道应该缩减哪边区间 将right-1
                // 如果nums[right]是最小 保证nums[mid] 还在区间内
                right--;
            }
        }
        return nums[left];
    }
}
