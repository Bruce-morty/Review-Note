package com.bruce.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/4/28
 * Version:1.0.0
 */
/*
给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer
子集中每一元素对 (answer[i], answer[j]) 都应当满足：
answer[i] % answer[j] == 0 ，或
answer[j] % answer[i] == 0
如果存在多个有效解子集，返回其中任何一个均可
示例 1：
输入：nums = [1,2,3]
输出：[1,2]
解释：[1,3] 也会被视为正确答案。
示例 2：
输入：nums = [1,2,4,8]
输出：[1,2,4,8]
 */
public class Medium368_LarDivisbleSubset {
    /*
    思路: DP
    这个subset 里的每个元素需要能够整除 -> 根据整除关系知道传递性 a % b, b % c -> a % c
    用DP：我们需要将输入数组nums按照升序排列，以获得一个子集的最小整数或最大整数。根据DP的无后效性
    我们需要将状态定义成 “某个元素必须选择”
    nums[i] 能否接在nums[j]后面取决于是否满足 nums[i] % nums[j]== 0
    定义dp[i]: 考虑前i个数字 且以第i个数为结尾的最长[整数子集]的长度
    得到这个长度之后再去找原数组中的元素
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 0) return new ArrayList<>();
        // 定义dp
        int[] dp = new int[n];
<<<<<<< HEAD
        Arrays.fill(dp, 1);
=======
        Arrays.fill(nums, 1);
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
        // 每个dp能够被自己整除所以初始化值为1 dp是当前数字结尾的最长子集的长度
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // not duplicate 状态转移：最大值: max(当前, 已经符合的 + 1)
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 若更新了dp 同时更新最大长度和最大值是多少 倒叙遍历需要控制条件size > 0 枚举set减少size
            if (dp[i] >maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 倒推获得最大子集
        List<Integer> res = new ArrayList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        // 倒序遍历 由于最大整数不一定包含在最长子集中 我们要枚举所有的dp[i] 直到dp[i] == size
        for (int i = n - 1; i >= 0 && maxSize > 0; i++) {
            // maxSize每一次匹配都需要-- 然后判断最大的元素对它是否整除  判断前一个元素是否符合
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                // 添加到结果集中
                res.add(nums[i]);
                // 然后更新最大元素
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }
}
