package com.bruce.greedyAlgorithm;

/**
 * Author: Qi Gao
 * Date:2021/9/18
 * Version:1.0.0
 */
/*
292. Nim 游戏
你和你的朋友，两个人一起玩 Nim 游戏：

桌子上有一堆石头。
你们轮流进行自己的回合，你作为先手。
每一回合，轮到的人拿掉 1 - 3 块石头。
拿掉最后一块石头的人就是获胜者。
假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。
如果可以赢，返回 true；否则，返回 false 。
示例 1：
输入：n = 4
输出：false
解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
示例 2：
输入：n = 1
输出：true
 */
public class Easy292_NimGame {

    /**
     假设共有m回合，第i回合开始时剩余last[i]颗石头，
     (1) 第m回合，我拿，胜利，那么m必须为奇数，且1<=last[m]<=3
     (2) 第m-1回合，对方拿，必须last[m-1]=4，才能保证1<=last[m]<=3
     (3) 第m-2回合，我拿，必须5<=last[m-2]<=7，才能保证last[m-1]=4
     (4) 第m-3回合，对方拿，同理(2)，last[m-3]=8
     (5) 第m-4回合，同理(3)，9<=last[m-5]<=11
     ……
     也就是说，凡是轮到我的回合里，石头数量k只要不是4的倍数，我每次只需要拿走k%4颗石头，那么我必赢。
     反之，如果轮到我的回合里，石头数量k是4的倍数，那么无论我怎么拿，轮到对方时石头数量都不用是4的倍数，则对方必赢。
     */
    public boolean canWinNim(int n) {
        // 贪心算法
        return n % 4 != 0;
    }
}
