package com.bruce.exercise_work_Day_1_14.recursion;

/**
 * Author: bruce
 * Date:2020/4/8
 * Version:1.0.0
 */
public class RecursionCase1 {
    /*
兔子问题
有一对兔子，从出生后每隔2个月开始每月生一对兔子，小兔子每个两月开始每月也生一对兔子，
假如是不死神兔，那么第20个月一共生多少对兔子？

月份：	1	2	3	4	5	6	7	...
数量：	1	1	2	3	5	8 	13	...

两个明确：
	a. 边界条件
		n <= 2	fib(n) = 1
	b. 递推关系
		fib(n) = fib(n-1) + fib(n-2); n > 2;

递归和循环：
	递归：
		优点: 简洁, 更符合人类的思想
		缺点：效率低
	循环：
		优点：效率高
		缺点：不够简洁。

什么情况下, 可以考虑使用递归
	a. 大问题可以分解成若干个子问题。
	b. 子问题之间除了数据规模不一样, 求解方式是一样。
	c. 子问题都有边界条件

注意事项：
	a. 一定要有边界条件, 且递归不能太深。否则会抛出 StackOverflowError.
	b. 一定要警惕重复计算问题。
*/
    public static void main(String[] args) {
        int amount = fib1(20);
        System.out.println("第20个月一共有" + amount + "对兔子");
    }

    public static int fib1(int n) {
        if (n <= 2) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    // use loop
    public static int fib2(int n) {
        if (n <= 2) return 1;
        // 前一个月和后一个月
        int a = 1;
        int b = 1;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            // 前一个月等于现在的后一个月的值
            a = b;
            b = c;
        }
        return b;
    }
}
