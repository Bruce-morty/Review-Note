package com.bruce.binarySearchTree.buildTree_recover;

import com.bruce.node.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/9/2
 * Version:1.0.0
 */
/*
99. 恢复二叉搜索树
给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。

进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？ Moris 遍历
示例 1：
输入：root = [1,3,null,null,2]
输出：[3,1,null,null,2]
解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 */
public class Medium99_RecoverBST {

    // 方法二 隐式的中序遍历：
    /*
    time complexity: O(N) 我们需要遍历整颗树 N为二叉树的节点个数
    Space Complexity O(H) H为二叉树的高度，栈的深度取决于高度
     */
    /*
    方法一是显式地将中序遍历的值序列保存在一个nums 数组中 然后再去寻找被错误交换的节点，但我们也可以隐式地在中序遍历的过程就
    找到被错误交换的节点 x 和 y。
    具体来说，由于我们只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，且错误交换后最多两个位置不满足条件，
    因此在中序遍历的过程我们只需要维护当前中序遍历到的最后一个节点pred，然后在遍历到下一个节点的时候，
    看两个节点的值是否满足前者小于后者即可，如果不满足说明找到了一个交换的节点，且在找到两次以后就可以终止遍历。
    这样我们就可以在中序遍历中直接找到被错误交换的两个节点 x 和 y，不用显式建立 nums 数组。
    中序遍历的实现有迭代和递归两种等价的写法，在本方法中提供迭代实现的写法。使用迭代实现中序遍历需要手动维护栈。
     */
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.value < pred.value) {
                // y是后一个节点值 且 root位置在pred后面 把x设为pred ->较大节点
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.value;
        x.value = y.value;
        y.value = tmp;
    }


    /*
    可能有两种情况：情况一：如果前后两个节点交换，则只用交换一次就变回正常。
                情况二：当前节点和很后面的节点交换，则记录的节点为2.
    中序遍历：从小到大，找到第一个不符合的记录下来 前一个较大值和后一个较小值，若后面再次遇到后一个小于前一个，记录后一个
            与前面的较大值交换！结束！重点是有可能遇到两次后一个小于前一个的情况
     */
    // 方法一 用中序遍历记录下原BST位置，再找到可能错误的位置，交换这两个位置

    /*
    情况一：要记录两个可能变换的量，找到第一个，若前一个大于后一个，说明这个节点是需要被交换的。
    y记录后一个位置的变量。然后把x设成前一个位置。若没有找到第二个错误的位置，只需要交换x和y
    position 2：找到了第二个需要交换的节点，将y设置成较小节点的值 与之前较大的x值交换
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        dfs(root,list);
        TreeNode x = null;
        TreeNode y = null;
        //扫面遍历的结果，找出可能存在错误交换的节点x和y
        for(int i=0;i<list.size()-1;++i) {
            if(list.get(i).value>list.get(i+1).value) {
                y = list.get(i+1);
                if(x==null) {
                    x = list.get(i);
                }
            }
        }
        //如果x和y不为空，则交换这两个节点值，恢复二叉搜索树
        if(x!=null && y!=null) {
            int tmp = x.value;
            x.value = y.value;
            y.value = tmp;
        }
    }

    //中序遍历二叉树，并将遍历的结果保存到list中
    private void dfs(TreeNode node,List<TreeNode> list) {
        if(node==null) {
            return;
        }
        dfs(node.left,list);
        list.add(node);
        dfs(node.right,list);
    }


    /*
    用Morris遍历去实现，遍历的同时保存pre节点判断是否出错可以保证space complexity为O(1)
    Time complexity: O(n) 每个节点会被访问两次 O(2n) = O(n)
    morris就是找到节点的最右节点，
    Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
    0.如果 x 无左孩子，则访问 x 的右孩子，即 x=x.right。
    1.如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），
    2.我们记为 predecessor 根据 predecessor 的右孩子是否为空，进行如下操作
    如果predecessor的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
    如果predecessor的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，我们将predecessor的右孩子置空
    然后访问 x 的右孩子，即 x=x.right。
    3.重复上述操作，直至访问完整棵树。
     */

    public void recoverTree3(TreeNode root) {
        //记录错误的两个值
        TreeNode x = null, y = null;
        //记录中序遍历当前节点的前驱
        TreeNode pre = null;
        //用来完成Morris连接的寻找前驱的指针
        TreeNode predecessor = null;
        while(root != null) {
            if(root.left != null) {//左子树不为空，1、链接root节点的前驱，他的前驱还没访问，所以root不能现在访问,
                // 继续访问root左子树  2、root节点访问,并且断开root节点的前驱连接，然后访问root的右子树
                predecessor = root.left;
                // Morris 找到左子树的最右节点
                while(predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == root) {// 已把右子树链接到root, 1已经执行过 我们执行2
                    //访问
                    if(pre != null && pre.value > root.value) {
                        if(x == null) x = pre;
                        y = root;
                    }
                    //更新前驱,为下一个节点做准备
                    pre = root;
                    //断开前驱连接
                    predecessor.right = null;
                    //访问root右子树
                    root = root.right;
                }else {//predecessor.right ！= root,说明了还没执行过1
                    predecessor.right = root;
                    root = root.left;
                }
            }else {//root.left == null，root不需要链接节点的前驱（他的前驱其实就是pre(第一个节点pre为null)，
                // 且已经被访问过了），那么直接访问root
                //访问
                if(pre != null && pre.value > root.value) {
                    if(x == null) x = pre;
                    y = root;
                }
                //更新前驱,为下一个节点做准备
                pre = root;
                //访问root的右子树 遍历到底 root 肯定 != null 因为在上一层loop predecessor.right 已经连上了root
                // 会回到自己的上一个父节点
                root = root.right;
            }
        }
        swap2(x, y);
    }

    public void swap2(TreeNode x, TreeNode y) {
        int tmp = x.value;
        x.value = y.value;
        y.value = tmp;
    }

       /*
                    4
                  /
                3
               /
              1
              最后一层：root = 1, root.left == null, 会进入到else 遍历右子树
              更新pre节点 为当前的root, prev = root root往右继续遍历 root = root.right,这时不会报bull
              因为 上一层 predecessor = 1, predecessor.right = 3. root = root.right --> root = 3
      */
}
