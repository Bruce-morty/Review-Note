package com.bruce.greedyAlgorithm;

/**
 * Author: Qi Gao
 * Date:2021/8/29
 * Version:1.0.0
 */
/*
330. 按要求补齐数组
给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，
使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数
示例 1:

输入: nums = [1,3], n = 6
输出: 1
解释:
根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数
所以我们最少需要添加一个数字
示例 2:
输入: nums = [1,5,10], n = 20
输出: 2
解释: 我们需要添加 [2, 4]
 */
public class Hard330_PatchingArra {
    // fill the array. 保证所有的数都能被cover到
    // 有这么一个式子 [1, x - 1] 已被cover 且x在数组中，则[1, 2x - 1]也被cover了
    /*
    对于任意 1≤y<x，y 已经被覆盖，x 在数组中，因此 y+x 也被覆盖，区间 [x+1,2x-1]（即区间 [1,x-1]内的每个数字加上 x之后
    得到的区间）内的所有数字也被覆盖，由此可得区间 [1,2x-1]内的所有数字都被覆盖
    若 1 - x-1 中的所有数字已被覆盖 从贪心角度考虑 充x之后即可覆盖到x 且满足补充的数字最少 补充x之后[1, 2x-1]都被cover
    每次找到未被数组覆盖到的最小整数 x 补充x，然后寻找下一个未被覆盖的最小整数，直到[1,n] 都被cover
     */
    public int minPatches(int[] nums, int n) {
        // caution 可能超过21亿 要用long类型
        long x = 1;
        int len = nums.length;
        // 设置index 遍历arr
        int index = 0;
        int pathchs = 0;
        while (x <= n) {
            if (index < len && nums[index] <= x) {
                // 说明这个数在范围内 不用添加新数 已经覆盖了 [1, 2x - 1] 区间
                x += nums[index];
                index++;
            } else {
                // 如果数组中的数都大于x 说明要补充x，同时更新到2x 位置, 因为 添加了x之后[1, 2x-1]已经被cover
                // 判断nums[index] 是否还大于x
                // 小于的话说明 在[1, x-1]区间里，大于的话还要继续补充x 再更新到2x
                x *= 2;
                pathchs++;
            }
        }
        return pathchs;
    }
}
