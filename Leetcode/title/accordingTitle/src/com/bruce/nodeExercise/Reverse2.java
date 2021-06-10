package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/5
 * Version:1.0.0
 */
/*
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
 */
public class Reverse2 {

    /*
    1. 用递归来写，首先写一个能从头开始反转链表到 n这个索引位置，n后面的可能不为null，即n不是最后一个节点
        所以要定义一个变量保存后面一个节点
    2. 再写一个递归，将m的值递减到1，n也一直递减。最后将从头节点到 n的位置反转
     */
    public static Node reverseBetween(Node head, int m, int n) {
        if (m == 1) {
            return reverse(head, n);
        }
        // 将头节点移动到该反转的位置，m位置
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    // 创建成员变量
    // 也可以不创建
    static Node successor = null;

    private static Node reverse(Node head, int n) {
        // 递归到最后一个节点了，保存后一个节点，返回head
        if (n == 1) {
            successor = head.next;
            return head;
        }
        Node last = reverse(head.next, n - 1);
        // 保存后驱节点
        //  Node node = head.next.next;
        head.next.next = head;
        // 反转后的尾节点连上之前的
        // head.next = node;
        head.next = successor;
        return last;
    }

    public static Node reverseIterate(Node head, int m, int n) {
        // 迭代的思路 1. 定义prev和curr找到当前要反转的节点
        // 2. 相当于把m递减到1了，然后再定义两个指针去反转链表。
        // 3. 最后要判断头节点是不是null，连上链表
        if (head == null || head.next == null) return head;
        Node prev = null;
        Node curr = head;
        while (m > 1) {
            prev = curr;
            curr = curr.next;
            m--;
            n--;
        }
        // 找到了，再定义指针反转，当前的头节点等等是要连上curr的尾节点。判断n
        // 继续用curr 和 prev去移动反转链表。定义两个引用记录当前位置，反转后再连上链表
        Node tail = curr;
        Node front = prev;
        while (n > 1) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            n--;
        }
        // 判断front是不是null, 如果是的话，也就是从头开始反转。则遍历到后面的prev作为头节点
        if (front == null) {
            head = prev;
        } else {
            front.next = prev;
        }
        tail.next = curr;
        return head;
    }
}
