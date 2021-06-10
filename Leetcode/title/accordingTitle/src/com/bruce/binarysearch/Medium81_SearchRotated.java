package com.bruce.binarysearch;

/**
 * Author: Qi Gao
 * Date:2021/4/7
 * Version:1.0.0
 */
/*
已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1]
..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）
例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
示例 1：

输入：nums = [2,5,6,0,0,1,2], target = 0
输出：true
示例 2：

输入：nums = [2,5,6,0,0,1,2], target = 3
输出：false
 */
public class Medium81_SearchRotated {
    public boolean search(int[] nums, int target) {
        //    for (int i = 0; i < nums.length; i++) {
        //        if (nums[i] == target) {
        //             return true;
        //        }
        //    }
        //    return false;
        int len = nums.length;
        if (len == 0) return false;
        if (len == 1) return nums[0] == target;
        int left = 0;
        int right = len - 1;
        // 用二分查找 确定哪边是有序的 还要确定是否left mid right 相等
        while(left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return true;
            if (nums[left] == nums[mid] && nums[mid] == nums[right]){
                left++;
                right--;
            }
            // 判断哪边有序
            else if (nums[left] <= nums[mid]) {
                // 判断左边是不是真的有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 若右边有序 判断中间是否小于target 若大于target target在左边。右边是有序的右边判断过了
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
