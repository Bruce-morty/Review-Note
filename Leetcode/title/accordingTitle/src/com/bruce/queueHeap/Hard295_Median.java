package com.bruce.queueHeap;

import java.util.PriorityQueue;

/**
 * Author: Qi Gao
 * Date:2021/8/29
 * Version:1.0.0
 */
/*
295. 数据流的中位数
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
 */
public class Hard295_Median {
    //用優先隊列去求

    /*
    我们用两个优先队列 queMax 和 queMin 分别记录大于中位数的数和小于等于中位数的数。当累计添加的数的数量为奇数时，
    queMin 中的数的数量比 queMax 多一个，此时中位数为 queMin 的队头。当累计添加的数的数量为偶数时，
    两个优先队列中的数的数量相同，此时中位数为它们的队头的平均值。
    分情况讨论：
    1. num <= max{queMin}
    需要把这个数添加到 queMin中,新的中位数将小于等于原来的中位数，因此我们可能需要将 queMin中最大的数移动到 queMax 中
    2. num > max{queMin}
    添加到queMax, 大于原来的中位数,可能需要将queMax中的最小数放入到queMin中


     */
    /** initialize your data structure here. */
    // 一个堆存大于等于 median的数 另一个存小于等于median的值
    PriorityQueue<Integer> queMin;
    // 这是小顶堆，把最小的值放在堆顶进行比较
    PriorityQueue<Integer> queMax;

    public Hard295_Median() {
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }
}
