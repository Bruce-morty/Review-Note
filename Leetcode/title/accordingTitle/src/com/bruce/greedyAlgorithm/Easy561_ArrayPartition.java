package com.bruce.greedyAlgorithm;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn)
使得从 1 到 n 的 min(ai, bi) 总和最大。
返回该 最大总和
示例 1：
输入：nums = [1,4,3,2]
输出：4
解释：所有可能的分法（忽略元素顺序）为：
1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
所以最大总和为 4
 */
public class Easy561_ArrayPartition {
    public int arrayPairSum(int[] nums) {
        // 两两分组 取min 然后相加拿到最大值
        // 贪心求解
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i += 2) {
            // 排序完后 每次加两个数中的前一个数 就可以保证加起来的是最大
            res += nums[i];
        }
        return res;
    }
    /*
    time complexity: O(nlogn) because we use sort algorithm it will spend logn time. and iterate
    the array, spend O(n).
    space complexity: O(logn)
     */
}
