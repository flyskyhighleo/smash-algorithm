package unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-consecutive-sequence/description/
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int res = 1;
        UnionFind uf = new UnionFind(n);

        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!numToIndex.containsKey(nums[i])) {
                numToIndex.put(nums[i], i);
            }
        }

        for (int i = 0; i < n; i++) {
            int curIndex = numToIndex.get(nums[i]);
            int nextIndex = numToIndex.getOrDefault(nums[i] + 1, -1);
            if (nextIndex != -1 && !uf.connected(curIndex, nextIndex)) {
                uf.union(curIndex, nextIndex);
                res = Math.max(res, uf.getSize(i));
            }
        }

        return res;
    }

    class UnionFind {
        private int count;
        private int[] size;
        private int[] parent;

        public UnionFind(int n) {
            this.count = n;
            this.size = new int[n];
            Arrays.fill(size, 1);
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
                size[rootQ] += size[rootP];
            }
        }

        public int getSize(int x) {
            int root = find(x);
            return size[root];
        }

        public int getCount() {
            return this.count;
        }
    }
}
