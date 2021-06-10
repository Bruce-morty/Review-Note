package com.bruce.map;

import java.util.HashSet;
import java.util.Set;

/**
 * 简单的 HashMap，不能存储 null 键和 null 值。
 *
 * @param <K> 键的类型
 * @param <V> 值得类型
 * @author bruce
 * @version v1.1
 */
public class MyHashMap<K, V> {
    // 属性, 链表加数组
    private static final int DEFAULT_LENGTH = 16;
    private static final int MAX_LENGTH = 1 << 30;
    private static final double DEFAULT_LOADFACTOR = 0.75;
    private Entry[] table;
    private int size;
    private double loadFactor;
    // 阈值
    private int threshold;

    class Entry<K, V> {
        K key;
        V value;
        // 应该右hash值，保存下来方便后面比较
        int hash;
        Entry next;

        // 构造方法

        @Override
        public String toString() {
            return key + "=" + value;
        }

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    // 构造方法
    public MyHashMap() {
        this(DEFAULT_LENGTH, DEFAULT_LOADFACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOADFACTOR);
    }

    public MyHashMap(int initialCapacity, double loadFactor) {
        // 判断参数是否合法
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initalCapacity = " + initialCapacity);
        }

        if (loadFactor <= 0) {
            throw new IllegalArgumentException("loadFactor = " + loadFactor);
        }

        int length = tableLength(initialCapacity);
        table = new Entry[length];
        this.loadFactor = loadFactor;
        threshold = (int) (length * loadFactor);
        // 判断阈值能否存下这么多元素
        threshold = threshold >= initialCapacity ? threshold : initialCapacity;
    }

    private int tableLength(int cap) {
        if (cap >= MAX_LENGTH) return MAX_LENGTH;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    /**
     * 添加键值对
     *
     * @param key   键
     * @param value 值
     * @return 如果存在和key相等键，更新value，返回原来的值
     */
    public V put(K key, V value) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            // 快速失败
            if (hash == entry.hash && (key == entry.key) || key.equals(entry.key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        // entry == null，添加新元素
        add(key, value, hash, index);
        return null;
     }

    private void add(K key, V value, int hash, int index) {
        if (threshold == size) {
            if (table.length == MAX_LENGTH) {
                threshold = Integer.MAX_VALUE;
            } else {
                // 扩容 1.5倍
                grow(table.length << 1);
                threshold =(int) (table.length * loadFactor);
                // 重新计算索引, 扩容之后元素不一定在原来位置
                index = indexFor(hash, table.length);
            }
        }
        // 添加进去元素
        Entry<K, V> entryToAdd = new Entry<>(key, value, hash);
        // 在头节点添加，然后变成头节点
        entryToAdd.next = table[index];
        table[index] = entryToAdd;
        size++;
    }

    private void grow(int length) {
        Entry[] newTable = new Entry[length];
        // 把所有数据赋值过去
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            // 找到这个所有下的全部节点
            while (entry != null) {
                // 需要一个节点保存entry的下一个节点,
                Entry node = entry.next;
                // 在新数组中的位置
                int index = indexFor(entry.hash, newTable.length);
                // 添加到头节点，将第一个节点连上新数组的空位置，数组再往下移动
                entry.next = newTable[index];
                // entry 又变回头节点了。新数组头节点
                newTable[index] = entry;
                // 遍历下一个节点
                entry = node;
            }
        }
        table = newTable;
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private int hash(Object obj) {
        int hash = obj.hashCode();
        return (hash << 16) ^ (hash >> 16);
    }

    /**
     * 根据 key 获取 value
     *
     * @param key 指定的键
     * @return key 对应的 value
     */
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (hash == entry.hash && key == entry.key || key.equals(entry.key)) {
                return entry.value;
            }
        }
        return null;
    }


    /**
     * 根据 key 删除对应的键值对
     *
     * @param key 指定的key
     * @return key 对应的 value, 如果没有对应的键值对, 返回 null
     */
    public V remove(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
         //保存前一个节点来删除
        Entry<K, V> prev = null;
        for(Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (hash == entry.hash && key == entry.key || key.equals(entry.key)) {
                // 删的是头节点
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    // 不是头节点的话就不用管数组了，table[index] 已经指向头节点
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            // 没找到 节点往下移动
            prev = entry;
        }
        return null;
    }

    /**
     * 清空所有键值对
     */
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * 判断指定的key是否存在
     *
     * @param key 指定的键
     * @return 如果存在返回true, 否则返回false
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    /**
     * 获取键的集合
     *
     * @return 键的集合
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                set.add(entry.key);
                entry = entry.next;
            }
        }
        return set;
    }

    /**
     * 判断Map是否为空
     *
     * @return 如果Map为空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取键值对的个数
     *
     * @return 键值对的个数
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                sb.append(entry).append(", ");
            }
        }
        // 退出循环，多添加了一个逗号空格
        // 先判断非空，就可以在后面之间拼接 ]
        if (!isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append("]").toString();
    }


    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
       /* System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.size());*/
        map.put("A", "Allen");
        map.put("B", "Beyonce");
        map.put("C", "Cindy");
        map.put("D", "Diana");
        /*System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.size());*/

        /*System.out.println(map.put("C", "Christa"));
        System.out.println(map);*/

       /* System.out.println(map.get("A"));
        System.out.println(map.get("X"));*/

        // System.out.println(map.remove("D"));
       /* System.out.println(map.remove("X"));
        System.out.println(map);
        System.out.println(map.size());*/

        // clear()
       /* map.clear();
        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.isEmpty());*/

        // containsKey(K key)
       /* System.out.println(map.containsKey("B"));
        System.out.println(map.containsKey("X"));*/

        // keySet()
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key + "=" + map.get(key));
        }
    }
}
