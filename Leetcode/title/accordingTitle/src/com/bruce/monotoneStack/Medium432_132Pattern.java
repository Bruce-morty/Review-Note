package com.bruce.monotoneStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Qi Gao
 * Date:2021/4/4
 * Version:1.0.0
 */
/*
给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j]
如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false
进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？

示例 1：
输入：nums = [1,2,3,4]
输出：false
解释：序列中不存在 132 模式的子序列。
示例 2：
输入：nums = [3,1,4,2]
输出：true
解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 */
public class Medium432_132Pattern {
    /*
    用单调栈 构建bottom - up 递减
    0. 从后往前遍历可以保证 i < j < k 最后一个元素入栈
    1. 遍历 构建栈底到 up的递减栈 若不满足
    出栈 找到左边第一个比它大的值 可以作为3. 用一个变量保存出栈的 2 位置元素。
    2. 栈顶元素大于遍历到的元素才能入栈。小于出栈 找到了3位置的元素 并且3位置的index小于栈顶
    3. 判断元素是否要入栈 要再看当前元素是否大于3位置
     */
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int len = nums.length;
        // 存3(索引)位置的值
        int max_k = Integer.MIN_VALUE;
        stack.push(nums[len - 1]);
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < max_k) return true;
            // 一直出栈
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                // 更新 3(索引)的值 先入栈的下标肯定大于后入栈的
                max_k = stack.pop();
            }
            // 判断是否可以作为2(index) 可以 放入栈 2要比3大才行
            if (nums[i] > max_k) {
                stack.push(nums[i]);
            }

        }
        return false;
    }
}
