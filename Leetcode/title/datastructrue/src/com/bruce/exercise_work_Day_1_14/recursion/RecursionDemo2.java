package com.bruce.exercise_work_Day_1_14.recursion;

import java.util.Scanner;

/**
 * Author: bruce
 * Date:2020/4/8
 * Version:1.0.0
 */
/*
汉诺塔（港台：河内塔）是根据一个传说形成的数学问题：

有三根杆子A，B，C。A杆上有 N 个 (N>1) 穿孔圆盘，盘的尺寸由下到上依次变小。要求按下列规则将所有圆盘移至 C 杆：

每次只能移动一个圆盘；
大盘不能叠在小盘上面。
提示：可将圆盘临时置于 B 杆，也可将从 A 杆移出的圆盘重新移回 A 杆，但都必须遵循上述两条规则。

问：如何移？最少要移动多少次？

问题1：最小需要移动多少次？
1	1
2	3
3	7
...
边界条件：
	count(1) = 1
递推关系：
	count(n) = 2*count(n-1) + 1;
	f(n) = count(n) + 1 = 2 * (count(n-1) + 1) = 2f(n-1);
	f(1) = 2
	f(n) = 2^n --> count(n) = 2^n - 1;

问题2：怎么移?
1	A --> C
2	A --> B
	A --> C
	B --> C
3	A --> C
	A --> B
	C --> B
	A --> C
	B --> A
	B --> C
	A --> C
4	...

递推公式：将N个盘子从A移动到C	--> 将N-1个盘子从A移动到B + 将最后一个盘子从A移动到C + 将N-1个盘子从B移动到C.
边界条件：N=1
*/
public class RecursionDemo2 {
    public static double count(int n) {
        /*if (n == 1) return 1;
        return 2 * count(n - 1) + 1;*/
        return Math.pow(2, n) - 1;
    }

    public static void main(String[] args) {
        System.out.println("请输入圆盘的个数：");
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();
        double count = count(nextInt);
        System.out.println("最少移动：" + count + "次");

        move('A', 'B', 'C', nextInt);
    }

    // 怎么移动
    // 把圆盘从start 移动 到end 经过 mid柱子
    /*
递推公式：将N个盘子从A移动到C --> 将N-1个盘子从A移动到B + 将最后一个盘子从A移动到C + 将N-1个盘子从B移动到C.
边界条件：N=1
     */
    public static void move(char start, char mid, char end, int n) {
        // 边界条件
        if (n == 1) {
            System.out.println(start + "-->" + end);
            return;
        }
        // 把第一根柱子上的盘子移动到第二根  A --> B
        // 把圆盘从start 移动 到mid 经过 end柱子
        move(start, end, mid, n - 1);
        // 把最后一个圆盘移动到 第三根柱子  A --> c
        System.out.println(start + "-->" + end);
        // 把第二根柱子上的盘子移动到第三根柱子  B --> C
        move(mid, start, end, n - 1);
    }
}
