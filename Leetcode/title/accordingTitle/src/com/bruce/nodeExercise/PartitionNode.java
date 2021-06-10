package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/6
 * Version:1.0.0
 */
/*
给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
你应当保留两个分区中每个节点的初始相对位置。

示例:
输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
 */
public class PartitionNode {

    /*
    思路就是分割链表，创建两个dump节点，比x小的值放小的，大的放大的。
    注意的点：
            1. 由于small和big都是哨兵，还要多创建两个引用指向他们
            2. small最后会指向big，所以它不担心后一个节点的问题，但big最后要连上null，要注意这一点
     */
    public static Node partition(Node head, int x) {
        Node small = new Node(0, null);
        Node big = new Node(0, null);
        Node before = small;
        Node after = big;
        while (head != null) {
            if (head.value < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        // caution 注意big后面要连null
        big.next = null;
        small.next = after.next;
        return before.next;
    }

    public static void main(String[] args) {
        Node head = new Node(10, null);
        head = new Node(9, head);
        head = new Node(8, head);
        head = new Node(7, head);
        head = new Node(6, head);
        Node partition = partition(head, 7);
        System.out.println(partition);
    }
}
