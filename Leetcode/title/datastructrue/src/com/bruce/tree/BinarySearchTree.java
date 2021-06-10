package com.bruce.tree;

import java.util.*;

/**
 * Author: bruce
 * Date:2020/10/21
 * Version:1.0.0
 */
/*
api:
    boolean add(E e)
    boolean remove(E e)
    boolean isEmpty()
    boolean contains(E e)
    void clear()
    int size()
    E max()
    E min()
    二叉树的时间复杂度和高相关
    int depth

    遍历：
        List<E> preOrder()
        List<E> inOrder()
        List<E> postOrder()
        List<E> levelOrder()
    建树：可以不存在这颗树对象，所以声明成静态方法
        static BinarySearchTree buildTree(List<E> preOder, List<E> inOrder)
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
    // 属性
    private int size;
    // 根节点
    private TreeNode root;

    private class TreeNode {
        // 链表
        E value;
        TreeNode left;
        TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }

        public TreeNode(E value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 在二叉树中添加元素 e
     *
     * @param e 要添加的元素
     * @return 如果添加成功返回true, 否则返回false
     */
    public boolean add(E e) {
        // 循环实现
        // 首先判断有没有节点
        if (root == null) {
            root = new TreeNode(e);
            size++;
            return true;
        }
        // 在root添加不同, 找到PNode才好添加
        TreeNode pNode = null;
        TreeNode node = root;
        // 需要和添加位置判断，加在左边还是右边
        int cmp;
        do {
            cmp = e.compareTo(node.value);
            // 不能添加相同元素
            if (cmp == 0) return false;
            else if (cmp < 0) {
                pNode = node;
                node = node.left;
            } else {
                pNode = node;
                node = node.right;
            }
        } while (node != null);
        // 找到node
        // 通过cmp 判断在右边添加还是左边
        if (cmp > 0) {
            pNode.right = new TreeNode(e);
        } else {
            pNode.left = new TreeNode(e);
        }
        size++;
        return true;

        //递归实现
        /*int oldSize = size;
        root = add(root, e);
        // 通过size判断是否添加成功
        return oldSize < size;*/
    }

    private TreeNode add(TreeNode node, E e) {
        // 没有节点
        if (node == null) {
            size++;
            return new TreeNode(e);
        }
        int cmp = e.compareTo(node.value);
        if (cmp == 0) return node;
        if (cmp > 0) node.right = add(node.right, e);
        if (cmp < 0) node.left = add(node.left, e);
        return node;
    }

    /**
     * 在二叉树中删除和指定对象 e 相等的元素
     *
     * @param e 指定对象
     * @return 如果删除成功返回true，否则返回false
     */
    public boolean remove(E e) {
        // 循环，在度为 1，0，2的节点添加完全不同
/*        TreeNode pNode = null;
        TreeNode node = root;
        while (node != null) {
            int cmp = e.compareTo(node);
            if (cmp == 0) break;
            if (cmp < 0) {
                pNode = node;
                node = node.left;
            }
            if (cmp > 0) {
                pNode = node;
                node = node.right;
            }
        }
        // 判断node,如果为null，没找到相同的元素
        if (node == null) return false;
        // 删除度为2
        while (node.left != null & node.right != null) {
            // 找到右子树中的最小左节点
            TreeNode minOfRight = node.right;
            while (minOfRight.left != null) {
                minOfRight = minOfRight.left;
            }
            //找到了，断开链 连接链
            minOfRight.left = node.left;
            // 如果删的是头节点
            // 现在要开始连被删除节点node 的右子树
            if (pNode == null) {
                root = node.right;
            } else {
                if (pNode.left == node) {
                    pNode.left = node.right;
                }else
                    pNode.right = node.right;
            }
        }
        // 删除度为1和度为0
        TreeNode child = node.left != null ? node.left : node.right;
        if (pNode == null) {
            root = child;
        } else {
            if (pNode.left == node) {
                pNode.left = child;
            } else pNode.right = child;
        }
        size--;
        return true;*/
        int oldSize = size;
        root = remove(root, e);
        return oldSize < size;
    }

    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;
        int cmp = e.compareTo(node.value);
        if (cmp < 0) node.left = remove(node.left, e);
        else if (cmp > 0) node.right = remove(node.right, e);
        else {
            size--;
            if (node.left != null && node.right != null) {
                TreeNode minOfRight = node.right;
                while (minOfRight.left != null) {
                    minOfRight = minOfRight.left;
                }
                // 连上左子树
                minOfRight.left = node.left;
                // 现在返回被删除的右子树回去连接链
                return node.right;
            } else {
                TreeNode child = node.left != null ? node.left : node.right;
                // 只删除度为1或者0的就不用care，最终需要连接的只有一条链，直接返回child
                return child;
            }
        }
        // 画图就可以理解了，没有经历删除的元素，按照同样的方式重新连上链
        // e.g 没有被删除的节点，按原样返回，传入一个 node.right, 返回node.right
        // 最后 node.right = node.right; 什么都没变
        return node;
    }

    /**
     * 判断是否存在于指定对象 e 相等的元素
     *
     * @param e
     * @return 存在返回true 否则返回false
     */
    public boolean contains(E e) {
        TreeNode node = root;
        while (node != null) {
            int cmp = e.compareTo(node.value);
            if (cmp == 0) return true;
            else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        // 没有与指定对象相等的元素
        return false;
        // 写个递归的方法, 需要对树进行递归，写个新方法
        // return contains(root, e);
    }

    private boolean contains(TreeNode node, E e) {
        if (node == null) return false;
        int cmp = e.compareTo(node.value);
        if (cmp == 0) return true;
        if (cmp < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }


    /**
     * 清空所有元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 获取存储元素的个数
     *
     * @return 存储元素的个数
     */
    public int size() {
        return size;
    }

    /**
     * 查找树中最大的元素
     *
     * @return 最大的元素
     */
    public E max() {
        if (root == null) return null;
        TreeNode node = root;
        // 因为要用到右节点，前面要判断root，否则 node=null node.right会报空指针
        while (node.right != null) {
            node = node.right;
        }
        // 最后出来node.right为null
        return node.value;
    }

    /**
     * 查找树中最小的元素
     *
     * @return 最小的元素
     */
    public E min() {
        /*TreeNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.value;*/
        // recursion
        if (root == null) return null;
        return min(root).value;
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) return node;
        return min(node.left);
    }


    /**
     * 求这个树的高
     *
     * @return 高度
     */
    public int depth() {
        return depth(root);
    }

    private int depth(TreeNode node) {
        if (node == null) return -1;
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth;
    }

    /**
     * 先序遍历
     *
     * @return 先序遍历的List
     */
    public List<E> preOrder() {
        /*List<E> list = new ArrayList<>();
        preOrder(root, list);
        return list;*/
        // 用栈实现 depth first search
        List<E> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        // 1.把根节点入栈 2.判断栈是否为空，不为空出栈
        // 3. 判断是否右右子树，有入栈， 判断是否有左子树，有入栈。 继续回到 2
        // 4.最后把所有节点出栈 完成
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            // list用来收集遍历出来的节点
            list.add(treeNode.value);
            if (treeNode.right != null) stack.push(treeNode.right);
            if (treeNode.left != null) stack.push(treeNode.left);
        }
        return list;
    }

    private void preOrder(TreeNode node, List<E> list) {
        if (node == null) return;
        // 遍历根节点
        list.add(node.value);
        // 遍历左子树
        preOrder(node.left, list);
        // 右子树
        preOrder(node.right, list);
    }


    /*
    栈迭代实现中序遍历
     */
    public List<E> inOrderTraversal() {
        // 创建栈和list存放 节点和结果
        Deque<TreeNode> stack = new LinkedList<>();
        ArrayList<E> res = new ArrayList<>();
        // 判断条件, 若节点 != null || !stack.isEmpty()
        while (root != null || !stack.isEmpty()) {
            // 将node放进栈里，中序遍历要先处理左子树，先放入根节点 再放入所有左子树
            // 并且如果 root等于null的话说明没有左子树了，出栈就是最下面的左子树节点,添加到list
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 若右子树也为null，继续出栈，原来的根节点是即将出栈的节点的左子树
            root = stack.pop();
            res.add(root.value);
            // 从当前节点继续找右子树, 因为它的左子树没有了，已经添加进去的相当于根节点
            root = root.right;
        }
        return res;
    }

    public List<E> inOrder() {
        List list = new ArrayList();
        inOrder(root, list);
        return list;
    }

    private void inOrder(TreeNode node, List list) {
        if (node == null) return;
        // 遍历左子树
        inOrder(node.left, list);
        // 遍历根节点
        list.add(node.value);
        // 遍历右子树
        inOrder(node.right, list);
    }

    /*
    后序遍历
     */
    public List<E> postOrder() {
        List<E> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    private void postOrder(TreeNode node, List<E> list) {
        if (node == null) return;
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.value);
    }

    /**
     * 层级遍历
     *
     * @return 层级遍历的list
     */
    /*public List<E> levelOrder() {
        if (root == null) return null;
        // 将根节点入队，2.判断队列是否为空，不为空出队。3.判断出队元素是否有左孩子，有入队，判断右孩子
        List<E> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.value);
            // 先判断左
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return list;
    }*/

    /**
     * 返回层级信息
     *
     * @return
     */
    public List<List<E>> levelOrder() {
        List<List<E>> externalList = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        // 在每一层判断
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 需要知道这个队列的大小
            int size = queue.size();
            List<E> list = new ArrayList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.value);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                // 这一层遍历完了
            }
            // 这一层遍历完了，external
            // external.add(list);
            // 这样就是倒序输出
            externalList.add(0, list);
        }
        return externalList;
    }


    // 建树操作
    public static <T extends Comparable<? super T>> BinarySearchTree<T> buildTree(List<T> preOder, List<T> inOrder) {
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        // 查找树的节点个数
        tree.size = preOder.size();
        // 构建树
        tree.root = tree.build(preOder, inOrder);
        return tree;
    }

    private TreeNode build(List<E> preOder, List<E> inOrder) {
        // 空树
        if (preOder == null || preOder.size() == 0) return null;
        // 找到根节点
        E e = preOder.get(0);
        TreeNode node = new TreeNode(e);
        // 找头节点的位置，找到索引进行建树
        int index = inOrder.indexOf(e);
        int size = preOder.size();
        // 找到左子树的前序遍历
        List<E> leftPreOrder = preOder.subList(1, index + 1);
        // 找左子树中序遍历
        List<E> leftInOrder = inOrder.subList(0, index);
        node.left = build(leftPreOrder, leftInOrder);

        //右子树的前序和中序
        List<E> rightPreOrder = preOder.subList(index + 1, size);
        List<E> rightInOrder = inOrder.subList(index + 1, size);
        node.right = build(rightPreOrder, rightInOrder);
        return node;
    }

    public static void main(String[] args) {
        List<Character> preOrder = Arrays.asList('b', 'a', 'd', 'c', 'e');
        List<Character> inOrder = Arrays.asList('a', 'b', 'c', 'd', 'e');
        BinarySearchTree<Character> tree = BinarySearchTree.buildTree(preOrder, inOrder);

        System.out.println(tree.isEmpty());
        System.out.println(tree.size());
        System.out.println(tree.preOrder());
        System.out.println(tree.inOrder());
        System.out.println(tree.postOrder());
        System.out.println(tree.levelOrder());
        System.out.println("depth=" + tree.depth());
    }
}
