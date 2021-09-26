package com.bruce.title;

import java.util.Arrays;
import java.util.Random;

/**
 * Author: bruce
 * Date:2021/1/22
 * Version:1.0.0
 */
/*
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
<<<<<<< HEAD
=======

>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释：
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 */
public class Leetcode973 {

    // 快速选择的思想 时间复杂度 最好情况: O(n) n是数组的长度 最坏情况o(n2) 需要划分 n - 1次
    static Random rand = new Random();

    public static int[][] kCloset(int[][] points, int k) {
        int n = points.length;
        // 这里right 传的是索引 不是length
        randomSelect(points, 0, n - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private static void randomSelect(int[][] points, int left, int right, int k) {
        // 随机定义pivot
        int pivotId = left + rand.nextInt(right - left + 1);
        int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
        // 交换 pivot和最右元素，对数组排序后 将pivot放在i的位置
        swap(points, pivotId, right);
        // 设 i指针，比pivot小的都在 i 左边, j从left索引开始遍历数组 (我之前写 j = 0)
        int i = left - 1;
        for (int j = left; j < right; j++) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            // 如果小于pivot i++ 移动指针 如果大于的话就不动 让j继续遍历下一个一维arr 找到小于pivot再交换
            if (dist <= pivot) {
                i++;
                swap(points, i, j);
            }
        }
        // 退出循环将pivot 放到i的位置
        i++;
        swap(points, i, right);
        // 对快排进行递归 k的个数如果在分区的左边就在左边找
        if (k < i - left + 1) {
            randomSelect(points, left, i - 1, k);
        } else if (k > i - left + 1) {
            randomSelect(points, i + 1, right, k - (i - left + 1));
        }
    }

    private static void swap(int[][] points, int pivot, int right) {
        int[] temp = points[pivot];
        points[pivot] = points[right];
        points[right] = temp;
    }

    public static void main(String[] args) {
        int[][] test = {{3,3}, {5, -1}, {-2, 4}};
        int k = 1;
        int[][] testArr = kCloset(test, k);
        System.out.println(Arrays.deepToString(testArr));
    }
}
