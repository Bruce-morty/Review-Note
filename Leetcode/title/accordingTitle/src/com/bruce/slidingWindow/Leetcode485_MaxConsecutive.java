package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/15
 * Version:1.0.0
 */
public class Leetcode485_MaxConsecutive {
    public int findMaxConsecutiveOnes(int[] nums) {
        // 遍历整个数组
        int len = nums.length;
         int count = 0;
         int maxCount = 0;
         for (int i = 0; i < len; i++) {
             if (nums[i] == 1) {
                 count++;
             } else{
                 // 需要把连续的1值清0 保存当前最大的count
                 maxCount = Math.max(count, maxCount);
                 count = 0;
             }
         }
         maxCount = Math.max(count, maxCount);
         return maxCount;

        // 双指针
       /*  int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < len) {
            if (nums[right] == 0) {
                left = right + 1;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;*/
    }
}
