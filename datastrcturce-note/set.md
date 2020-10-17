# 集合总结

### Collection 

* **List**：有序的可以存储重复的集合
  * ArrayList：底层是数组，增删慢，查找快，线程不安全
  * LinkedList：底层是链表，增删快，查找慢，线程不安全
  * Vector：底层是数组，增删慢，查找快
* **Set**: 不可以存储重复元素的集合，set可以是有序的也可以是无序的
  * HashSet: 底层是哈希表，元素的唯一性依赖元素的hashcode() 和equals(Object obj); 不保证迭代顺序，并且不保证迭代顺序永久不变。原因：扩容时会重新计算hash值确定索引，和之前不同。
  * LinkedHashSet: HashSet的子类，底层是哈希表+链表。哈希表保证元素的唯一性，链表保证元素的有序性。有序：迭代的顺序和插入的顺序一致，线程不安全，效率高
  * TreeSet：底层是TreeMap，TreeMap底层是红黑树。如果创建TreeSet 没有传入一个 Comparator, 就要求存储元素能够进行比较(实现 Comparable接口)。如果创建TreeSet 传入了一个 Comparator, TreeSet 就会根据 Comparator 里面的比较规则比较元素。TreeSet 不能存储 null 元素。