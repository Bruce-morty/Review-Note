package com.bruce.list;


import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Author: bruce
 * Date:2020/10/20
 * Version:1.0.0
 */
public class MyArrayList<E> implements MyList<E> {
    // character
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private int size;
    private E[] elements;
    // modCount record modify count
    private int modCount;

    // constructor

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        // initialise array
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0 || initialCapacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("initialCapacity = " + initialCapacity);
        }
        elements = (E[]) new Object[initialCapacity];
    }

    /**
     * 在表尾添加元素
     *
     * @param e 要添加的元素
     * @return 如果添加成功返回true，失败返回false
     */
    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        // 定义一个变量去判断min
        int minCapacity = size + 1;
        // 数组长度小于size + 1, 意味着数组满了需要扩容。正常来说length == size
        if (elements.length < minCapacity) {
            int newLength = calculateNewLength(minCapacity);
            grow(newLength);
        }
        // add, 将这个索引的元素往后移动，空出这个位置
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    private void grow(int newLength) {
        E[] newArray = (E[]) new Object[newLength];
        for (int i = 0; i < newLength; i++) {
            newArray[i] = elements[i];
        }
        // 把newArray指向elements
        elements = newArray;
    }

    private int calculateNewLength(int minCapacity) {
        // 如果小于0或者大于MAX 都是溢出了
        if (minCapacity > MAX_CAPACITY || minCapacity < 0) {
            throw new OutOfMaxSizeException("minCapacity=" + minCapacity);
        }
        int newLength = elements.length + (elements.length >> 1);
        if (newLength < 0 || newLength > MAX_CAPACITY) {
            newLength = MAX_CAPACITY;
        }
        // 判断和minCapacity的大小，有可能传一个集合进来，需要多次扩容很麻烦
        newLength = newLength > minCapacity ? newLength : minCapacity;
        return newLength;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index= " + index + ", size= " + size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index= " + index + ", size= " + size);
        }
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        modCount++;
    }

    /**
     * 判断集合中是否包含和 o 相等的元素
     *
     * @param o 要判断的值
     * @return 如果包含返回true，否则返回false
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 获取指定索引位置的元素
     *
     * @param index 指定索引位置的元素
     * @return 该索引位置的元素
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }


    /**
     * 获取和指定对象 o 相等的第一个元素的索引
     *
     * @param o 指定的对象
     * @return 与指定对象o相等的第一个对象的索引，如不存在返回-1
     */
    @Override
    public int indexOf(Object o) {
        // 由于ArrayList可以存null，要判断o是不是null
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == o) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) return i;
            }
        }
        // 都没找到
        return -1;
    }

    /**
     * 获取和指定对象 o 相等的相等的最后一个元素的索引
     *
     * @param o 指定的对象
     * @return 与指定的对象 o 相等的最后一个元素的索引, 如果不存在, 返回 -1.
     */
    @Override
    public int lastIndexOf(Object o) {
        // 从后开始bianli
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == o) return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    /**
     * 判断集合中是否有元素
     *
     * @return 如果集合中有元素返回false, 没有元素返回true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 删除指定索引位置的元素
     *
     * @param index 指定索引位置的元素
     * @return 被删除的元素
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        // 删除，保存这个节点，将后面的值覆盖前面的元素
        E removeValue = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[index] = elements[index + 1];
        }
        size--;
        modCount++;
        return removeValue;
    }

    /**
     * 删除第一个与指定值 O 相等的元素
     *
     * @param o 指定值
     * @return 如果删除成功返回true, 否则返回false.
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 将指定索引位置的元素替换成新的值 element
     *
     * @param index   指定的索引位置
     * @param element 新的值
     * @return 该索引位置原来的值
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = elements[index];
        // 设置
        elements[index] = element;
        // 返回
        return oldValue;
    }

    /**
     * 集合中存储多少个元素
     *
     * @return 存储元素的个数
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public MyIterator iterator() {
        return new Itr();
    }

    @Override
    public MyIterator iterator(int index) {
        // 首先要检查index, cursor 可以位于size
        checkIndexForAdd(index);
        return new Itr(index);
    }

    // Iterator的remove set 等修改集合的方法之前除了要checkConcurrent 还有看lastRet是否移动过
    private class Itr implements MyIterator<E> {
        // character
        // 光标，lastRet越过元素的索引
        int cursor;
        int lastRet = -1;
        int expModCount = modCount;

        public Itr() {
        }

        public Itr(int index) {
            cursor = index;
        }

        /**
         * 在 cursor 索引位置添加一个元素, 后面的元素往后移
         *
         * @param e 要添加的元素
         */
        @Override
        public void add(E e) {
            checkConcurrentModification();
            //出现同名问题该如何解决？不能用super 不是继承关系，用类名.this
            MyArrayList.this.add(cursor, e);
            expModCount = modCount;
            cursor++; //同jdk里的实现，下一个添加的元素应该在前一个添加的后面
            // 移动光标之后, 应该将lastRet更新
            lastRet = -1; // caution 防止它出现问题，添加了之后不允许马上set 和 remove
        }

        private void checkConcurrentModification() {
            if (expModCount != modCount) {
                throw new ConcurrentModificationException();
            }
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
         * @return cursor 越过的而元素
         */
        @Override
        public E next() {
            // 首先判断是否修改过集合
            checkConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // 这时候的cursor就是要越过元素的索引
            lastRet = cursor;
            return elements[cursor++];
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
            // 倒着遍历的时候，lastRet和cursor的值相同
            lastRet = --cursor;
            return elements[lastRet];
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
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            MyArrayList.this.remove(cursor);
            expModCount = modCount;
            // 正向遍历 cursor需要--， 逆向遍历不变
            cursor = lastRet;
            lastRet = -1;
        }

        /**
         * 将最近返回的元素的设置成新的元素
         *
         * @param e 新的元素
         */
        @Override
        public void set(E e) {
            checkConcurrentModification();
            if (lastRet == -1) {
                throw new IllegalArgumentException();
            }
            elements[lastRet] = e;
            lastRet = -1;
        }
    }
}
