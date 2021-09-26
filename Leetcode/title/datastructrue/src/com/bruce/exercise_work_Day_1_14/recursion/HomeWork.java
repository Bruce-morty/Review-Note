package com.bruce.exercise_work_Day_1_14.recursion;

/**
 * Author: bruce
 * Date:2020/4/11
 * Version:1.0.0
 */
public class HomeWork {
    /*
    一个楼梯有n级，每一次走一级或两级，请问从1级台阶走到第n级台阶一共有多少种走法
    1  1
    2  2
    3  3
    4  5
    ...
    分析：
    a. 如果第一步走一步：f(n - 1)
    b. 如果第一步走两步：f(n - 2)
    f(n) = f(n - 1) + f(n - 2);

    边界条件：f (1) = 1;
            f (2) = 2;
     递推关系：f(n) = f(n-1) + f(n - 2)   n>=3;
     */

    // 这就是斐波那契数列
    public static int recursion(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return recursion(n - 1) + recursion(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(recursion(5));
    }
}
