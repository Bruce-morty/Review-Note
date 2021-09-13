package com.bruce.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/9/10
 * Version:1.0.0
 */
/*
Given an integer array nums, return the number of all the arithmetic sub sequences of nums.
A sequence of numbers is called arithmetic if it consists of at least three elements and
if the difference between any two consecutive elements is the same.
For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
A sub sequence of an array is a sequence that can be formed by removing some elements (possibly none)
of the array.
For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The test cases are generated so that the answer fits in 32-bit integer.

Example 1:
Input: nums = [2,4,6,8,10]
Output: 7
Explanation: All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]

Example 2:
Input: nums = [7,7,7,7,7]
Output: 16
Explanation: Any subsequence of this array is arithmetic.
 */
public class Hard446_Arithmetic {

    /*
    dp[i] 代表 以nums[i]结尾的数字 最大公差子序列的个数. 由于差值可能很大，所以用map的key存差值。 value存个数
    dp[i] += dp[j] + 1. j是之前能形成公差的数量 若符合
    用dp，e.g 当nums = [2, 4, 6, 8].  遍历到8, 8 - 2 = 6. map中没有6的key，8 - 4 = 4. map中有4的key
    4的key是从 6 - 2的公差 存到map中的.
    为什么res 就可以直接加了。首先根据三个以上的序列，我们可以知道两个数字才能出现公差，再根据map的containsKey，
    若是包含了key 则隐喻的表示，出现了至少3个符合条件的数字。所以所有符合的都直接加
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        // dp[i]：以 nums[i] 结尾，公差为 key 的长度大于等于 2 的等差数列的个数
        Map<Long, Integer>[] dp = new HashMap[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new HashMap<>();
        }

        int res = 0;
        // 从 1 开始就可以
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                long diff = nums[i] - nums[j];
                // dp[i][diff] += (dp[j][diff] + 1) ，Java 写起来有点麻烦，
                // 表示 nums[i] 可以接在之前「公差相等」的等差数列后面形成长度更长的等差数列
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + dp[j].getOrDefault(diff, 0) + 1);

                // 与之前的等差数列公差相等的时候，说明可以接上，此时计算结果
                if (dp[j].containsKey(diff)) {
                    // 理解：对结果的贡献「恰好是」之前的某个 j 的对应状态值，
                    // 这里的 j 一定会在之前的某一个 i 加上 1，看上面有注释的那一行代码
                    res += dp[j].get(diff);
                }
            }
        }
        return res;
    }
}
