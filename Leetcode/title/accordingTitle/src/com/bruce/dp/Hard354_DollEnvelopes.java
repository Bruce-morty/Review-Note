package com.bruce.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: bruce
 * Date:2021/3/4
 * Version:1.0.0
 */
/*
给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，
这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
说明:
不允许旋转信封。
输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出: 3
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class Hard354_DollEnvelopes {

    /*
    这道题其实是找LIS 最长递增子序列
    首先我们将所有的信封按照 w 值第一关键字升序、h 值第二关键字降序进行排序；
    随后我们就可以忽略 w 维度，求出 hh 维度的最长严格递增子序列，其长度即为答案
    若w相同 也不care了，由于高度是降序排列 我们只需要找到h的LIS 就是答案
    e.g
    1 5
    1 4
    1 2     *    以1位width的h 都是递减 最长递增是从 h = 2开始
    2 7
    2 4     *   7 - 4 也不是LIS
    3 5     *
    4 8     *
    w = 1 h = 4 虽然可以放入 w = 2 h = 7 里面 但是只能套一个
    4 -> 5 -> 8 只能套三个
    可以找到最长的递增子序列是：从 2 -> 4 -> 5 -> 8 这个可以套娃 4个
     */
    public int maxEnvelopes(int[][] envelopes) {
        // brute force
        int len = envelopes.length;
        // 宽度按升序排列 若宽度一样 高度降序排列
        Arrays.sort(envelopes, new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        // 对高度数组找LIS
        int[] height = new int[len];
        for (int i = 0; i < len; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthLIS(height);
    }

    /*
    二分查找：只有点数小的牌才能放到大的牌上面 如果不能放 则新开一个堆放最大的元素 然后继续遍历下一个
     */
    public int lengthLIS(int[] arr) {
        int piles = 0;
        int len = arr.length;
        int[] top = new int[len];
        for (int i = 0; i < len; i++) {
            // 要处理的扑克牌
            int poker = arr[i];
            int left = 0;
            int right = piles;
            // 二分查找插入的位置
            while(left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= poker) right = mid;
                else left = mid + 1;
            }
            if (left == piles) piles++;
            // 把这张牌放到堆顶
            top[left] = poker;
        }
        // 牌堆数就是LIS 长度
        return piles;
    }


    /*
    dp：
        同样要对整个二维数组进行排序
        1. 要找到最大的递增子序列的长度 若当前序列比前一个序列大
        可以求前一个序列的长度加一 和当前序列长度的大小
     */public int maxEnvelopesDP(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            // 知道这个数组每个位置上的最长递增序列 就需要两次loop 每次移动右指针时 是新的长度 要重新判断
            for (int j = 0; j < i; ++j) {
                // 如果小于说明j这个位置可以放进 f[i]中 所以f[j] 要 + 1
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
                // 若当前小于前一个 则跳过 说明形不成递增子序列
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
