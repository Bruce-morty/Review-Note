package com.bruce.monotoneStack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2021/3/8
 * Version:1.0.0
 */
/*
Given a circular array (the next element of the last element is the first element of the array),
print the Next Greater Number for every element. The Next Greater Number of a number x is the first
greater number to its traversing-order next in the array, which means you could search circularly to
find its next greater number. If it doesn't exist, output -1 for this number.

Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
 */
public class Medium503 {
    /*
    单调递增栈是指，元素pop时为递增的。同理，单调递减栈是元素pop出时大小顺序是递减的
    递增(减)栈 可以找到当前元素左右两侧比自己第一个大(小)的元素.对栈顶元素，可找右侧比自己第一个小（大）的元素
     已本题为例，遍历到的数比栈顶元素大，出栈。对于栈顶元素来说：找到右边第一个比自己大的元素。
    对于新元素，等待破坏栈单调性的元素出栈以后，可以找到左边第一个比自己大的元素
     */
    /*
    circular 怎么把循环数组全部遍历完？拉直：将第一个元素最后拼接到最后一个元素后面 然后对长度取余
    注意到只遍历一次序列是不够的，例如序列 [2,3,1]，最后单调栈中将剩余 [3,1]，
    其中元素 [1] 的下一个更大元素还是不知道的。
    一个朴素的思想是，我们可以把这个循环数组「拉直」，即复制该序列的前 n-1 个元素拼接在原序列的后面。
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        // 重点是拉直
        for (int i = 0; i < n * 2 - 1; i++) {
            // 找到右边第一个比它大的元素？若比栈顶元素大就出栈 找到了 比栈顶元素小就入栈
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }
}
