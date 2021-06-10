package com.bruce.dp;

/**
 * Author: bruce
 * Date:2021/3/3
 * Version:1.0.0
 */
/*
给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:

输入: 2
输出: [0,1,1]
示例 2:

输入: 5
输出: [0,1,1,2,1,2]
 */
public class Medium338_BitCount {

    /*
    动态规划:
    方法二需要实时维护最高有效位，当遍历到的数是 2 的整数次幂时，需要更新最高有效位。
    如果再换一个思路，可以使用「最低有效位」计算「一比特数」。
    如果x是偶数 bit[x] = bit[x/2];
       x是奇数 bit[x] = bit[x/2] + 1;
    可以通过 x>>1 得到，x 除以 2 的余数可以通过 x&1 得到，因此有：bits[x]=bits[x>>1]+(x&1)。
     */
    public int[] countBits(int num) {
        // brute force
        int[] arr = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            // 将i转为binary 然后计算1的个数
            // arr[i] = countOne(i);
            // dp 偶数位和奇数位的一比特数差一
            arr[i] = arr[i >> 1] + (i & 1);
        }
        return arr;
    }

    public int countOne(int x) {
        // 计算这个数字有几个1 即用位运算 判断这个数操作了几次
        int count = 0;
        while (x > 0) {
            // 这个运算将这个数字的最后一个1变为0 每一次计算都会少将最后一个1去掉 最后所有1都不见了只剩0
            x &= (x - 1);
            count++;
        }
        return count;
    }
}
