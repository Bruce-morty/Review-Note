package com.bruce.greedyAlgorithm;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/8/26
 * Version:1.0.0
 */
/*
881. 救生艇
第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
示例 1：

输入：people = [1,2], limit = 3
输出：1
解释：1 艘船载 (1, 2)
示例 2：

输入：people = [3,2,2,1], limit = 3
输出：3
解释：3 艘船分别载 (1, 2), (2) 和 (3)
 */
public class Medium881_BoatsToSave {

    /*
    思路：用贪心实现，先对数组排序。
    1. 若最轻的人和最重的人重量超过limit，单独给最重的人分配一条船, ans++
    2. 若最轻和最重的人重量小于等于limit，他们两个都被分配到了。ans++
     */
    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int light = 0;
        int heavy = people.length - 1;
        while (light <= heavy) {
            // 判断最重的人加最轻的是否能在一条船上
            int sum = people[light] + people[heavy];
            /*
            if (sum <= limit) {
                light++;
            }
            heavy--;
            ans++;
             */
            if (sum > limit) {
                // 单独给heavy分配一条船 ans++
                heavy--;
                ans++;
            } else {
                // 最轻的人可以和最重的人一起搭船 light++ heavy--; ans++;
                light++;
                ans++;
                heavy--;
            }
        }
        return ans;
    }
}
