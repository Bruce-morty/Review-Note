package com.bruce.heap;

import java.util.NoSuchElementException;

/**
 * Author: bruce
 * Date:2020/10/21
 * Version:1.0.0
 */
public class Heap {
    private int[] elements;
    private int size;

    public Heap() {
        // 初始大小为11，index 0 no element
        elements = new int[11];
    }

    public Heap(int initalCapacity) {
        this.elements = elements;
        if (initalCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity = " + initalCapacity);
        }
        elements = new int[initalCapacity + 1];
    }

    public void add(int e) {
        // 不扩容了
        if (size == elements.length - 1) return;
        elements[size++] = e;
        // 从下往上堆化
        int i = size;
        while (i > 1 && elements[i] > elements[i / 2]) {
            // 交换元素
            swap(elements, i, i / 2);
            // 继续向上比较
            i /= 2;
        }
    }

    private void swap(int[] elements, int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * 删除堆顶的元素
     *
     * @return 堆顶元素
     */
    public int remove() {
        if (isEmpty()) throw new NoSuchElementException();
        int removeValue = elements[1];
        // 交换元素
        swap(elements, 1, size);
        size--;
        // 重新变成大顶堆
        int i = 1;
        while (true) {
            // 设i为最大值
            int maxIndex = i;
            int leftChild = i * 2;
            int rightChild = i * 2 + 1;
            if (leftChild <= size && elements[maxIndex] < elements[leftChild]) {
                maxIndex = leftChild;
            }
            if (rightChild <= size && elements[maxIndex] < elements[rightChild]) {
                maxIndex = rightChild;
            }
            // i 就是最大值
            if (maxIndex == i) break;
            // 交换max和i
            swap(elements, maxIndex, i);
            // 判断左子树或右子树
            i = maxIndex;
        }
        return removeValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i <= size; i++) {
            if (i != 1) sb.append(", ");
            sb.append(elements[i]);
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(4);
        heap.add(6);
        System.out.println(heap);
        heap.add(11);
        System.out.println(heap);
        System.out.println(heap.remove());
        System.out.println(heap);
    }
}
