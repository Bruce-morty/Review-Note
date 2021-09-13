package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/7
 * Version:1.0.0
 */
/*
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
 */
public class DeleteDuplicate2 {

    /*
    思路：定义一个哨兵，然后比较当前节点和下一个节点。
    1. 若当前节点和一个节点值相等，就一直往下走 curr = curr.next
        直到不相等的时候，将前一个节点prev，连上curr.next. curr相等的之前已经删去了，curr还没删
        然后prev也往后移动
    2. 若不等，两个节点都往后移动
    3. 循环结束，返回哨兵的后一个节点
    caution：要一直删除的话，while遍历里面应该还有一个while
     */
    public static Node deleDuplicate(Node head) {
        if (head == null || head.next == null) return head;
        /*
    另一种方法：这个方法更好
    1. 定义哨兵，两个指针。一个指针提前移动，指向头节点的下一个节点
       判断a.next和b a.next是head
    2. 注意循环条件，第一个外层循环时b ！= null.
        if(head==null || head.next==null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode a = dummy;
        ListNode b = head.next;
        while(b!=null) {
            if(a.next.val!=b.val) {
                a = a.next;
                b = b.next;
            }
            else {
                while(b!=null && a.next.val==b.val) {
                    b = b.next;
                }
                // 这里b已经是不重复的元素了，a.next 是重复的，所以把它删了，连上b
                //a是dummy，现在连上的就是不相等的值，进入循环判断的是a.next 所以不用a = a.next
                a.next = b;
                //b指针在while中判断完后，可能指向了null，这里需要处理边界问题
                // 感觉不需要这一步，因为重新进入while循环会判断b，等于null的话直接退出while
                b = (b==null) ? null : b.next;
            }
        }
        return dummy.next;
    }
     */
        Node node = new Node(0, head);
        Node prev = node;
        Node curr = head;
        while(curr != null && curr.next != null) {
            if (curr.value != curr.next.value) {
                prev = prev.next;
                // curr = curr.next;
            }else {
                // 要循环判断当前和下一个节点的值
                while (curr.next != null && curr.value == curr.next.value) {
                    curr = curr.next;
                }
                // 把重复的节点包括当前curr删去，curr也要向后移动继续判断
                prev.next = curr.next;
            }
            // 一起放在最后移动
            curr = curr.next;
        }
        return node.next;
    }

}
