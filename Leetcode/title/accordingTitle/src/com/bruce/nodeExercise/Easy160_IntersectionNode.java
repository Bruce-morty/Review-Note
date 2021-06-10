package com.bruce.nodeExercise;

import com.bruce.node.Node;

import java.util.HashSet;

/**
 * Author: Qi Gao
 * Date:2021/4/28
 * Version:1.0.0
 */
/*
给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。
换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，
链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

 */
public class Easy160_IntersectionNode {

    /*
    用双指针解决
    我变成你 走过你来时的路 若相遇则有相交
     */
    public static Node getIntersectionTwo(Node headA, Node headB) {
        Node l1 = headA;
        Node l2 = headB;
        // 若l1 == null 变成headB 继续往下走
        while (l1 != l2) {
            l1 = l1 != null ? l1.next : headB;
            l2 = l2 != null ? l2.next : headA;
        }
        // 若相交或不相交都会跳出loop l1 = l2 不是null 就是相交节点
        return l1;
    }

    public static Node getIntersectionNode(Node headA, Node headB) {
        // 用HashSet
        HashSet set = new HashSet();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
}
