package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/21
 * Version:1.0.0
 */
/*
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

输入：head = [4,2,1,3]
输出：[1,2,3,4]
 */
public class Leetcode148 {
    // 分析，适合链表的是归并排序，将他们分成一个个节点再比较大小归并
    public static Node sortList(Node head) {
        // 1. 用快慢指针找到中间节点，分割左右两边，递归地把他们变成一个节点
        // 2. 然后归并两个返回的节点，最后返回归并后的节点
        if (head == null ||head.next == null) return head;
        Node slow = head;
        // 为什么fast 要先走一步？这样才能找到中点 如果不先走会stackOVerFlow
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 当fast == null slow 已经走到中间，从这开始分割
        Node temp = slow.next;
        slow.next = null;
        // 在这里递归，一直到剩一个节点为止
        Node left = sortList(head);
        Node right = sortList(temp);
        // 归并
        Node mergeNode = merge(left, right);
        return mergeNode;
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node(0);
        Node temp = dummy;
        // 用到合并两个有序链表的知识，以前做过
        while (left != null & right != null) {
            if (left.value <= right.value) {
                dummy.next = left;
                dummy = dummy.next;
                left = left.next;
            } else {
                dummy.next = right;
                dummy = dummy.next;
                right = right.next;
            }
        }
        dummy.next = left != null ? left : right;
        return temp.next;
    }

    /*
    用自底向上的方式进行归并排序，空间复杂度 O(1)
    递归调用栈的深度导致 空间复杂度为 O(log n) n 为递归的次数，可以用迭代的方式改进
     */
    public static Node sortList2(Node head) {
        // 1. 需要找出链表的长度，然后将整个链表分成一个个节点，在将前后节点归并
        // 2. 每归并一次 有序的节点长度从 1 * 2 ....
        if (head == null) return null;
        Node node = head;
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        // 定义一个引用 哨兵, 当子序列的长度大于链表长度 就排序完成了
        Node dummy = new Node(0, head);
        for (int subLength = 1; subLength < len; subLength *= 2) {
            Node curr = dummy.next;
            Node prev = dummy;
            // 将所有的链表分成一个个节点
            while(curr != null) {
                // 判断左边链表
                Node headLeft = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                Node headRight = curr.next;
                // 这样左边的链表就断开了右边, 同时将右边头节点赋值给curr 下面开始判断右边
                curr.next = null;
                curr = headRight;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                Node temp = null;
                if (curr != null) {
                    // 一开始是判断前两个节点，然后保存之后节点，进入while 两两归并后面的节点
                    // 说明右边还有节点没有判断，只因为小于subLength这个条件退出，保存右边的节点继续判断
                    temp = curr.next;
                    curr.next = null;
                }
                // 归并了之后 移动哨兵，为了之后继续链接节点
                Node mergeNode = merge(headLeft, headRight);
                prev.next = mergeNode;
                while (prev.next != null) {
                    prev = prev.next;
                }
                // 将后面节点值赋给curr 继续判断
                curr = temp;
            }
        }
        return dummy.next;
    }

}
