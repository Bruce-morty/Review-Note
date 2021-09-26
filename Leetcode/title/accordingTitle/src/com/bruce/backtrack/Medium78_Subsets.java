package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/9/23
 * Version:1.0.0
 */
/*
78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]
 */
public class Medium78_Subsets {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> track = new ArrayList<>();
        backtrack(nums, track, 0);
        return res;
    }

    /*
    子集和之前的不一样，子集收集的是所有的节点(且不能有重复)，其他的回溯问题是收集遍历到叶子的节点.
    其实子集也是一种组合问题，因为它的集合是无序的，子集{1,2} 和 子集{2,1}是一样的。
    那么既然是无序，取过的元素不会重复取，写回溯算法的时候，for就要从startIndex开始，而不是从0开始！
    有同学问了，什么时候for可以从0开始呢？求排列问题的时候，就要从0开始，因为集合是有序的，{1, 2} 和{2, 1}是两个集合
     */
    public void backtrack(int[] nums, List<Integer> track, int start) {
        // 所以一开始要先把track 添加到结果中
        res.add(new ArrayList<>(track));
        // edge case 是start是否到达nums末尾 到达说明遍历完一次了.
        if (start >= nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, track, i + 1);
            track.remove(track.size() - 1);
        }
    }
}
