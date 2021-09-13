package com.bruce.monotoneStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Qi Gao
 * Date:2021/7/2
 * Version:1.0.0
 */
/*
请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
如果气温在这之后都不会升高，请在该位置用 0 来代替
例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]
提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数
 */
public class Medium739_Temperatures {
    /*
    分析，找到当前数组中这个元素的右边第一个大位置的值，可使用单调栈 用递增栈 若栈顶元素小于比较元素 出栈
     */
    public int[] dailyTemperatures(int[] T) {
        // 找右边第一个大的温度，add到数组中他们的长度
        int len = T.length;
        Deque<Integer> stack = new LinkedList<Integer>();
        int[] res = new int[len];
        // 遍历数组
        for (int i = 0; i < len; i++) {
            // 判断stack
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                // 出栈 计算距离
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }
}
