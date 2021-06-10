package com.bruce.list;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Author: bruce
 * Date:2020/10/21
 * Version:1.0.0
 */
public class MyLinkedList<E> implements MyList<E> {
    // 属性，链表，size
    // 哨兵
    private Node head;
    private Node end;
    private int size;
    private int modCount;


    private class Node {
        E value;
        // 双向链表
        Node prev;
        Node next;


        public Node(E value) {
            this.value = value;
        }

        public Node(Node prev, E value, Node next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    // constructor

    public MyLinkedList() {
        // 初始化属性
        head = new Node(null, null, null);
        end = new Node(head, null, null);
        head.next = end;
    }

    /**
     * 在表尾添加元素
     *
     * @param e 元素
     * @return 添加成功返回true 否则返回false
     */
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        // 链表添加不需要考虑扩容
        // 判断是不是size位置
        if (index == size) {
            Node nodeToAdd = new Node(end.prev, element, end);
            end.prev.next = nodeToAdd;
            end.prev = nodeToAdd;
        } else {
            // 添加元素 原来的元素往后移动
            // 得到索引位置的节点
            Node node = getNode(index);
            Node nodeToAdd = new Node(node.prev, element, node);
            node.prev.next = nodeToAdd;
            node.prev = nodeToAdd;
        }
        size++;
        modCount++;
    }

    private Node getNode(int index) {
        checkIndex(index);
        // 判断节点在前面还是后面
        if (index * 2 < size) {
            // 有哨兵
            Node node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node node = end.prev;
            for (int i = size - 1; i > index; i--) {
                end = end.prev;
            }
            return node;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index = " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index=  " + index);
        }
    }


    /**
     * 清空所有元素
     */
    @Override
    public void clear() {
        head.next = end;
        end.prev = head;
        size = 0;
        modCount++;
    }

    /**
     * 判断集合中是否存在与指定对象o相等的元素
     *
     * @param o 指定对象
     * @return 如果存在返回true 否则返回false
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 取得该索引位置的元素
     *
     * @param index
     * @return 该索引的元素
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }

    /**
     * 获取第一个与指定对象o相等的元素的索引
     *
     * @param o 指定对象
     * @return 第一个与o相等元素的索引
     */
    @Override
    public int indexOf(Object o) {
        Node node = head;
        if (o == null) {
            node = node.next;
            for (int i = 0; i < size; i++) {
                if (node.value == o) return i;
                node = node.next;
            }
        } else {
            node = node.next;
            for (int i = 0; i < size; i++) {
                if (o.equals(node.value)) return i;
                node = node.next;
            }
        }
        return -1;
    }

    /**
     * 获取最后一个与指定对象 o 相等的元素的索引
     *
     * @param o 指定对象
     * @return 最后一个与 o 相等元素的索引, 如果不存在返回-1
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            Node node = end.prev;
            for (int i = size - 1; i >= 0; i--) {
                if (node.value == o) return i;
                node = node.prev;
            }
        } else {
            Node node = end.prev;
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(node.value)) return i;
                node = node.prev;
            }
        }
        return -1;
    }


    /**
     * 判断集合中是否有元素
     *
     * @return 如果有元素返回false, 否则返回true.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 删除指定索引位置的而元素
     *
     * @param index 指定索引位置
     * @return 被删除的元素
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        // remove 需要get节点
        Node node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        modCount++;
        return node.value;
    }


    /**
     * 删除第一个与指定对象 o 相等的元素
     *
     * @param o 指定对象
     * @return 如果删除成功返回 true, 否则返回 false.
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            Node node = head.next;
            for (int i = 0; i < size; i++) {
                if (node.value == o) {
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                    size--;
                    modCount++;
                    return true;
                }
                node = node.next;
            }
        } else {
            Node node = head.next;
            for (int i = 0; i < size; i++) {
                if (o.equals(node.value)) {
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                    size--;
                    modCount++;
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /**
     * 将指定索引位置的元素替换成 element
     *
     * @param index   指定的索引位置
     * @param element 新的元素
     * @return 旧的元素
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node node = getNode(index);
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }


    /**
     * 获取集合中元素的个数
     *
     * @return 集合中元素的个数
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node.value);
            node = node.next;
        }
        return sb.append("]").toString();
    }

    @Override
    public MyIterator<E> iterator() {
        return new Itr();
    }

    @Override
    public MyIterator<E> iterator(int index) {
        return new Itr(index);
    }

    private class Itr implements MyIterator<E> {
        // 属性 cursor lastRest expModCount
        int cursor;
        // 不仅要有光标，还要有下一个节点的索引，因为不是数组，仅有光标无法得到值
        Node nextNode;
        Node lastRet = null;
        int expModCount = modCount;

        Itr() {
            // cursor 是基本数据类型，会自动初始化值是0, 引用数据类型必须初始化(e.g. lastRet = null)
            nextNode = head.next;
        }

        Itr(int index) {
            // 即使cursor可以取到size位置，但是这个位置没有值，getNode方法会报错，单独判断
            cursor = index;
            if (index == size) {
                nextNode = end;
            } else {
                nextNode = getNode(index);
            }
        }

        private void checkConcurrentModification() {
            if (expModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * 在 cursor 索引位置添加一个元素, 后面的元素往后移
         *
         * @param e 要添加的元素
         */
        @Override
        public void add(E e) {
            checkConcurrentModification();
            Node node = new Node(nextNode.prev, e, nextNode);
            // 添加两条链
            nextNode.prev.next = node;
            nextNode.prev = node;
            cursor++;
            expModCount = ++modCount;
            lastRet = null;
        }

        /**
         * 判断是否有下一个元素
         *
         * @return 如果有下一个元素返回 true, 否则返回 false
         */
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * 判断是否有前一个元素
         *
         * @return 如果有前一个元素返回 true, 否则返回 false
         */
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }


        /**
         * 将 cursor 往后移动一个位置, 并将越过的元素返回
         *
         * @return cursor 越过的元素
         */
        @Override
        public E next() {
            checkConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // 越过元素
            lastRet = nextNode;
            cursor++;
            nextNode = nextNode.next;
            return lastRet.value;
        }

        /**
         * 获取下一个元素的索引
         *
         * @return 下一个元素的索引
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * 将 cursor 往前移动一个位置, 并将越过的元素返回
         *
         * @return cursor 越过的元素
         */
        @Override
        public E previous() {
            checkConcurrentModification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            // 倒着返回 lastRet = cursor
            nextNode = nextNode.prev;
            cursor--;
            lastRet = nextNode;
            return lastRet.value;
        }

        /**
         * 获取前一个元素的索引
         *
         * @return 前一个元素的索引
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }


        /**
         * 删除最近返回的元素
         */
        @Override
        public void remove() {
            checkConcurrentModification();
            if (lastRet == null) {
                throw new IllegalArgumentException();
            }
            // 删除节点，因为有哨兵，不用担心删除的是不是头节点
            nextNode.prev.next = nextNode.next;
            nextNode.next.prev = nextNode.prev;
            size--;
            expModCount = modCount;
            // cursor要改变，lastRet要变null
            // 正向 cursor--，逆向不变
            if (lastRet == nextNode) {
                // 逆向，nextNode保存的是删除的元素，删了之后要移动
                nextNode = nextNode.next;
            }else {
                cursor--;
            }
            lastRet = null;
        }

        @Override
        public void set(E e) {
            checkConcurrentModification();
            if (lastRet == null) {
                throw new NoSuchElementException();
            }
            lastRet.value = e;
            // 设置完后lastRet更新
            lastRet = null;
        }
    }

    public static void main(String[] args) {
        MyList<String> list = new MyLinkedList<>();
        /*System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());*/
        list.add("hello");
        list.add("world");
        list.add("java");
       /* System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());*/

        // void add(int index, E element);
        list.add(0, "goodbye");
        list.add(list.size(), "wuhan");
        System.out.println(list);
        list.add(2, "kitty");
        System.out.println(list);

        // list.add(-1, "kitty");
        // list.add(list.size() + 1, "kitty");

        // void clear();
       /* list.clear();
        System.out.println(list);
        System.out.println(list.isEmpty());*/

        // boolean contains(Object o);
        /*list.add(null);
        System.out.println(list.contains("java"));
        System.out.println(list.contains(null));
        System.out.println(list.contains("wuhan"));*/

        // E get(int index);
        // System.out.println(list.get(1));
        // System.out.println(list.get(-1));
        // System.out.println(list.get(list.size()));

        // int indexOf(Object o);
        /*list.add(null);
        list.add("hello");
        list.add(null);
        System.out.println(list);
        System.out.println(list.indexOf("hello"));
        System.out.println(list.indexOf(null));
        System.out.println(list.indexOf("world"));
        System.out.println(list.indexOf("wuhan"));*/

        // int lastIndexOf(Object o);
        /*list.add(null);
        list.add("hello");
        list.add(null);
        System.out.println(list);
        System.out.println(list.lastIndexOf("hello"));
        System.out.println(list.lastIndexOf(null));
        System.out.println(list.lastIndexOf("world"));
        System.out.println(list.lastIndexOf("wuhan"));*/

        // E remove(int index);
        // System.out.println(list.remove(1));
        // System.out.println(list.remove(-1));
        // System.out.println(list.remove(list.size()));
        // System.out.println(list);

        // boolean remove(Object o);
       /* list.add(null);
        list.add("hello");
        list.add(null);
        System.out.println(list);
        // System.out.println(list.remove("hello"));
        // System.out.println(list.remove(null));
        // System.out.println(list.remove("world"));
        System.out.println(list.remove("wuhan"));
        System.out.println(list);*/

        // E set(int index, E element);
        /*list.add(null);
        System.out.println(list.set(3, "hello"));
        System.out.println(list.set(2, "javaSE"));
        // System.out.println(list.set(-1, ""));
        System.out.println(list.set(list.size(), ""));
        System.out.println(list);*/
    }
}
