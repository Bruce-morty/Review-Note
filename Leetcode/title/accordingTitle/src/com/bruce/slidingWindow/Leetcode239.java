package com.bruce.slidingWindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2021/1/30
 * Version:1.0.0
 */
/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。
示例 1：
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
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
    public static int[] bruteForce(int[] nums, int k) {
    // 暴力破解 定义一个新数组存窗口的最大值
        int n = nums.length;
        int[] newNums = new int[n - k + 1];
        // 判断数组是否为空
        if (n * k == 0) return new int[0];
        // 遍历新数组长度 将max存入newNums
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            // 里面的循环 j应该是窗口的左指针，然后小于窗口的长度=k 就可以遍历整个窗口 右指针是 j + k
            for (int j = i; j < k + i; j++) {
                max = Math.max(max, nums[j]);
            }
            newNums[i] = max;
        }
        return newNums;
    }

    /*
    单调队列去解决滑动窗口
    遍历数组，将 数 存放在双向队列中，并用 L,R 来标记窗口的左边界和右边界。队列中保存的并不是真的数，而是该数值对应的数组下标
    位置，并且数组中的数要从大到小排序。如果当前遍历的数比队尾的值大，则需要弹出队尾值，直到队列重新满足从大到小的要求。
    刚开始遍历时，L 和 R 都为 0，有一个形成窗口的过程，此过程没有最大值，L 不动，R 向右移。当窗口大小形成时，
    L 和 R 一起向右移，每次移动时，判断队首的值的数组下标是否在 [L,R] 中，如果不在则需要弹出队首的值，
    当前窗口的最大值即为队首的数。
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口的最大值的index 保证是一个单调队列 从大到小
        LinkedList<Integer> queue = new LinkedList<>();
        int n = nums.length;
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            // 保证队列是从大到小排列 如果最后一个元素比当前数组元素要小 poll出来
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 如当前queue的index不在窗口[i - k  + 1, i]里面 把这个index poll出来, i - k + 1是当前窗口的第一个index位置
            if (queue.peekFirst() <= i - k) {
                queue.poll();
            }
            // 当形成窗口时保存最大值 k是长度 i是索引 所以要判断是否形成窗口 i要加1
            if (i + 1 >= k) {
                output[i + 1 - k] = nums[queue.peekFirst()];
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] arr1 = bruteForce(nums, 3);
        int[] arr2 = maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
