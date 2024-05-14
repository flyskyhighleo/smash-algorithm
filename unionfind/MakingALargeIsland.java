package unionfind;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/making-a-large-island/description/
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.
 */
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                if (cur == 1) {
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        uf.union(convert(i, j, m, n), convert(i - 1, j, m, n));
                    }
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        uf.union(convert(i, j, m, n), convert(i, j +  1, m, n));
                    }
                }
            }
        }

        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                int curIndex = convert(i, j, m, n);
                if (cur == 1 && curIndex == uf.find(curIndex)) {
                   res = Math.max(res, uf.getSize(curIndex));
                }
            }
        }

        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                Set<Integer> visited = new HashSet<>();
                if (cur == 0) {
                    int islandArea = 0;
                    int curIndex = convert(i, j, m, n);
                    for (int[] dir : directions) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                            int index = convert(x, y, m, n);
                            int leader = uf.find(index);
                            if (!visited.contains(leader)) {
                                islandArea += uf.getSize(curIndex);
                                visited.add(leader);
                            }

                        }
                    }
                    res = Math.max(res, islandArea + 1);
                }
            }
        }

        return res;
    }

    private int convert(int x, int y, int m, int n) {
        return x * n + y;
    }

    class UnionFind {
        private int count;
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
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
                this.size[rootQ] += this.size[rootP];
            }
        }

        public int getSize(int x) {
            return this.size[find(x)];
        }
    }
}
