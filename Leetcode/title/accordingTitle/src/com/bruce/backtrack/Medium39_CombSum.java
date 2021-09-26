package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/9/23
 * Version:1.0.0
 */
/*
The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.
It is guaranteed that the number of unique combinations that sum up to target is less than 150
combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
 */
public class Medium39_CombSum {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> track = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0);
        return res;
    }

    /*
    本题还需要startIndex来控制for循环的起始位置，对于组合问题，什么时候需要startIndex呢？
    举例子，如果是一个集合来求组合的话，就需要startIndex，例如：回溯算法：求组合问题(77)回溯算法：求组合总和(216)
    如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex，例如：回溯算法：电话号码的字母组合
     */
    public void backtrack(int[] num, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(track));
            return;
        }
        // iterate the nums
        for (int i = start; i < num.length; i++) {
            track.add(num[i]);
            // because of we can choose duplicated elements, start is from i not i + 1
            backtrack(num, target - num[i], i);
            track.remove(track.size() - 1);
        }
    }
}
