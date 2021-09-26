package com.bruce.title;

import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2020/11/19
 * Version:1.0.0
 */
/*
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */
public class Leetcode239 {
    // 滑动窗口，用一个单调的双端队列存窗口中值的索引
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return new int[0];
        }
        int index = 0;
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // 在队列不为空的情况下，如果队列尾部的元素要比当前的元素小，或等于当前的元素
            // 那么为了维持从大到小的原则，必须让尾部元素弹出
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 不走 while 的话，说明我们正常在队列尾部添加元素
            queue.addLast(i);
            // 如果滑动窗口已经略过了队列中头部的元素，则将头部元素弹出
            if (queue.peekFirst() <= (i - k)) {
                queue.pollFirst();
            }
            // 看看窗口有没有形成，只有形成了大小为 k 的窗口，我才能收集窗口内的最大值
            if (i >= (k - 1)) {
                res[index++] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

    // 暴力法
    public static int[] maxSlid(int[] arr, int k) {
        //定义新数组长度
        int[] res = new int[arr.length - k + 1];
        int max = 0;

        // 定义两个循环，在内循环里面判断max
        for (int i = 0; i < arr.length - k + 1; i++) {
            // j相当于遍历arr的索引，如果前面 arr.length 不 -1 则j 只能取到arr.length - 2
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, arr[j]);
            }
            res[i] = max;
        }
        return res;
    }


}
