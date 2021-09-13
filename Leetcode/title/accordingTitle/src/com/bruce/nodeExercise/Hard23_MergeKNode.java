package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: Qi Gao
 * Date:2021/6/17
 * Version:1.0.0
 */
/*
给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：
输入：lists = []
输出：[]
 */
public class Hard23_MergeKNode {
    // 暴力法: 构建两个链表排序的方法，然后写一个循环把所有链表组合成一个
    public Node mergeKLists(Node[] lists) {
        // 对list中的node都调用merge 方法 最后都是升序
        int len = lists.length;
        if (len < 1) return null;
        Node res = lists[0];
        for (int i = 1; i < len; i++) {
            res = merge(res, lists[i]);
        }
        return res;
    }

    public Node merge(Node h1, Node h2) {
        // 构建dummy
        Node dummy = new Node(-1);
        Node temp = dummy;
        while (h1 != null && h2 != null) {
            // 小于等于都一样
            if (h1.value <= h2.value) {
                temp.next = h1;
                temp = temp.next;
                h1 = h1.next;
            } else {
                temp.next = h2;
                temp = temp.next;
                h2 = h2.next;
            }
        }
        temp.next = h1 != null ? h1 : h2;
        return dummy.next;
    }

    /*
    分治法合并链表
    将k个node 分成 k/2份，然后对里面的node排序合并
    最后合并成1个链表
     */

    public Node mergeKlikst2(Node[] lists) {
        return merge2(lists, 0, lists.length - 1);
    }

    // 进行合并操作
    public Node merge2(Node[] list, int l, int r) {
        // 若左指针==右 直接返回当前node
        if (l == r) return list[l];
        if (l > r) return null;
        // 或者 mid = l + ((r - l) >> 1);
        int mid = (l + r) >> 1;
        // 分治操作，将list分成两个 最后合成一个
        Node n1 = merge2(list, l, mid);
        Node n2 = merge2(list, mid + 1, r);
        return mergeTwoList(n1, n2);
    }

    /*
    将两个链表比较合并的操作
     */
    private Node mergeTwoList(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return n1 != null ? n1 : n2;
        }
        // 构建dummy去结合list、
        Node head = new Node(-1);
        Node tmp = head;
        Node l1 = n1;
        Node l2 = n2;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = l1 != null ? l1 : l2;
        return tmp.next;
    }

}
