package com.bruce.dp.knapsack;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/9/6
 * Version:1.0.0
 */
/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i],
obtaining a profit of profit[i].
You're given the startTime, endTime and profit arrays, return the maximum profit you can take
such that there are no two jobs in the subset with overlapping time range.
If you choose a job that ends at time X you will be able to start another job that starts at time X.
Example 1:

Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Example 3:

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 */
public class Hard1235_MaxProfit {
    /*
    思路：用动态规划，以endTime对数组从低到高排列，然后找到startTime > endTime 的index profit加入到dp[index]
    dp[i]: 第i份工作 可以挣到最大多少钱.
    dp[i] = max(dp[i - 1], profit[i]); dp[i] = max(dp[i], dp[k] + profit[i]) i的startTime > k endTime
    Time Complexity: O(n ^2) : 我们需要对数组循环遍历两轮，才能找到dp[i]
    Space Complexity: O(n) 新建了一个dp数组保存状态
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;
        Job[] jobs = new Job[length];
        for (int i = 0; i < length; i++) {
            Job job = new Job(startTime[i], endTime[i], profit[i]);
            jobs[i] = job;
        }
        Arrays.sort(jobs, (j1, j2) -> (j1.end - j2.end));
        int[] dp = new int[length];
        //初始化的利益就是只做当前这份工作的利益
        for (int i = 0; i < length; i++) {
            dp[i] = jobs[i].profit;
        }
        for (int i = 1; i < length; i++) {
            //1.先找出dp[i-1],和profit[i]的利益最大值。得出第一步dp[i]
            // e.g. 若 i - 1 的startTime和end : [1,3]. i的start end : [2,4] 选i - 1和 i 的profit最大值
            dp[i] = Math.max(dp[i - 1], jobs[i].profit);
            //2.再找出离i最近的不重合的j，取dp[j]+profit[i],第一步的dp[i]，两者之间的最大值
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[j].end <= jobs[i].start) {
                    //第一次找到j时得出的最大值，一定是dp[i]的最大值，
                    // 再往更小的j取寻找得到的都不是最大值，所以此处break。
                    dp[i] = Math.max(dp[j] + jobs[i].profit, dp[i]);
                    break;
                }
            }
        }
        return dp[length - 1];
    }

    class Job {
        int start;
        int end;
        int profit;

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

}
