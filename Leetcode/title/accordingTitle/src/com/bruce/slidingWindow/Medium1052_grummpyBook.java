package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/23
 * Version:1.0.0
 */
/*
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，
所有这些顾客都会在那一分钟结束后离开。
在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 
示例：
输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.

 */
public class Medium1052_grummpyBook {

    // 重点：转变思维 不是用滑动窗口。窗口已经固定 就是X。
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // 保证grumpy的连续0的个数最多 统计所有的0然后再去customer中添加顾客数量
        // 但需要判断customer中的顾客最多的时候 再去对应的grumpy中定义连续窗口
        // 假设老板不能控制情绪 计算满意量
        // 同时用temp记录损失了多少满意量  用窗口长度扫描temp 找到最大值 思想很重要!!!
        int[] temp = new int[customers.length];
        int count = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                count += customers[i];
            } else {
                temp[i] = customers[i];
            }
        }
        // 用窗口 扫描temp 看能挽回多少
        int max = 0;
        int recover = 0;
        // for (int i = 0; i < temp.length; i++) {
        //     if (i - X >= 0) {
        //         recover -= temp[i - X];
        //     }
        //     recover += temp[i];
        //     max = Math.max(max, recover);
        // }
        int left = 0;
        int right = 0;
        while (right < customers.length) {
            recover += temp[right];
            if (right - left + 1 == X) {
                max = Math.max(max, recover);
                recover -= temp[left];
                left++;
            }
            right++;
        }
        // max 是最大的了
        return max + count;
    }
}
