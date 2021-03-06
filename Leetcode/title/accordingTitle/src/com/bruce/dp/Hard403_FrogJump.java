package com.bruce.dp;

/**
 * Author: Qi Gao
 * Date:2021/5/24
 * Version:1.0.0
 */
/*
一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）
青蛙可以跳上石子，但是不可以跳入水中。
给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位
另请注意，青蛙只能向前方（终点的方向）跳跃。
示例 1：
输入：stones = [0,1,3,5,6,8,12,17]
输出：true
解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子,
然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子
跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
示例 2：

输入：stones = [0,1,2,3,4,8,9,11]
输出：false
解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 */
public class Hard403_FrogJump {
    // 思路：用dp取解决 判断能否到达最后一个石头
    // base case: 从第一个石头开始只能跳一步 状态：索引要怎么变
    // 选择：当前的boolean取决于 dp[j][k] j是前一个石子位置 k是石子能跳的范围
    // dp[i][k] : 当前i位置的石子，k = '上一次跳跃的距离'
    public static boolean canCross(int[] stones) {
        int len = stones.length;
        boolean[][] dp = new boolean[len][len];
        dp[0][0] = true;
        for (int i = 1; i < len; i++) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        // 计算dp值
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int temp = stones[i] - stones[j];
                // 我们可以从后向前枚举「上一次所在的石子编号」j
                // 当「上一次跳跃距离」k 超过了j+1, 时我们即可以停止跳跃 因为在第 j 个石子上我们至多只能跳出 j+1的距离
                if (temp > j + 1) {
                    break;
                }
                // 状态转移思路：知道跳到i要花temp步，但是不知道跳到'上一个石子j'花多少步，
                // 但是为了要确保跳到最后 跳到j的范围只能是[k - 1, k + 1] 只有这样最后才能跳k步到n - 1
                // 要跳到 n - 1这个位置，需要最后是刚好跳temp 到最后石子
                // 即dp[i][k] = 花了 k - 1步到j，在原来的步长上+1就能到i || 花了k步到j，再跳k步
                // || 花了k + 1步到j，再跳k - 1步就能到i
                dp[i][temp] = dp[j][temp - 1] || dp[j][temp] || dp[j][temp + 1];
                if (i == len - 1 && dp[i][temp]) {
                    return true;
                }
            }
        }
        return false;
    }
}
