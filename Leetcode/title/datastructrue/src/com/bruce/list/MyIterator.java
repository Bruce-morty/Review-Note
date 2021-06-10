package com.bruce.list;

import java.util.Iterator;

/**
 * Author: bruce
 * Date:2020/10/20
 * Version:1.0.0
 */
public interface MyIterator<E> extends Iterator<E> {
    void add(E e);

    boolean hasNext();

    boolean hasPrevious();

    E next();

    int nextIndex();

    E previous();

    int previousIndex();

    void remove();

    void set(E e);
}