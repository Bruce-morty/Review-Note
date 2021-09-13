package com.bruce.title;

/**
 * Author: bruce
 * Date:2020/12/1
 * Version:1.0.0
 */
/*
给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

示例:
输入: nums = [-2,5,-1], lower = -2, upper = 2,
输出: 3
解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。

 */
public class Leetcode327 {
    /*
    考虑两个升序数组n1 n2，然后维护两个指针在一个数组中 x r，
    向右移动x，若 n2[x] >= n1[0] + lower 表示这个从x开始符合区间
    向右移动r, n2[r] > n1[0] + upper. 找到了n2的索引[x,r] 符合 n2[j] - n1[i] -> [lower, upper]
    记录这个下标，统计这个数量
    由于n1是有序的，遍历n1 从0开始一直到n1.length - 1 就找到全部的数量
     */
    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        int res = countRangeSum(nums, -2, 2);
        System.out.println(res);
    }
    public static int countRangeSum(int[] nums, int lower, int upper) {
        long s = 0;
        // 将数组的和 放进一个新数组，索引为0的位置为0，这样子可以自己与自己比较, n2需要比较当前索引值是否符合区间,
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public static int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            // n1的第一个元素肯定是0 因为传进来的sum第一个就是0，最后递归返回0.
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            // 这相当于计算在端点在n1和端点在n2的和的数量。下面计算左端点在n1 右端点在n2的数量
            int ret = n1 + n2;

            // 统计下标对的数量，i相当于n1的起始位置，从left开始往后遍历
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            // 因为第一个索引值是0，自己减0还是自己。n2自己判断自己当前索引值与lower和upper的大小
            while (i <= mid) {
                // 要记得判断条件与思考过程不同：n2[x] >= n1[0] + lower，只有小于lower说明sum还没进入区间 要++
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                // 小于等于upper sum 就还在区间里
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组，因为递归之后需要给这个sum排序。n1和n2需要是两个升序的数组才行
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }
}
