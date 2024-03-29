package com.bruce.dp;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2021/3/4
 * Version:1.0.0
 */
/*
300. 最长递增子序列
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
LIS: longest increasing sub
 */
public class Medium300_LIS {

    /*
    二分查找: 返回最长序列的长度 即只需要构造一个递增数组
     */
    public static int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];

            /***** 搜索左侧边界的二分查找 *****/
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    // 为什么只需要到mid就行了 而不是mid - 1
                    // 若找到的话也是还在找 找到最前面的那个 然后left == right 或者 left > right 退出
                    right = mid;
                }
            }
            /*********************************/
            // 没找到合适的牌堆，新建一堆
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶 或者放到该放的位置
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }

    /*
    dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
    根据这个定义，我们的最终结果（子序列的最大长度）应该是 dp 数组中的最大值。
    int res = 0;
    for (int i = 0; i < dp.size(); i++) {
        res = Math.max(res, dp[i]);
    }
    return res;
    根据刚才我们对 dp 数组的定义，现在想求 dp[5] 的值，也就是想求以 nums[5] 为结尾的最长递增子序列
    nums[5] = 3，既然是递增子序列，我们只要找到前面那些结尾比 3 小的子序列，然后把 3 接到最后，就可以形成一个新的递增子序列
    而且这个新的子序列长度加一
    两层遍历 i作为结尾 j作为结尾 但是比i小，判断nums[i] nums[j] 若大于nums[j] 就可以连起来成为更长的子序列
    for (int j = 0; j < i; j++) {
    if (nums[i] > nums[j])
        dp[i] = Math.max(dp[i], dp[j] + 1);
    }
     */
    public static int dp(int[] nums) {
        // 用dp
         int[] dp = new int[nums.length];
         // 定义每个dp位置的最大值
         int max = 1;
         Arrays.fill(dp, 1);
         // 动态转移: 要找到当前nums[i]位置的最长上升子序列 只需要判断当前dp[i]和之前的最大值
         for(int i = 0; i < nums.length; i++) {
             // dp[i] = max(dp[i], max);
             for (int j = 0; j < i; j++) {
                 if (nums[i] > nums[j]) {
                     // 可知i可以放在j后面形成递增序列了 判断之前dp最大的大还是现在的大
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
             max = Math.max(max, dp[i]);
         }
         return max;
    }
}
