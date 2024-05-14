package unionfind;

/*
https://leetcode.com/problems/couples-holding-hands/description/

There are n couples sitting in 2n seats arranged in a row and want to hold hands.

The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat.
The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last
couple being (2n - 2, 2n - 1).

Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people,
then they stand up and switch seats.
 */
public class CouplesHoldingHands {
    public int minSwapsCouples(int[] row) {
        int coupleCount = row.length / 2;
        UnionFind uf = new UnionFind(coupleCount);

        for (int i = 0; i + 1 < row.length; i += 2) {
            uf.union(row[i] / 2, row[i + 1] / 2);
        }

        return coupleCount - uf.getComponents();
    }

    class UnionFind {
        private int components;
        private int[] parent;

        public UnionFind(int n) {
            this.components = n;
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

        public boolean connected (int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                components--;
            }
        }

        public int getComponents() {
            return this.components;
        }
    }
}
