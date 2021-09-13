package com.bruce.binarySearchTree.buildTree_recover;

import com.bruce.node.Node;
import com.bruce.node.TreeNode;

/**
 * Author: Qi Gao
 * Date:2021/9/1
 * Version:1.0.0
 */
/*
109. 有序链表转换二叉搜索树
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
示例:

给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class Medium109_LinkedListToTree {

    /*
    与上一题一样，要构建一个高度尽量平衡的树 我们可以选择中间节点作为根节点，中序遍历尽可能左右子树的差值最小
     */
    public TreeNode sortedListToBST(Node head) {
        // 用中序遍历build tree
        if (head == null) return null;
        TreeNode node = dfs(head, null);
        return node;
    }

    /*
    链表的难点在于怎么找mid，用快慢指针 注意边界和循环条件
     */
    public TreeNode dfs(Node head, Node tail) {
        // 边界条件想清楚 为什么头尾相等直接返回null 头尾相等说明不用在连上子树了, 返回null给上一层作为子树
        if (head == tail) return null;
        // 找到中间节点
        Node slow = head;
        Node fast = head.next;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow == mid
        TreeNode root = new TreeNode(slow.value);
        // 对头尾节点递归
        root.left = dfs(head, slow);
        root.right = dfs(slow.next, tail);
        return root;
    }

    /*
    分治法 一样的
    时间复杂度：O(nlogn)，其中n 是链表的长度。
    设长度为 n 的链表构造二叉搜索树的时间为 T(n)，递推式为 T(n) = 2⋅T(n/2)+O(n)，根据主定理，T(n) = O(nlogn)

    空间复杂度：O(logn)，这里只计算除了返回答案之外的空间
    平衡二叉树的高度为 O(logn)，即为递归过程中栈的最大深度，也就是需要的空间。

     */
    public TreeNode sortedListToBST2(Node head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(Node left, Node right) {
        if (left == right) {
            return null;
        }
        // 与之前写的 108 一样 得到根 构建root 然后递归地去连接左右子树
        Node mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.value);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    public Node getMedian(Node left, Node right) {
        Node fast = left;
        Node slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /*
    方法3：分治 + 中序遍历优化
    方法1 和 2的时间复杂度的瓶颈在于寻找中位数节点。由于构造出的二叉搜索树的中序遍历结果就是链表本身，
    因此我们可以将分治和中序遍历结合起来，减少时间复杂度。
    具体地，设当前链表的左端点编号为 left，右端点编号为 right，包含关系为「双闭」，即 left 和 right 均包含在链表中。
    链表节点的编号为 [0, n)[0,n)。中序遍历的顺序是「左子树 - 根节点 - 右子树」，那么在分治的过程中，
    我们不用急着找出链表的中位数节点，而是使用一个“占位节点”，等到中序遍历到该节点时，再填充它的值。
    我们可以通过计算编号范围来进行中序遍历：
    中位数节点对应的编号为 mid=(left+right+1)/2；根据方法一中对于中位数的定义，
    编号为(left+right)/2 的节点同样也可以是中位数节点。左右子树对应的编号范围分别为[left,mid−1]和[mid+1,right]。
    如果left>right，那么遍历到的位置对应着一个空节点，否则对应着二叉搜索树中的一个节点。
     */

    /*
    time complexity : O(n) n是链表的长度
    设长度为 n 的链表构造二叉搜索树的时间为 T(n)，递推式为 T(n) = 2⋅T(n/2)+O(n)，根据主定理，T(n) = O(n)
    Space Complexity: O(logn) 递归的栈的深度
     */
    Node globalHead;

    public TreeNode sortedListToBST3(Node head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(Node head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        // 新建一个占位的node
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        // 一开始等于head的值，到后面global 一直往下走
        root.value = globalHead.value;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }
}
