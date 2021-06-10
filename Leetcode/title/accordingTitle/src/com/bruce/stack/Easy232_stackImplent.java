package com.bruce.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2021/3/5
 * Version:1.0.0
 */
/*
请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
实现 MyQueue 类：
void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false
输入：
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 1, 1, false]

解释：
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 */
public class Easy232_stackImplent {
    // 用两个栈实现api 可知道 主要是pop和peek元素的时候 需要操作 将一个栈中元素栈顶放到另一个栈中就相当于队列的头元素了
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    /** Initialize your data structure here. */
    public Easy232_stackImplent() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        // 直接压入输入栈中
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 如果outstack也是空呢  就把instack的元素都放进来 不为空直接返回
        if (outStack.isEmpty()) {
            if (!inStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
        // 输出栈的栈顶就是instack的栈底也是队列的头元素
        return outStack.pop();

    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            if (!inStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        // 测instack
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
