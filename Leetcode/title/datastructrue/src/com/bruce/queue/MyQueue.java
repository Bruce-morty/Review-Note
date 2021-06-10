package com.bruce.queue;

/**
 * Author: bruce
 * Date:2020/10/28
 * Version:1.0.0
 */
public class MyQueue<E> {

    // 属性
    public static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;
    private int front;
    private int rear; // 插入元素所在的索引

    @SuppressWarnings("unchecked")
    public MyQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyQueue(int initialCapacity) {
        if (initialCapacity < 0 || initialCapacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("initialCapacity = " + initialCapacity);
        }
        elements = (E[]) new Object[initialCapacity];
    }

    /**
     * 将元素添加到队尾
     *
     * @param e 要添加的元素
     */
    public void enqueue(E e) {
        if (size == elements.length) {
            // 不考虑一次添加一个集合的情况了
            int length = caculatCapacity();
            grow(length);
        }
        // 添加
        elements[rear] = e;
        size++;
        // 添加了新元素 rear要移动
        rear = (rear + 1) % elements.length;
    }

    private void grow(int length) {
        E[] newElements = (E[]) new Object[length];
        for (int i = 0; i < size; i++) {
            // 找出索引
            int index = (front + i) % elements.length;
            newElements[i] = elements[index];
        }
        elements = newElements;
        // 更新属性
        front = 0;
        rear = size;
    }

    private int caculatCapacity() {
        if (size == MAX_CAPACITY) {
            throw new ArrayStoreException("数组已经达到最大了");
        }
        int newLength = elements.length + (elements.length >> 1);
        if (newLength < 0 || newLength > MAX_CAPACITY) {
            newLength = MAX_CAPACITY;
        }
        return newLength;
    }

    /**
     * 将队头元素出队列
     *
     * @return 出队列的元素
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        E oldValue = elements[front];
        elements[front] = null;
        // 循环数组 对length取余
        front = (front + 1) % elements.length;
        size--;
        return oldValue;
    }

    /**
     * 判断队列是否为空
     *
     * @return 如果队列为空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
