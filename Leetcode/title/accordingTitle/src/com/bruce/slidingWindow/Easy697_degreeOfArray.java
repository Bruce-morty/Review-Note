package com.bruce.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: bruce
 * Date:2021/2/21
 * Version:1.0.0
 */
/*
697. 数组的度
给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
示例 1：
输入：[1, 2, 2, 3, 1]
输出：2
解释：
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
示例 2：
输入：[1,2,2,3,1,4,2]
输出：6
 */
public class Easy697_degreeOfArray {

    /*
   1. 首先找到数组的度 用map来记录数组各位置的度  2. 因为要找到最短的 度相同时，要记录出现的位置第一次和最后一次
   caution: 最短长度 取决于这个数字的第一次出现位置和最后一次出现位置
     */
    public static int  findShortestSubArray(int[] nums) {
        // 定义hashmap
        HashMap<Integer, int[]> map = new HashMap<>();
        // int 数组中第一个位置存度 第二个位置存第一次出现的 第三个位置存最后一次出现
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                // 度增加了 增加数组index=0的值
                map.get(nums[i])[0]++;
                // 更新最后一次出现的值
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        // 再遍历这个map 找到等于数组的度的最小数组
        int count = 0;
        int minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] value = entry.getValue();
            // 这一步是为了找到最大的度 遍历整个map
            if (count < value[0]) {
                count = value[0];
                // 这一步直接记录了这个数字的度的子数组长度 下一步循环就比较下一个map的value
                minLen = value[2] - value[1] + 1;
            } else if (count == value[0]) {
                // 碰到相等的度 要去找它们的最小长度 就比较上一个度的minLen 和当前的长度大小
                if (minLen > value[2] - value[1] + 1) {
                    minLen = value[2] - value[1] + 1;
                }
            }
        }
        return  minLen;
    }
}
