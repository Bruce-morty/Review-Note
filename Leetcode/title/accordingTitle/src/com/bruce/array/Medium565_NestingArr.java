package com.bruce.array;

/**
 * Author: Qi Gao
 * Date:2021/9/1
 * Version:1.0.0
 */
/*
565. 数组嵌套
索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i],
 A[A[i]], A[A[A[i]]], ... }且遵守以下的规则
假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推
不断添加直到S出现重复的元素。
示例 1:
输入: A = [5,4,0,3,1,6,2]
输出: 4
解释:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
其中一种最长的 S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
constrains :
        N是[1, 20,000]之间的整数。
        A中不含有重复的元素。
        A中的元素大小在[0, N-1]之间。
 */
public class Medium565_NestingArr {

    /*
      通过限制条件我们知道，这个数肯定不会越界。只可能出现重复的元素，我们就可以利用这一点记录每个开始元素
      到出现重复元素的长度，比较大小
     */
    public int arrayNesting(int[] nums) {
        int len = nums.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            // 因为改变了数组的值 每次都先判断这个数是否合法
            if (nums[i] != Integer.MAX_VALUE) {
                // 记录当前元素作为下个元素的index
                int start = nums[i];
                int count = 0;
                // 我们用改变数组值的方法 确定有没有重复
                while (start < len && nums[start] != Integer.MAX_VALUE) {
                    int tmp = start;
                    start = nums[start];
                    count++;
                    nums[tmp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
