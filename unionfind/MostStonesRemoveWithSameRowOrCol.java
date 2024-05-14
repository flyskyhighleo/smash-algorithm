package unionfind;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/

On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest
possible number of stones that can be removed.
 */
public class MostStonesRemoveWithSameRowOrCol {
    public int removeStones(int[][] stones) {
        Map<Integer, Integer> rowToStone = new HashMap<>();
        Map<Integer, Integer> colToStone = new HashMap<>();
        UnionFind uf = new UnionFind(stones.length);

        for (int i = 0; i < stones.length; i++) {
            int[] stone = stones[i];
            if (rowToStone.containsKey(stone[0])) {
                uf.union(i, rowToStone.get(stone[0]));
            } else if (colToStone.containsKey(stone[1])) {
                uf.union(i, colToStone.get(stone[1]));
            }

            if (!rowToStone.containsKey(stone[0])) {
                rowToStone.put(stone[0], i);
            }
            if (!colToStone.containsKey(stone[1])) {
                colToStone.put(stone[1], i);
            }
        }

        return stones.length - uf.getCount();
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

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--;
            }
        }

        public int getCount() {
            return this.count;
        }
    }
}
