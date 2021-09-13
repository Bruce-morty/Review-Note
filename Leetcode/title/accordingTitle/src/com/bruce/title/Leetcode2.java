package com.bruce.title;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/10/15
 * Version:1.0.0
 */
public class Leetcode2 {
    /*
    根据两个链表将值相加
    为了匹配链表的长度，可以将链表为null 的部分进行加0操作
    只有两个连边都为null，才退出循环
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.
     */
    public static Node addNode(Node n1, Node n2) {
        Node curr = new Node(0);
        Node temp = curr;
        int carry = 0;
        while (n1 != null || n2 != null) {
            int x = n1 != null ? n1.value : 0;
            int y = n2 != null ? n2.value : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            int digits = sum % 10;
            temp.next = new Node(digits);
            // 有哨兵。不会空指针
            temp = temp.next;
            if (n1 != null) {
                n1 = n1.next;
            }
            if (n2 != null) {
                n2 = n2.next;
            }
        }
        if (carry != 0) {
            temp.next = new Node(carry);
        }
        return curr.next;
    }

    public static void main(String[] args) {
        Node n1 = new Node(9, null);
        n1 = new Node(9, n1);
        n1 = new Node(9, n1);
        Node n2 = new Node(1, null);
        Node node = addNode(n1, n2);
        while (node != null) {
            System.out.print(node.value + "-->");
            node = node.next;
        }
    }
}
