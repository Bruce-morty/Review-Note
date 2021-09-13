package com.bruce.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/7/10
 * Version:1.0.0
 */
/*
930. 和相同的二元子数组
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组
子数组 是数组的一段连续部分
示例 1：
输入：nums = [1,0,1,0,1], goal = 2
输出：4
解释：
如下面黑体所示，有 4 个满足题目要求的子数组：
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
 */
public class Medium930_SubArray {

    /*
   一个简单的想法是，先计算 nums 的前缀和数组 sum，然后从前往后扫描 nums，对于右端点r 通过前缀和数组可以在 O(1)复杂度内求得
    [0, r][0,r] 连续一段的和，根据容斥原理，我们还需要求得某个左端点 ll，使得 [0, r]减去 [0, l - 1]和为 t，即满足
    sum[r] - sum[l - 1] = t，这时候利用哈希表记录扫描过的sum[i]的出现次数可以实现 O(1)复杂度内求得满足要求的左端点个数
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        // 用哈希表来解决 定义哈希表存储每次相加与goal相等时，val++，添加到res中
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // sum 用来统计这个数组的和
        int sum = 0;
        for (int num : nums) {
            // 每次进来前先得到前最后出现的次数 然后在当前基础上++
            int temp = map.getOrDefault(sum, 0);
            map.put(sum, temp + 1);
            sum += num;
            // 这一步判断sum[j] - goal = sum[i]出现的次数说明有几个子数组
            res += map.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    // 用滑动窗口
    /*
    思路：sum[j] - sum[i] = goal 的i总是落在一个区间，取区间中哪一个元素都满足。我们可以想到用滑窗
    找到符合区间的左右端点，定义两个窗口 右指针只有一个。
     */
    public int numSubarraysWithSum2(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
         while (right < n) {
             sum1 += nums[right];
             // 这一步使得 left1 还在goal的左区间
             while (left1 <= right && sum1 > goal) {
                 sum1 -= nums[left1];
                 left1++;
             }
             sum2 += nums[right];
             // 这一步使得left2 充当==goal的右区间 直到  < goal才不移动left2
             while (left2 <= right && sum2 >= goal) {
                 sum2 -= nums[left2];
                 left2++;
             }
             ret += left2 - left1;
             right++;
         }
         return ret;
    }
}
