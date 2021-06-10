package com.bruce.title;

/**
 * Author: Qi Gao
 * Date:2021/4/6
 * Version:1.0.0
 */
/*
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most 
twice and return the new length.
Do not allocate extra space for another array; you must do this by modifying the input
array in-place with O(1) extra memory.

Example 1:
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3]
Explanation: Your function should return length = 5, with the first five elements of nums
being 1, 1, 2, 2 and 3 respectively. It doesn't matter what you leave beyond the returned length.
 */
public class Medium80_RemoveDuplicate {

    public static int removeDuplicate(int[] nums) {
        // double pointer 由于要保留两个重复项 我们定义slow 和 fast
        // slow代表已经处理好的数组的长度 fast代表要遍历的元素
        int len = nums.length;
        if (len < 2) return len;
        int slow = 2;
        int fast = 2;
        while (fast < len) {
            // 若 nums[slow - 2] == nums[fast] slow不变 fast++
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
