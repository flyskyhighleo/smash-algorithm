package unionfind;

/*
https://leetcode.com/problems/number-of-islands/description/
Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume
all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int oneCount = 0;

        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    oneCount++;
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        uf.union(convertIndex(i, j, n), convertIndex(i - 1, j, n));
                    }
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        uf.union(convertIndex(i, j, n), convertIndex(i, j + 1, n));
                    }
                }
            }
        }

        return uf.components() + oneCount - m * n;
    }

    private int convertIndex(int x, int y, int n) {
        return x * n + y;
    }

    class UnionFind {
        private int count;
        private int[] parent;

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--;
            }
        }

        public int components() {
            return this.count;
        }
    }
}
