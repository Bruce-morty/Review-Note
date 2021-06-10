package com.bruce.title;

/**
 * Author: bruce
 * Date:2021/3/2
 * Version:1.0.0
 */
/*
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线垂直线 i 的两个端点分别为 
(i, ai) 和 (i, 0)
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器。
输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：
输入：height = [1,1]
输出：1
示例 3：
输入：height = [4,3,2,1,4]
输出：16
示例 4：
输入：height = [1,2,1]
输出：2

 */
public class Medium11_Container {

    /*
    找到最大值和第二大的值 同时间隔最长 可以得到最大的容量的水
     */
    public static int maxArea(int[] height) {
        // 怎么使用双指针去证明和移动? 无论移动大或小指针 长度总会-1
        // 1. 移动大指针的结果: 容量可能变小或者不变 因为当前指针值最大并且长度最长 最多也就是不变
        // 移动小指针的结果：容量可能变大 因为可能找到比当前值大的指针
        // 所以移动小指针。我们每次都记录了当前的值 与缩减的容量进行比较 会找到最大面积
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left < right) {
            // 找到小的指针
            int minOfPointer = Math.min(height[left], height[right]);
            // right - left 不用 - 1 已经等于中间可以存的水了
            int currArea = minOfPointer * (right - left);
            maxArea = Math.max(currArea, maxArea);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
