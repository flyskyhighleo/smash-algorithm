package unionfind;

import java.util.*;

/*
https://leetcode.com/problems/number-of-good-paths/description/
There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and
exactly n - 1 edges.

You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also
given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes
ai and bi.

A good path is a simple path that satisfies the following conditions:

1. The starting node and the ending node have the same value.
2. All nodes between the starting node and the ending node have values less than or equal to the starting node
(i.e. the starting node's value should be the maximum value along the path).

Return the number of distinct good paths.

Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0.
A single node is also considered as a valid path.
 */
public class NumOfGoodPaths {
    public int numOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        int goodPaths = 0;

        UnionFind uf = new UnionFind(n, vals);
        Arrays.sort(edges, (a, b) -> Math.max(vals[a[0]], vals[a[1]]) - Math.max(vals[b[0]], vals[b[1]]));

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (uf.getMax(a) == uf.getMax(b)) {
                goodPaths += uf.getMaxCount(a) * uf.getMaxCount(b);
            }
            uf.union(a, b);
        }

        return vals.length + goodPaths;
    }

    class UnionFind {
        private int count;
        private int[] parent;
        private int[] max;
        private int[] maxCount;
        private int[] vals;

        public UnionFind(int n, int[] vals) {
            this.count = n;
            this.parent = new int[n];
            this.max = new int[n];
            this.maxCount = new int[n];
            this.vals = vals;
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.max[i] = vals[i];
                this.maxCount[i] = 1;
            }
        }

        public int find(int x) {
            if (this.parent[x] != x) {
                this.parent[x] = find(this.parent[x]);
            }
            return this.parent[x];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                this.parent[rootP] = rootQ;

                if (this.max[rootP] > this.max[rootQ]) {
                    this.maxCount[rootQ] = maxCount[rootP];
                    this.max[rootQ] = this.max[rootP];
                } else if (this.max[rootP] == this.max[rootQ]){
                    this.maxCount[rootQ] += this.maxCount[rootP];
                }

                count--;
            }
        }

        public int getMax(int x) {
            return this.max[find(x)];
        }

        public int getMaxCount(int x) {
            return this.maxCount[find(x)];
        }
    }
}
