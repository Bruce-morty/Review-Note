package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/8/5
 * Version:1.0.0
 */
/*
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 示例 1：
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

 */
public class Medium47_Per2 {

    // backtrack 需要剪枝判断 如果顺序相同则可知道重复
    List<List<Integer>> res;
    boolean[] check;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length < 1) return res;
        check = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, path, 0);
        return res;
    }

    public void backtrack(int[] nums, List path, int index) {
        if (index == nums.length) {
            res.add(new ArrayList(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 因为有重复的数字 不能直接这么判断
            // if (path.contains(nums[i])) continue;
            // 注意!check[i] 为什么这样剪枝更好
            if (check[i] || (i > 0 && nums[i] == nums[i - 1] && !check[i - 1])) continue;
            path.add(nums[i]);
            check[i] = true;
            // 回溯 这里用index + 1与nums的length比较
            backtrack(nums, path, index + 1);
            check[i] = false;
            path.remove(index);
        }
    }
}
