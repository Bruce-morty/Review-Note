package com.bruce.greedyAlgorithm;

/**
 * Author: bruce
 * Date:2021/2/7
 * Version:1.0.0
 */
/*
给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
示例 1:
输入: nums = [4,2,3]
输出: true
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
示例 2:
输入: nums = [4,2,1]
输出: false
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 */
public class Leetcode665 {
    public static boolean checkPossibility(int[] nums) {
        // 优化 若nums[i + 1] 被替换成 nums[i]了 判断 nums[i + 1] 是否小于 Nums[i - 1] 小于则 说明不是非递减
        // 要将 i+1 改成 i的值这样才能是非递减
       /* int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            int x = nums[i];
            int y = nums[i+1];
            if (x > y) {
                count--;
                if (count < 0) return false;
                if (i > 0 && y < nums[i - 1]) {
                    // 可知前面肯定不是递减 只能修改num[i+1]
                    nums[i + 1] = x;
                }
                // 后面若不是非递减 count++ > 1 return false
            }
        }
        return true;*/
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            // 判断 若修改了则count++
            if (x > y) {
                count++;
                if (count > 1) return false;
                // 优化判断：如果y > nums[i-1] 则说明只改i这个位置的值就符合了 因为y的值只小于Nums[i] 前面都是非递减
                //用了一次修改 只是隐含没有修改i这个元素的值 但count数+1了 遍历后面数组如果有不满足非递减
                // 则count > 1 false
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
            // 若没修改i+1的值 则说明i+1仅小于i的值 就看后面的是否都不小于i + 1就好
            // 修改之后 遍历判断 i+1 i+2 .。。len-1 的值，若中间有小于前面的值 则不能修改了返回false
            // 重点是用一个计数隐含将某个元素修改了 修改元素需要保证前面的都是非递减才行 根据这个进行优化赋值操作
        }
        return true;
    }

    public boolean checkPossibility2(int[] nums) {
         int left = 0;
         for (int i = 0; i < nums.length - 1; i++) {
             int x = nums[i];
             int y = nums[i + 1];
             if (x > y) {
                 // 改变两次的原因：贪心算法 将i 改成 i +1的值 判断是否是非递减 符合直接返回 不符合就复原 这修改1次作废
                 nums[i] = y;
                 if (isSort(nums)) {
                     return true;
                 }
                 // 如果是false 可以知道i + 1的值放在i不符合(修改1次)将数组复原(修改0次)
                 // 再将前面最大的数x赋值给i+1（修改1次） 继续判断 如果false 说明后面有不符合的 不能修改了 返回false
                 nums[i] = x;
                 nums[i + 1] = x;
                 return isSort(nums);
             }
         }
         return true;
    }

    public boolean isSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i+1]) {
                return false;
            }
        }
        return true;
    }

}
