package com.bruce.monotoneStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Qi Gao
 * Date:2021/3/30
 * Version:1.0.0
 */
/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。
输入: [2,1,5,6,2,3]
输出: 10
 */
public class Hard84_LargestDiagrom {
    public int largestRectangleArea(int[] heights) {
        // 用栈实现，怎么用栈呢？构建递增栈
        // 先暴力法 定义当前元素可以得到的最大面积 超时
        // int len = heights.length;
        // if (len == 0) return 0;
        // int maxArea = 0;
        // for (int i = 0; i < len; i++) {
        //     // 往左边看 能延长到多大 找到大于等于当前矩形高度的最左边元素
        //     int maxLeft = i;
        //     while (maxLeft > 0 && heights[maxLeft - 1] >= heights[i]) {
        //         maxLeft--;
        //     }
        //     // 找右边大于等于
        //     int maxRight = i;
        //     while (maxRight < len - 1 && heights[maxRight + 1] >= heights[i]) {
        //         maxRight++;
        //     }
        //     // 找到了 计算最大面积
        //     int currArea = heights[i] * (maxRight - maxLeft + 1);
        //     maxArea = Math.max(maxArea, currArea);
        // }
        // return maxArea;

        // 栈 找到右边第一个当前元素小的值 也就是实现单调递减栈
        Deque<Integer> stack = new LinkedList<>();
        int len = heights.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            // 若栈顶大于遍历元素 出栈 当前元素是栈顶的右边第一个最小值
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int currHeight = heights[stack.pop()];
                // 然后由于是单调栈 栈顶到栈底是递减的 找到左边第一个比它小的元素 相等高度的就不管
                // 找到小于它的index就可以计算中间相等元素的面积了
                // 不用判断也是一样的 不相等的话就继续出栈 直到碰到左边第一个小的元素 这个while可以省
                while (!stack.isEmpty() && heights[stack.peek()] == currHeight) {
                    stack.pop();
                }
                // count the width
                int width;
                if (stack.isEmpty()) {
                    width = i;
                }else{
                    width = i - stack.peek() - 1;
                }
                res = Math.max(res, width * currHeight);
            }
            stack.push(i);
        }
        // 遍历完了 判断栈里还有没有元素
        while(!stack.isEmpty()) {
            // 要把栈内元素一个个出栈
            int curr = heights[stack.pop()];
            // 不用判断也是一样的 不相等的话就继续出栈 直到碰到左边第一个小的元素 这步可以省略
            while(!stack.isEmpty() && heights[stack.peek()] == curr) {
                stack.pop();
            }
            // 当前的width就是len
            int width = 0;
            if (stack.isEmpty()) {
                width = len;
            } else {
                width = len - stack.peek() - 1;
            }
            res = Math.max(res, width * curr);
        }
        return res;
    }

    // 另一种方法 用sentinel(哨兵)
    public static int largestArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        if (len == 1) return heights[0];
        int res = 0;
        // define 2 sentinel 在左边和右边 这样不用判断栈是否为空了
        int[] newArr = new int[len + 2];
        newArr[0] = 0;
        System.arraycopy(heights, 0, newArr, 1, len);
        newArr[len + 1] = 0;
        heights = newArr;
        len += 2;
        Deque<Integer> stack = new LinkedList<>();
        // 先放入sentinel
        stack.addLast(0);
        // 遍历
        for (int i = 1; i < len; i++) {
            // 构建递减栈
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                // 为什么这里不用判断前面有相同的元素了
                // 不用判断也是一样的 不相等的话就继续出栈 直到碰到左边第一个小的元素
                // 出栈得到当前出栈元素的高
                int currHeight = heights[stack.pop()];
                int currWidth = i - stack.peek() - 1;
                res = Math.max(res, currHeight * currWidth);
            }
            stack.push(i);
        }
        return res;
    }
}
