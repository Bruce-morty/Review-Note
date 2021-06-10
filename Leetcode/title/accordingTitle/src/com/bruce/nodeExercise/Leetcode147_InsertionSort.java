package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/21
 * Version:1.0.0
 */
/*
对链表进行插入排序
    1.分析 首先链表没有索引，只能通过节点移动进行遍历，定义两个引用 一个无序区 一个有序区
    2. 定义一个哨兵，当有序区大于无序区时需要从头遍历链表，找到前驱节点连上无序区的节点
    3. 然后将有序区的节点往后移动
 */
public class Leetcode147_InsertionSort {
    public static Node insertionSortList(Node head) {
        // 判断是否是空节点
        if(head == null) return head;
        Node dummy = new Node(0, head);
        // 定义有序区和无序区节点
        Node lastSorted = head;
        Node curr = head.next;
        while (curr != null) {
            // 判断无序区和有序区的元素大小
            if (lastSorted.value <= curr.value) {
                // 直接移动当前链表，并且由于curr就是lastSorted下一个节点， 可以不用再连上lastSorted.next = curr
                lastSorted.next = curr;
                lastSorted = lastSorted.next;
            } else {
                // 定义一个要插入元素的前驱 数组是从后往前遍历 链表从头遍历
                Node prev = dummy;
                while (prev.next.value <= curr.value) {
                    prev = prev.next;
                }
                // 找到了要插入的位置，之前的节点都比curr小 先把有序区的元素连上无序区的下一个节点，已经排好序了
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            // 将curr 移动到下一个位置, 前面的已经排好序了
            curr = lastSorted.next;
        }
        return dummy.next;
    }
}
