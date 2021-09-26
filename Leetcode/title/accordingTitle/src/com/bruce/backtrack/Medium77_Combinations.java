package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/9/23
 * Version:1.0.0
 */
/*
77. 组合
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合,你可以按 任何顺序 返回答案
示例 1：
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
public class Medium77_Combinations {

    // 用back track，和全排列不同的是 我们需要一个start index 传入下一个递归函数，因为如果不这样 就还是从0开始iterate
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        // backtrack 解决
        List<Integer> list = new ArrayList<>();
        backtrack(n, k, list, 1);
        return res;
    }

    public void backtrack(int n, int k, List track, int start) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        // iteration caution start
        // 剪枝优化，我们可以改变遍历条件确保当k = n时 后面遍历的数没意义
        // e.g n = 4, k = 4. i = start = 2, 添加不进去集合了
        // for (int i = start; i <= n - (k - track.size()) + 1； i++). 随着track的变化 可以取到n
        // e.g. track.size = k - 1; -> i <= n - (k - k + 1) + 1 -> i <= n
        for (int i = start; i <= n; i++) {
            // if (track.contains(i)) continue;
            track.add(i);
            backtrack(n, k, track, i + 1);
            track.remove(track.size() - 1);
        }
    }
}
