package com.bruce.unionnFind;

/**
 * Author: bruce
 * Date:2021/1/28
 * Version:1.0.0
 */
/*
在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

返回区域的数目。
输入：
[
  " /",
  "  "
]
输出：1
解释：2x2 网格如下：

 */
public class Leetcode959 {
    // 首先 判断会把一个区域分成4个三角形，如果只有 “/” 将左边和上边的三角形合并 下边和右边的合并 因为在同一个区域属于同一连通分量
    // 如果只有 “\” 将上边和右边合并 左边和下边合并
    // 将整个区域的值赋值给unionn算法 它的值是 n*n 个区域 还有乘上4 一个区域初始值是都不同源
    // 遍历完一个区域后 需要和相邻的单元格进行合并

    public int regionBySlashes(String[] grid) {
        int n = grid.length;
        int size = 4 * n * n;
        UF uf = new UF(size);
        for (int i = 0; i < n; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < n; j++) {
                // 二维网格转化为一维 index表示每个单元格内三角形的编号
                int index = 4 * (i * n + j);
                char c = row[j];
                if (c == '/') {
                    //合并 0 和 3， 1 和 2
                    uf.union(index, index + 3);
                    uf.union(index + 1, index + 2);
                } else if (c == '\\') {
                    uf.union(index, index + 1);
                    uf.union(index + 2, index + 3);
                } else {
                    uf.union(index, index + 1);
                    uf.union(index + 1, index + 2);
                    uf.union(index + 2, index + 3);
                }
                // 单元格间进行合并
                // 向右合并：1（当前）、3（右边一列） 最右一个单元格不与右边合并
                if (j + 1 < n) {
                    uf.union(index + 1, 4 * (i * n + j + 1) + 3);
                }
                // 向下合并: 2 和 0 最下面的单元格 i = n - 1 时不与下面合并
                if (i + 1 < n) {
                    uf.union(index + 2, 4 * ((i + 1) * n + j));
                }
                // 向左和向上
                /*if (j - 1 >= 0) {
                    uf.union(index + 3, 4 * (n * i + j - 1) + 1);
                }
                if (i - 1 >= 0) {
                    uf.union(index, 4 * (n * (i - 1) + j) + 2);
                }*/
            }
        }
        return uf.getCount();
    }

    private class UF {
        private int count;

        private int[] parent;



        public int getCount() {
            return count;
        }

        public UF(int n) {
            this.count = n;
            this.parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int q) {
            while (q != parent[q]) {
                // 若用路径压缩会有问题？最后test 是4 而不是5
               // parent[q] = parent[parent[q]];
                q = parent[q];
            }
            return q;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            // 不用按秩合并 就用简单的quick union
            parent[pRoot] = qRoot;
            count--;
        }
    }

    public static void main(String[] args) {
        Leetcode959 test = new Leetcode959();
        String[] grid = {" /", "/ "};
        String[] grid2 = {  "\\/",
                "/\\"};
        String[] grid3 = { " /",
                "  "};
        String[] grid4 = { "/\\",
                "\\/"};
        int count = test.regionBySlashes(grid);
        int count2 = test.regionBySlashes(grid2);
        int count3 = test.regionBySlashes(grid3);
        int count4 = test.regionBySlashes(grid4);
        System.out.println(count);
        System.out.println(count2);
        System.out.println(count3);
        System.out.println(count4);
    }

}
