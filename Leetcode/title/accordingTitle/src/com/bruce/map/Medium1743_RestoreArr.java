package com.bruce.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/7/25
 * Version:1.0.0
 */
/*
存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi]
表示元素 ui 和 vi 在 nums 中相邻。
题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是
[nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可
示例 1：

输入：adjacentPairs = [[2,1],[3,4],[3,2]]
输出：[1,2,3,4]
解释：数组的所有相邻元素对都在 adjacentPairs 中。
特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
示例 2：

输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
输出：[-2,4,1,-3]
解释：数组中可能存在负数。
另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
 */
public class Medium1743_RestoreArr {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //记录每个元素的位置，第一个元素所和最后一个元素只会相邻一个元素
        for (int[] adjacent  : adjacentPairs) {
            map.putIfAbsent(adjacent[0], new ArrayList<Integer>());
            map.putIfAbsent(adjacent[1], new ArrayList<Integer>());
            map.get(adjacent[0]).add(adjacent[1]);
            map.get(adjacent[1]).add(adjacent[0]);
        }

        int n = adjacentPairs.length + 1;
        int[] ret = new int[n];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int i = entry.getKey();
            List<Integer> adj = entry.getValue();
            if (adj.size() == 1) {
                // 这是开头或者结尾的元素
                ret[0] = i;
                break;
            }
        }
        // caution! 得到当前第一元素相邻的值 进入loop
        ret[1] = map.get(ret[0]).get(0);
        for (int i = 2; i < n; i++) {
            // get 当前i - 1的adjacent 同时判断list中的第一个和第二个元素 哪个是与 i - 2相等 取另一个
            List<Integer> adj = map.get(ret[i - 1]);
            ret[i] = ret[i - 2] == adj.get(0) ? adj.get(1) : adj.get(0);
        }
        return ret;
    }
}
