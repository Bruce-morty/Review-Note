package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/15
 * Version:1.0.0
 */
/*
46. 全排列
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]
 */
public class Medium46_Permutations {

    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        // 定义一个res数组用来添加要返回的list
        res = new ArrayList<>();
        //
        List<Integer> track = new LinkedList<>();
        backtrack(track, nums);
        return res;
    }

    private void backtrack(List<Integer> track, int[] nums) {
        // 边界条件 当前track已经添加过nums中的所有元素了
        if (track.size() == nums.length) {
        //       创建最后添加到结果的每一个路径path
            res.add(new ArrayList<>(track));
            // return 可以省略
            return;
        }

        // 回溯 核心遍历所有的选择
        for (int i = 0; i < nums.length; i++) {
            // 当前元素已经遍历过 找下一个元素
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            backtrack(track, nums);
            // 删除最后的元素
            track.remove(track.size() - 1);
        }
    }
}
