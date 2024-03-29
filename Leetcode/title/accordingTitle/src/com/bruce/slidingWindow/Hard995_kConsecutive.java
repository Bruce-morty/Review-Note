package com.bruce.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2021/2/19
 * Version:1.0.0
 */
/*
在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，
同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
示例 1：
输入：A = [0,1,0], K = 1
输出：2
解释：先翻转 A[0]，然后翻转 A[2]。
示例 2：
输入：A = [1,1,0], K = 2
输出：-1
解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
示例 3：
输入：A = [0,0,0,1,0,1,1,0], K = 3
输出：3
解释：
翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
 */
public class Hard995_kConsecutive {

    /*
    考虑不去翻转数字，而是统计每个数字需要翻转的次数。对于一次翻转操作，相当于把子数组中所有数字的翻转次数加1。
    这启发我们用差分数组的思想来计算当前数字需要翻转的次数。我们可以维护一个差分数组diff，
    其中diff[i] 表示两个相邻元素 A[i-1] 和 A[i] 的翻转次数的差，对于区间 [l,r]将其元素全部加 1，
    只会影响到 l 和 r+1 处的差分值，故 diff[l] 增加 1，diff[r+1] 减少 1
    通过累加差分数组可以得到当前位置需要翻转的次数，我们用变量 revCnt 来表示这一累加值。
    遍历到 A[i] 时，若 A[i]+revCnt 是偶数 则说明当前元素的实际值为 0需要翻转区间 [i,i+K-1]
    我们可以直接将revCnt 增加 1，diff[i+K] 减少 1
    注意到若 i+K > n 则无法执行翻转操作，此时应返回 -1。
     */
    public static int minKBitFlips(int[] A, int K) {
        int n = A.length;
        // 定义差分数组 diff[i]示 A[i] - A[i-1] 反转的次数的差
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            // 每次循环进来前要累加差分的值 一开始所有的差分都是0
            revCnt += diff[i];
            // 判断是否需要反转
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                // 反转后 需要将后面区间的端点的值减少 因为已经累加到rev上了
                --diff[i + K];
            }
        }
        return ans;
    }

    /*
    滑动窗口
    我们使用队列模拟滑动窗口，该滑动窗口的含义是前面 K - 1个元素中，以哪些位置起始的 子区间 进行了翻转
    该滑动窗口从左向右滑动，如果当前位置 i 需要翻转，则把该位置存储到队列中。遍历到新位置 j (j < i + K) 时
    队列中元素的个数代表了 i 被前面 K - 1 个元素翻转的次数。
    当 A[i]为 0，如果 i 位置被翻转了偶数次，那么翻转后仍是 0，当前元素需要翻转；
    当 A[i] 为 1，如果 i 位置被翻转了奇数次，那么翻转后变成 0，当前元素需要翻转。
    综合上面两点，我们得到一个结论，如果 len(que) % 2 == A[i] 时，当前元素需要翻转。
    当 i + K >N 时，说明需要翻转大小为 K 的子区间，但是后面剩余的元素不到 K 个了，所以返回 -1。
    */
    public int minKBitFlips2(int[] A, int K) {
        int res = 0;
        Deque<Integer> que = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            // 若当前index 大于 队头的index + K - 1  需要移动窗口指针
            if (que.size() > 0 && i > que.peek() + K - 1) {
                que.removeFirst();
            }
            //1.本来是1，翻转奇数次变为0，所以需要再次翻转，放入队列
            //2.本来是0，翻转偶数次还是0，所以需要再次翻转，放入队列
            if (que.size() % 2 == A[i]) {
                if (i + K > A.length) return -1;
                que.add(i);
                res += 1;
            }
        }
        return res;
    }
}
