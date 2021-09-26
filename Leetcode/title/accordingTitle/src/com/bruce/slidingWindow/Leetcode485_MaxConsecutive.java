package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/15
 * Version:1.0.0
 */
<<<<<<< HEAD
/*
给定一个二进制数组， 计算其中最大连续 1 的个数。

示例：

输入：[1,1,0,1,1,1]
输出：3
解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 */
public class Leetcode485_MaxConsecutive {

=======
public class Leetcode485_MaxConsecutive {
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
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
