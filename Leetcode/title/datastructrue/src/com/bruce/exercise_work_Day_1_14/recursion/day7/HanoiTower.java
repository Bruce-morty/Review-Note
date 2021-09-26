package com.bruce.exercise_work_Day_1_14.recursion.day7;

/**
 * Author: bruce
 * Date:2020/12/1
 * Version:1.0.0
 */
public class HanoiTower {
    // 计算移动汉诺塔盘子的次数
    // 边界条件：count(1) = 1
    // 递推关系：将N个盘子从A移动到C ： 将N-1个盘子从A移动到B + 将1个盘子从A移动到C + 将N-1个盘子从B移动到C
    // count(n) = 2count(n - 1) + 1
    /*
    f(n) = count(n) + 1 = 2 * (count(n-1) + 1) = 2f(n-1);
	f(1) = 2
	f(n) = 2^n --> count(n) = 2^n - 1;
     */
    public static double count(int n) {
    /*if(n == 1) return 1;
		return 2 * count(n-1) + 1;*/
        return Math.pow(2, n) - 1;
    }

    /*
    应该怎么移动这个盘子，把移动过程打印出来。定义三个参数 开始位置 中间经过位置 最后位置
     */
    public static void move(char start, char middle, char end, int n) {
        if (n == 1) {
            System.out.println(start + "-->" + end);
            return;
        }
        // 打印 N-1个盘子从 A 到 B
        move(start, end, middle, n - 1);
        // 递归到这里 要打印这个盘子从 A到C
        System.out.println(start + "-->" + end);
        // 打印 盘子从 B 到 C
        move(middle, start, end, n - 1);
    }

    public static void main(String[] args) {
        int n = 10;
        move('A', 'B', 'C', n);
    }
}
