package com.bruce.unionFind;

/**
 * Author: bruce
 * Date:2021/2/16
 * Version:1.0.0
 */
/*
N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 一次交换可选择任意两人，让他们站起来交换座位。
人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)
这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
示例 1:
输入: row = [0, 2, 1, 3]
输出: 1
解释: 我们只需要交换row[1]和row[2]的位置即可。
示例 2:
输入: row = [3, 2, 0, 1]
输出: 0
解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 */
public class Leetcode765_couplesHoldingHands {
    // 考虑用并查集 每一对情侣若它们坐在一起了就是一个连通分量
    /*
       四对情侣、五对情侣以上的情况也可以类似看待。通过举例，可以知道把 坐错了位置、逻辑上连在一起的情侣
      拆成所有的情侣都能彼此牵手的 「最少交换次数 = 情侣对数 - 1」。
    「首尾相连」这件事情可以使用 并查集 表示，将输入数组相邻位置的两个编号 在并查集中进行合并。编写代码基于了下面的事实
     如果一对情侣恰好坐在了一起，并且坐在了成组的座位上，其中一个下标一定是偶数，另一个一定是奇数，
     并且「偶数的值 + 1 = 奇数的值」。例如编号数对 [2, 3]、[9, 8]，这些数对的特点是除以 2（下取整）得到的数相等

     假设有N对情侣，逻辑上连在一起的情侣(包括坐对和坐错)分别有N1，N2...Nn 对，n是连通分量的个数，N1+N2+...+Nn = N对
     把逻辑上的情侣拆开重新交换 需要 N1-1，N2-1，...Nn-1 次。即使坐对也没关系 1 - 1=0
     (N1-1)+(N2-1)+...+(Nn-1) = (N1+...+Nn) - n 等于需要交换的次数
     N1代表这个连通分量中情侣对数，可能这个分量中有3对情侣 需要交换次数=3 - 1 = 2 若有两对情侣 2 - 1= 1交换1次
     e.g [1,2,0,3] 连通分量是将1,2除2的值位0，1合并了，0，3除2得 0，1 则之前合并过了属于同一分量 分量个数=1 2对情侣
     */
    public int minSwapsCouples(int[] row) {
        // 假设每个人都坐在正确位置上 连通分量是n 它们之间不需要连接。若它们有不相等的量 合并 分量--
        int len = row.length;
        UnionFind un = new UnionFind(len / 2);
        // 合并 要找到当前的两个元素 难点在于乘2
        for (int i = 0; i < len; i += 2) {
            un.union(row[i] / 2, row[i + 1] / 2);
        }
        // 所有情侣的对数 - 并查集里连通分量的个数
        return len / 2 - un.getCount();
    }
    class UnionFind {
        private int[] f;
        private int count;

        public UnionFind(int n) {
            f = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
            }
            this.count = n;
        }

        public int getCount() {
            return count;
        }

        public int find(int p) {
            while (p != f[p]) {
                f[p] = f[f[p]];
                p = f[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            // 将q重写在p的下面
            f[pRoot] = qRoot;
            count--;
        }
    }
}
