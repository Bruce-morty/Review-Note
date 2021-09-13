package com.bruce.twoPointer;

/**
 * Author: Qi Gao
 * Date:2021/8/25
 * Version:1.0.0
 */
/*
633. 平方数之和
给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c
示例 1：
输入：c = 5
输出：true
解释：1 * 1 + 2 * 2 = 5

示例 2：
输入：c = 3
输出：false

示例 3：
输入：c = 4
输出：true
 */
public class Medium633_SumOfSquart {

    public boolean judgeSquareSum(int c) {
        // 用双指针一次遍历，若等于和就return true
        int left = 0;
        // 这是开平方
        int right = (int)Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) return true;
            if (sum < c) {
                left++;
            }else {
                right--;
            }
        }
        return false;
    }
}
