package com.bruce.map;

import java.util.HashMap;

/**
 * Author: Qi Gao
 * Date:2021/7/7
 * Version:1.0.0
 */
/*
大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。你可以搭配 任意 两道餐品做一顿大餐。
给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同
大餐 的数量。结果需要对 10^9 + 7 取余。
注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
示例 1：
输入：deliciousness = [1,3,5,7,9]
输出：4
解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
示例 2：
输入：deliciousness = [1,1,1,3,3,3,7]
输出：15
解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 */
public class Medium1711_count {

    /*
    brute force time complexity: O(n^2)
    用hash表去保存重复的元素 外层套循环确保每个重复元素的count 若符合2的幂会被加入result
     */
    public int countPairs(int[] deliciousness) {
        int len = deliciousness.length;
        final int MOD = 100000007;
        // 定义数组的最大值 没有大于最大值 * 2的值
        int maxVal = 0;
        int res = 0;
        for (int v : deliciousness) {
            if (v > maxVal) maxVal = v;
        }
        int max = maxVal * 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 遍历数组中的值
        for (int i = 0; i < len; i++) {
            int value = deliciousness[i];
            // 这一步是遍历所有小于maxSum的2的幂 log maxSum操作 判
            // 断map中是否包含与要遍历元素相加等于2幂次的元素 有的话得到value 加到result里
            for (int j = 1; j < max; j <<= 1) {
                // 在这里首先判断j - value 是否存在于map中
                int temp = map.getOrDefault(j - value, 0);
                res = (res + temp) % MOD;
            }
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        return res;
    }
}
