package com.bruce.greedyAlgorithm;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
夏日炎炎，小男孩 Tony 想买一些雪糕消消暑
商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格
Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量
注意：Tony 可以按任意顺序购买雪糕
示例 1：

输入：costs = [1,3,2,4,1], coins = 7
输出：4
解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
 */
public class Medium1833_MaxIceCream {
    /*
    由此可以得到贪心的解法：对数组 costs 排序，然后按照从小到大的顺序遍历数组元素，
    对于每个元素，如果该元素不超过剩余的硬币数，则将硬币数减去该元素值，表示购买了这支雪糕，
    当遇到一个元素超过剩余的硬币数时，结束遍历，此时购买的雪糕数量即为可以购买雪糕的最大数量。
     */
    public int maxIceCream(int[] costs, int coins) {
        int len = costs.length;
        Arrays.sort(costs);
        // 排序之后 从前往后买 即可得到贪心结果 也是最优解 买价格低的雪糕对coins的消耗小 对数量的贡献与价格高的一样 都是1
        // 定义买到的雪糕数量
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (coins >= costs[i]) {
                coins -= costs[i];
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
