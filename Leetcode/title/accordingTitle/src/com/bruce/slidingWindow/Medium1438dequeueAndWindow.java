package com.bruce.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2021/2/21
 * Version:1.0.0
 */
/*
1438. 绝对差不超过限制的最长连续子数组
给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
如果不存在满足条件的子数组，则返回 0 。
示例 1：
输入：nums = [8,2,4,7], limit = 4
输出：2
解释：所有子数组如下：
[8] 最大绝对差 |8-8| = 0 <= 4.
[8,2] 最大绝对差 |8-2| = 6 > 4.
[8,2,4] 最大绝对差 |8-2| = 6 > 4.
[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
[2] 最大绝对差 |2-2| = 0 <= 4.
[2,4] 最大绝对差 |2-4| = 2 <= 4.
[2,4,7] 最大绝对差 |2-7| = 5 > 4.
[4] 最大绝对差 |4-4| = 0 <= 4.
[4,7] 最大绝对差 |4-7| = 3 <= 4.
[7] 最大绝对差 |7-7| = 0 <= 4.
因此，满足题意的最长子数组的长度为 2 。
示例 2：
输入：nums = [10,1,2,4,7,2], limit = 5
输出：4
解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
示例 3：

输入：nums = [4,2,2,2,4,4,2,2], limit = 0
输出：3
 */
public class Medium1438dequeueAndWindow {

    /**
     * 用单调队列加来处理滑动窗口
     * @param nums input的数组
     * @param limit 绝对值的查的edge
     * @return 返回数组长度
     */
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int right = 0;
        // 保存当前窗口最大值 以便与最小值比较
        int maxLen = 0;
        Deque<Integer> maxQ = new LinkedList<>();
        Deque<Integer> minQ = new LinkedList<>();
        while (right < nums.length) {
            while (!maxQ.isEmpty() && nums[maxQ.peekLast()] < nums[right]) {
                maxQ.pollLast();
            }
            maxQ.add(right);
            while (!minQ.isEmpty() && nums[minQ.peekLast()] > nums[right]) {
                minQ.pollLast();
            }
            minQ.add(right);
            // 如果当前窗口最大值 - 最小值大于limit 弹出元素 移动窗口
            while (!maxQ.isEmpty() && !minQ.isEmpty() && nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > limit) {
                // 移动窗口 更新最大值和最小值 出队
                if(maxQ.peek() == left) maxQ.poll();
                if (minQ.peek() == left) minQ.poll();
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        // 普通的滑动窗口：重点 将left移动到right 然后left 一直向前移动
        return maxLen;
        //   int n = nums.length;
        // int left = 0;
        // int right = 0;
        // int res = 0;

        // LinkedList<Integer> maxQueue = new LinkedList<>();
        // LinkedList<Integer> minQueue = new LinkedList<>();

        // while(right<n){
        //     while(!maxQueue.isEmpty()&&maxQueue.getLast()<nums[right]){
        //         maxQueue.pollLast();
        //     }
        //     maxQueue.addLast(nums[right]);
        //     while(!minQueue.isEmpty()&&minQueue.getLast()>nums[right]){
        //         minQueue.pollLast();
        //     }
        //     minQueue.addLast(nums[right]);
        //     while(Math.abs(maxQueue.getFirst()-minQueue.getFirst())>limit){
        //         // 这一步判断不一样 速度就不一样 这个队列存的是值 而不是index
        //         if(maxQueue.getFirst()==nums[left]){
        //             maxQueue.pollFirst();
        //         }
        //         if(minQueue.getFirst()==nums[left]){
        //             minQueue.pollFirst();
        //         }
        //         left++;
        //     }
        //     res = Math.max(res,right-left+1);
        //     right++;
        // }
        // return res;
    }
}
