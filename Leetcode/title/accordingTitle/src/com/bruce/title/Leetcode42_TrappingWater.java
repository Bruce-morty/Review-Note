package com.bruce.title;

import java.util.Stack;

/**
 * Author: bruce
 * Date:2021/2/16
 * Version:1.0.0
 */
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1
 compute how much water it can trap after raining.
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]
In this case, 6 units of rain water (blue section) are being trapped.
Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
 */
public class Leetcode42_TrappingWater {

    /*
    method1:brute force
     */
    public static int trap1(int[] height) {
        // 按列去求，找到当前列的左边最高列和右边最高列
        int res = 0;
        int len = height.length;
        // 从1开始 < len - 1
        for (int i = 1; i < len - 1; i++) {
            // 细节：每次都要定义一个新的初始值，
            // 1. 然后从当前列的左边去比较
            // 2. 从当前列右边去找
            // 3. 找到最小值与当前列进行比较
            int maxLeft = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > maxLeft) maxLeft = height[j];
            }
            int maxRight = 0;
            for (int j = i + 1; j < len; j++) {
                if (height[j] > maxRight) maxRight = height[j];
            }
            // 找到最小的墙判断是否大于当前列值 能存下的值取决于最小的木板
            int min = Math.min(maxLeft, maxRight);
            if (height[i] < min) {
                res += min - height[i];
            }
        }
        return res;
    }

    /*
    解法2：DP maxL[i]和maxR[i]的定义：当前i元素的左边最大值， 当前i的右边最大值
     */
    public static int trap2(int[] height) {
        // 可以定义两个数组 存每个位置当前左边最大和右边最大 maxL代表当前位置左边最大的列是多少
        // 动态转移方程 maxL[i] = Math.max(maxL[i - 1], height[i - 1])，比较当前左边最大和当前 --> 得到左边最大
        int len = height.length;
        int[] maxL = new int[len];
        int[] maxR = new int[len];
        for (int i = 1; i < len; i++) {
            maxL[i] = Math.max(maxL[i - 1], height[i - 1]);
        }
        for (int i = len - 2; i >= 0; i--) {
            maxR[i] = Math.max(maxR[i + 1], height[i + 1]);
        }
        // 寻找能存多少水
        int res = 0;
        for (int i = 0; i < len; i++) {
            int min = Math.min(maxL[i], maxR[i]);
            if (height[i] < min) {
                res += min - height[i];
            }
        }
        return res;
    }

    /*
    解法3：双指针
    是从上一个方法演变而来减小空间消耗，判断当前height[left] height[right]大小
    根据大小不同从左和从右遍历。若height[left]大，再判断当前的height[left]和之前maxLeft大小 找到左边最大
    判断是否比当前的列大 大的话就加上
    定理一：在某个位置i处，它能存的水，取决于它左右两边的最大值中较小的一个。
    定理二：当我们从左往右处理到left下标时，左边的最大值left_max对它而言是可信的，但right_max对它而言是不可信的
    定理三：当我们从右往左处理到right下标时，右边的最大值right_max对它而言是可信的，但left_max对它而言是不可信的
    对于位置left而言，它左边最大值一定是left_max，右边最大值“大于等于”right_max，这时候，如果left_max<right_max成立
    那么它就知道自己能存多少水了。无论右边将来会不会出现更大的right_max，都不影响这个结果。
     */
    public static int trap3(int[] height) {
        if (height == null || height.length < 2) return 0;
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int res = 0;
        while (left < right) {
            // 判断左边若比右边小的话 就不用去找右边的最大了.如果右边还有比height[right]大,存的水只取决于左边最大的位置
            if (height[left] < height[right]) {
                //判断是否比左边最大大
                if (height[left] < maxLeft) {
                    res += maxLeft - height[left];
                } else {
                    maxLeft = height[left];
                }
                left++;
            } else {
                if (height[right] < maxRight) {
                    res += maxRight - height[right];
                } else {
                    maxRight = height[right];
                }
                right--;
            }
        }
        return res;
    }

    /*
    用栈
    当遍历墙的高度的时候，如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。
    如果当前高度大于栈顶的墙的高度，说明之前的积水到这里停下，我们可以计算下有多少积水了。计算完，
    就把当前的墙继续入栈，作为新的积水的墙。
    总体的原则就是，1.当前高度小于等于栈顶高度，入栈，指针后移。
    2.当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2 步。
    直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移
     */
    public static int trap4(int[] height){
        // 找到能存多少水？通过定义current指针 遍历数组 若比栈顶小 说明还能继续存水入栈。
        // 比栈顶大 出栈计算存水量，怎么计算？将出栈后的 当前栈顶元素和current进行比较，
        // 若比栈顶大继续出栈直到栈为空或者找到比当前current大的元素，类似于找到出栈元素的左边最大和右边最大
        int len = height.length;
        int current = 0;
        int sum = 0;
        // 创建stack
        Stack<Integer> stack = new Stack<>();
        while (current < len) {
            // 判断栈顶, 构建单调递增栈
            while (!stack.isEmpty() && height[stack.peek()] < height[current]) {
                // 出栈元素
                Integer index = stack.pop();
                int val = height[index];
                // 出栈后判断栈是否为空
                if (stack.isEmpty()) {
                    break;
                }
                // 计算两个墙之间的距离
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[current], height[stack.peek()]);
                // 减去value是 以value这个位置为底 判断能存多少水
                sum += (min - val) * distance;
            }
            stack.push(current);
            current++;
        }
        return sum;
    }

}
