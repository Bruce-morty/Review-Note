package com.bruce.title;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: bruce
 * Date:2021/2/7
 * Version:1.0.0
 */
/*
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
请你找出所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：
输入：nums = []
输出：[]
示例 3：
输入：nums = [0]
输出：[]

 */
public class Leetcode15_ThreeSum {
    // 1.对数组进行排序 2. 从第一个数字开始遍历时 对第二第三个数字用双指针遍历(牢记去重)3.时间复杂度O(n^2) n为数组长度
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            // 当前大于 则和肯定大于0
            if (nums[i] > 0) break;
            // 如果元素重复 结束这次迭代
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 定义左右指针 对后面的元素遍历判断 直到left==right
            int left = i + 1;
            int right = len - 1;
            int target = nums[i];
            // while循环结束 判断完第一个数字i 接着继续下一轮for循环遍历数组
            while (left < right) {
                int sum = target + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 若后面的元素有与左指针和右指针相同 需要去重
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    // 越过当前元素
                    left++;
                    right--;
                } else if (sum > 0) {
                    // 说明右指针的数字太大需要--
                    right--;
                }else {
                    left++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        if (nums[0] > 0) return res;
        // 用for循环加两个指针做法遍历数组
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 每次都要记得去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 定义后面两个指针去判断和是否等于target
            int target = -nums[i];
            int third = len - 1;
            for (int j = i + 1; j < len; j++) {
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                while(third > j && nums[third] + nums[j] > target) {
                    third--;
                }
                if (j == third) break;
                // 判断target
                if (nums[j] + nums[third] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[third]));
                }
            }
        }
        return res;
    }
}
